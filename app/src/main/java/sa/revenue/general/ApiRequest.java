package sa.revenue.general;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.adsense.AdSense;
import com.google.api.services.adsense.AdSenseScopes;
import com.google.api.services.adsense.model.Account;
import com.google.api.services.adsense.model.Accounts;
import com.google.api.services.adsense.model.AdsenseReportsGenerateResponse;
import com.orhanobut.hawk.Hawk;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.greenrobot.event.EventBus;
import sa.revenue.AppController;
import sa.revenue.R;
import sa.revenue.Utils;
import sa.revenue.advertisers.admob.AdmobUtils;
import sa.revenue.advertisers.admob.UpdateAdmobService;
import sa.revenue.advertisers.tapjoy.TapjoyUtils;
import sa.revenue.advertisers.tapjoy.model.api.Day;
import sa.revenue.general.model.Advertiser;
import sa.revenue.general.model.Message;

public class ApiRequest {
    private static Context mContext = AppController.getInstance().getApplicationContext();

    //Basically admob does all the network handling itself, however for completeness added ehre.
    public static class Admob {
        static int MAX_LIST_PAGE_SIZE = 50;
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        GoogleAccountCredential credential;
        static AdSense adsenseService;
        String account;

        public Admob(Context context) {
            credential = GoogleAccountCredential.usingOAuth2(context, Collections.singleton(AdSenseScopes.ADSENSE));
            credential.setSelectedAccountName(Hawk.get(context.getString(R.string.admob_email_key), context.getString(R.string.admob_email_default)));
            account = Hawk.get(context.getString(R.string.admob_account_id_key));
            adsenseService = new AdSense.Builder(httpTransport, jsonFactory, credential).setApplicationName("RevenueMonitor/0.1.0b").build();
        }

        public List<sa.revenue.advertisers.admob.model.Account> getAccounts() {
            List usersAccounts = new ArrayList();
            //TODO - Additional check when email = null? if that can happen
            try {
                Accounts accounts = adsenseService.accounts().list().setMaxResults(MAX_LIST_PAGE_SIZE).execute();
                if (accounts.getItems() != null && !accounts.getItems().isEmpty()) {
                    //TODO -
                    for (Account account : accounts.getItems()) {
                        sa.revenue.advertisers.admob.model.Account userAccount = new sa.revenue.advertisers.admob.model.Account();
                        userAccount.setName(account.getName());
                        userAccount.setId(account.getId());
                        usersAccounts.add(userAccount);
                    }
                } else {
                    System.out.println("No accounts found.");
                }
            } catch (UserRecoverableAuthIOException e) {
                //TODO - handle this nicely...
                //   startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return usersAccounts;

        }


        public void send(final String startDate, final boolean notifyWhenDone) {
            final AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        AdSense.Accounts.Reports.Generate request = adsenseService.accounts().reports().generate(account, startDate, startDate);
                        request.setDimension(Arrays.asList("AD_CLIENT_ID",
                                "AD_UNIT_CODE",
                                "AD_UNIT_ID",
                                "AD_UNIT_NAME",
                                "DATE"));
                        request.setMetric(Arrays.asList("AD_REQUESTS",
                                "AD_REQUESTS_COVERAGE",
                                "CLICKS",
                                "COST_PER_CLICK",
                                "EARNINGS",
                                "INDIVIDUAL_AD_IMPRESSIONS",
                                "INDIVIDUAL_AD_IMPRESSIONS_CTR",
                                "INDIVIDUAL_AD_IMPRESSIONS_RPM",
                                "MATCHED_AD_REQUESTS",
                                "MATCHED_AD_REQUESTS_CTR",
                                "MATCHED_AD_REQUESTS_RPM",
                                "PAGE_VIEWS",
                                "PAGE_VIEWS_CTR",
                                "PAGE_VIEWS_RPM"));
                        request.setUseTimezoneReporting(true); //To adsense account local time.
                        AdsenseReportsGenerateResponse response = request.execute();

                        //Delete data from startdate
                        AdmobUtils.deleteDataFromDay(startDate);

                        if (response.getRows() != null && !response.getRows().isEmpty()) {
                            // Store result per ad unit
                            for (List<String> row : response.getRows()) {
                                AdmobUtils.storeResultRowAsAdmobAdUnit(row);
                            }
                            //Store data from startdate
                            AdmobUtils.storeResultRowAdAdmobDay(startDate, response.getTotals());

                        } else {
                            System.out.println("No rows returned.");
                        }

                        if (Utils.shouldParseNextDay(AdmobUtils.admobDateFormat,startDate)) {
                            //send(Utils.nextDayString(AdmobUtils.admobDateFormat,startDate), false);
                            EventBus.getDefault().post(new Message(Advertiser.ADMOB, Message.Type.PARSING_NEXT_DAY));
                        } else {
                            EventBus.getDefault().post(new Message(Advertiser.ADMOB, Message.Type.PARSING_DONE));

                            //Stop service when done.
                            Intent intent = new Intent(mContext, UpdateAdmobService.class);
                            mContext.stopService(intent);

                            if (notifyWhenDone) {
                                NotificationBuilder.createNotification(mContext,
                                        mContext.getString(R.string.notification_title),
                                        String.format(mContext.getString(R.string.notification_text), mContext.getString(R.string.admob)));

                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                }

            };
            task.execute();


        }

    }

    public static class Tapjoy {
        //get credentials and decrypt them.
        static String base_url = "https://api.tapjoy.com/";

        public static void validate(String newEmail, String newKey) throws ParseException {
            String url = String.format(base_url + "reporting_data.json?email=%1$s&api_key=%2$s&date=%3$s",
                    newEmail,
                    newKey,
                    Utils.todayString(TapjoyUtils.tapjoyDateFormat));


            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Valid when this response
                            Hawk.put(mContext.getString(R.string.tapjoy_valid), mContext.getString(R.string.valid));
                            //Set preference to valid and send broadcast to update screen.
                            EventBus.getDefault().post(new Message(Advertiser.TAPJOY, Message.Type.UPDATE_API_STATUS));

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Invalid when this response
                            Hawk.put(mContext.getString(R.string.tapjoy_valid), mContext.getString(R.string.invalid));
                            //Set preference to valid and send broadcast to update screen.
                            EventBus.getDefault().post(new Message(Advertiser.TAPJOY, Message.Type.UPDATE_API_STATUS));
                        }
                    }
            );
            getRequest.setRetryPolicy(new DefaultRetryPolicy(100000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(getRequest, base_url);
        }

        public static void send(final String startDate, final boolean notifyWhenDone) {
            String emailKey = Hawk.get(mContext.getString(R.string.tapjoy_email_key));
            String apikeyKey = Hawk.get(mContext.getString(R.string.tapjoy_apikey_key));

            String url = String.format(base_url + "reporting_data.json?email=%1$s&api_key=%2$s&date=%3$s",
                    emailKey,
                    apikeyKey,
                    startDate);

            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Moshi moshi = new Moshi.Builder().build();
                            JsonAdapter<Day> jsonAdapter = moshi.adapter(Day.class);
                            try {
                                Day marketingData = jsonAdapter.fromJson(response.toString());

                                //Delete date from startDate
                                TapjoyUtils.deleteDataFromDay(marketingData.getDate());

                                //Add data from startdate
                                TapjoyUtils.storeApiToDb(marketingData);

                                if (Utils.shouldParseNextDay(TapjoyUtils.tapjoyDateFormat,marketingData.getDate())) {
                                  //   send(TapjoyUtils.nextDayString(marketingData.getDate()), false);
                                    EventBus.getDefault().post(new Message(Advertiser.TAPJOY, Message.Type.PARSING_NEXT_DAY));
                                } else {
                                    EventBus.getDefault().post(new Message(Advertiser.TAPJOY, Message.Type.PARSING_DONE));
                                    //Stop service when done.
                                    Intent intent = new Intent(mContext, UpdateAdmobService.class);
                                    mContext.stopService(intent);

                                    //show notification
                                    if (notifyWhenDone) {
                                        NotificationBuilder.createNotification(mContext,
                                                mContext.getString(R.string.notification_title),
                                                String.format(mContext.getString(R.string.notification_text), mContext.getString(R.string.tapjoy)));
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            getRequest.setRetryPolicy(new DefaultRetryPolicy(100000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(getRequest, base_url);
        }

    }

}
