package sa.revenue.fragment;


import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.common.AccountPicker;
import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.event.EventBus;
import sa.revenue.R;
import sa.revenue.Utils;
import sa.revenue.advertisers.admob.AdmobUtils;
import sa.revenue.advertisers.admob.HistoricalLoadAdmobService;
import sa.revenue.advertisers.admob.UpdateAdmobReceiver;
import sa.revenue.advertisers.admob.UpdateAdmobService;
import sa.revenue.advertisers.admob.adapter.AdmobAccountAdapter;
import sa.revenue.advertisers.tapjoy.HistoricalLoadTapjoyService;
import sa.revenue.advertisers.tapjoy.TapjoyUtils;
import sa.revenue.advertisers.tapjoy.UpdateTapjoyReceiver;
import sa.revenue.general.ApiRequest;
import sa.revenue.general.model.Advertiser;
import sa.revenue.general.model.Message;


public class SettingsFragment extends PreferenceFragment {
    String TAG = SettingsFragment.class.getSimpleName();
    private Context globalContext = null;
    SharedPreferences.Editor editor;


    //START Admob preferences
    static final int ADMOB_REQUEST_PICK_GOOGLE_ACCOUNT = 1000;

    private String admobStartDate;
    private String admobEmail;
    private String admobId;
    private String admobName;
    private int admobSyncFrequency;

    private Preference mAdmobDate;
    private Preference mAdmobEmail;
    private Preference mAdmobId;
    private Preference mAdmobStart;
    private ListPreference mAdmobSync;
    private CheckBoxPreference mAdmobNotify;
    private Preference mAdmobStats;
    private Preference mAdmobReset;
    //END Admob preferences


    //START Tapjoy preferences
    private String tapjoyStartDate;
    private String tapjoyEmail;
    private String tapjoyApikey;
    private int tapjoySyncFrequency;

    private Preference mTapjoyDate;
    private EditTextPreference mTapjoyEmail;
    private EditTextPreference mTapjoyApikey;
    private Preference mTapjoyStart;
    private ListPreference mTapjoySync;
    private CheckBoxPreference mTapjoyNotify;
    private Preference mTapjoyStats;
    private Preference mTapjoyReset;
    //END Tapjoy preferences


    public SettingsFragment() {

    }


    //TODO - Remove hawk keys from shared preference!!

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        globalContext = this.getActivity();
        editor = PreferenceManager.getDefaultSharedPreferences(globalContext).edit();

        //START Admob preferences
        admobStartDate = Hawk.get(getString(R.string.admob_start_date_key), getString(R.string.start_date_blank));
        admobEmail = Hawk.get(getString(R.string.admob_email_key), getString(R.string.admob_email_summary));
        admobId = Hawk.get(getString(R.string.admob_account_id_key), getString(R.string.admob_account_id_default));
        admobName = Hawk.get(getString(R.string.admob_account_name_key), getString(R.string.admob_account_name_default));
        admobSyncFrequency = Integer.parseInt(Hawk.get(getString(R.string.admob_syncfrequency_key), getString(R.string.admob_syncfrequency_default)));

        mAdmobDate = findPreference(getString(R.string.admob_start_date_key));
        mAdmobEmail = findPreference(getString(R.string.admob_email_key));
        mAdmobId = findPreference(getString(R.string.admob_account_id_key));
        mAdmobStart = findPreference(getString(R.string.admob_start_data_load_key));
        mAdmobSync = (ListPreference) findPreference(getString(R.string.admob_syncfrequency_key));
        mAdmobNotify = (CheckBoxPreference) findPreference(getString(R.string.admob_notify_key));
        mAdmobStats = findPreference(getString(R.string.admob_stats_key));
        mAdmobReset = findPreference(getString(R.string.admob_reset_key));
        //END Admob preferences

        //START Tapjoy preferences
        tapjoyStartDate = Hawk.get(getString(R.string.tapjoy_start_date_key), getString(R.string.start_date_blank));
        tapjoyEmail = Hawk.get(getString(R.string.tapjoy_email_key), getString(R.string.tapjoy_email_default));
        tapjoyApikey = Hawk.get(getString(R.string.tapjoy_apikey_key), getString(R.string.tapjoy_apikey_default));
        tapjoySyncFrequency = Integer.parseInt(Hawk.get(getString(R.string.tapjoy_syncfrequency_key), getString(R.string.tapjoy_syncfrequency_default)));

        mTapjoyDate = findPreference(getString(R.string.tapjoy_start_date_key));
        mTapjoyEmail = (EditTextPreference) findPreference(getString(R.string.tapjoy_email_key));
        mTapjoyApikey = (EditTextPreference) findPreference(getString(R.string.tapjoy_apikey_key));
        mTapjoyStart = findPreference(getString(R.string.tapjoy_start_data_load_key));
        mTapjoySync = (ListPreference) findPreference(getString(R.string.tapjoy_syncfrequency_key));
        mTapjoyNotify = (CheckBoxPreference) findPreference(getString(R.string.tapjoy_notify_key));
        mTapjoyStats = findPreference(getString(R.string.tapjoy_stats_key));
        mTapjoyReset = findPreference(getString(R.string.tapjoy_reset_key));
        //END Tapjoy preferences


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] mSyncFrequency = getResources().getStringArray(R.array.syncFrequency);

        //START Admob preference
        mAdmobDate.setSummary(admobStartDate);
        mAdmobDate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                admobStartDate = Hawk.get(getString(R.string.admob_start_date_key), getString(R.string.start_date_blank));
                showDateDialog(Advertiser.ADMOB, admobStartDate);
                return true;
            }
        });
        mAdmobEmail.setSummary(admobEmail);
        mAdmobEmail.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                pickGoogleUserAccount();
                return true;
            }
        });
        mAdmobId.setSummary(String.format(getString(R.string.admob_account_id_summary), admobId, admobName));
        mAdmobId.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                if (Hawk.get(getString(R.string.admob_email_key), null) != null) {
                    new getAdmobAccountsTask().execute();
                } else {
                    pickGoogleUserAccount();
                }
                return true;
            }
        });

        mAdmobStart.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startAdmobHistoricalLoad(true);
                return true;
            }
        });

        mAdmobSync.setSummary(mSyncFrequency[admobSyncFrequency]);
        mAdmobSync.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Hawk.put(getString(R.string.admob_syncfrequency_key), newValue);
                mAdmobSync.setSummary(mSyncFrequency[Integer.parseInt((String) newValue)]);
                Intent intent = new Intent(globalContext, UpdateAdmobReceiver.class);
                setSyncSchedule(Integer.parseInt((String) newValue), intent);
                return true;
            }
        });

        updateAdmobStats();


        mAdmobReset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showResetDataDialog(Advertiser.ADMOB);
                return true;
            }
        });
        //END Admob preferen

        //START Tapjoy preferences
        mTapjoyDate.setSummary(tapjoyStartDate);
        mTapjoyDate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                tapjoyStartDate = Hawk.get(getString(R.string.tapjoy_start_date_key), getString(R.string.start_date_blank));
                showDateDialog(Advertiser.TAPJOY, tapjoyStartDate);
                return true;
            }
        });
        mTapjoyEmail.setSummary(tapjoyEmail);
        mTapjoyEmail.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String newEmail = (String) newValue;
                mTapjoyEmail.setSummary(newEmail);
                Hawk.put(getString(R.string.tapjoy_email_key), newValue);
                validateTapjoyCredentials(mTapjoyEmail.getSummary().toString(), mTapjoyApikey.getSummary().toString());
                return true;
            }
        });

        mTapjoyApikey.setSummary(tapjoyApikey);
        mTapjoyApikey.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String newApiKey = (String) newValue;
                mTapjoyApikey.setSummary(newApiKey);
                Hawk.put(getString(R.string.tapjoy_apikey_key), newApiKey);
                validateTapjoyCredentials(mTapjoyEmail.getSummary().toString(), mTapjoyApikey.getSummary().toString());
                return true;
            }
        });


        mTapjoyStart.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startTapjoyHistoricalLoad(true);
                return true;
            }
        });

        mTapjoySync.setSummary(mSyncFrequency[tapjoySyncFrequency]);
        mTapjoySync.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Hawk.put(getString(R.string.tapjoy_syncfrequency_key), newValue);
                mTapjoySync.setSummary(mSyncFrequency[Integer.parseInt((String) newValue)]);
                Intent intent = new Intent(globalContext, UpdateTapjoyReceiver.class);
                setSyncSchedule(Integer.parseInt((String) newValue), intent);
                return true;
            }
        });

        updateTapjoyStats();

        mTapjoyReset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showResetDataDialog(Advertiser.TAPJOY);
                return true;
            }
        });
        //END Tapjoy preferences


    }


    //START Admob

    private void startAdmobHistoricalLoad(boolean startLoad) {
        //Admob valid - start historical data load
        if (AdmobUtils.admobSetupCorrect(globalContext)) {
            if (startLoad) {
                Intent admob = new Intent(globalContext, HistoricalLoadAdmobService.class);
                globalContext.startService(admob);
                try {
                    updateAdmobParseStatus();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } else {
            StringBuilder message = new StringBuilder();
            message.append(Hawk.get(getString(R.string.admob_start_date_key)) == null ? getString(R.string.start_date) + " " : "");
            message.append(Hawk.get(getString(R.string.admob_email_key)) == null ? getString(R.string.admob_email) + " " : "");
            message.append(Hawk.get(getString(R.string.admob_account_id_key)) == null ? getString(R.string.admob_account_id) + " " : "");
            message.append("required");
            mAdmobStart.setSummary(message.toString());
        }

    }

    private void updateAdmobParseStatus() throws ParseException {
        long daysInDatabase = AdmobUtils.getDaysInDB();
        long totalDays = Utils.getDaysAgo(AdmobUtils.admobDateFormat, (String) Hawk.get(getString(R.string.admob_start_date_key)));
        mAdmobStart.setSummary("Data loading (" + daysInDatabase + "/" + (totalDays + 1) + ")");
    }

    private void updateAdmobStats() {
        String lastParsedDateAdmob = AdmobUtils.getLastParsedDate(globalContext);
        long admobDaysInDB = AdmobUtils.getDaysInDB();
        long admobAppsInDB = AdmobUtils.getAppsInDB();
        mAdmobStats.setSummary(String.format(getString(R.string.tapjoy_stats_summary), lastParsedDateAdmob, admobDaysInDB, admobAppsInDB));

    }

    private void pickGoogleUserAccount() {
        String[] accountTypes = new String[]{"com.google"};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, true, null, null, null, null);
        startActivityForResult(intent, ADMOB_REQUEST_PICK_GOOGLE_ACCOUNT);
    }

    private class getAdmobAccountsTask extends AsyncTask<Void, Void, List<sa.revenue.advertisers.admob.model.Account>> {
        AlertDialog alertDialog;
        ProgressBar progressBar_loadingAdmobAccounts;
        ListView listView_admobAccounts;
        List<sa.revenue.advertisers.admob.model.Account> accounts = new ArrayList<>();
        AdmobAccountAdapter adapter;

        String email;


        @Override
        protected void onPreExecute() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(globalContext);
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View accountDialog = inflater.inflate(R.layout.dialog_list, null);
            alertDialogBuilder.setView(accountDialog);
            alertDialogBuilder.setTitle("Select Account");
            progressBar_loadingAdmobAccounts = (ProgressBar) accountDialog.findViewById(R.id.progressBar_loadingAdmobAccounts);
            listView_admobAccounts = (ListView) accountDialog.findViewById(R.id.listView_admobAccounts);
            adapter = new AdmobAccountAdapter(globalContext, accounts);
            listView_admobAccounts.setAdapter(adapter);
            alertDialog = alertDialogBuilder.show();
            email = Hawk.get(getString(R.string.admob_email_key), getString(R.string.admob_email_summary));
        }

        @Override
        protected List<sa.revenue.advertisers.admob.model.Account> doInBackground(Void... params) {
            return new ApiRequest.Admob(globalContext).getAccounts();
        }

        @Override
        protected void onPostExecute(final List<sa.revenue.advertisers.admob.model.Account> accounts) {
            adapter.swap(accounts);
            progressBar_loadingAdmobAccounts.setVisibility(View.GONE);
            listView_admobAccounts.setVisibility(View.VISIBLE);
            listView_admobAccounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Set
                    admobId = accounts.get(position).getId();
                    admobName = accounts.get(position).getName();
                    Hawk.put(getString(R.string.admob_account_id_key), admobId);
                    Hawk.put(getString(R.string.admob_account_name_key), admobName);
                    mAdmobId.setSummary(String.format(getString(R.string.admob_account_id_summary), admobId, admobName));
                    if (alertDialog.isShowing())
                        alertDialog.dismiss();


                    //Update start load but do not start yet.
                    startAdmobHistoricalLoad(false);
                }
            });

        }
    }

    DatePickerDialog.OnDateSetListener admobDataSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String startDate = Utils.getDateFromDatePicker(AdmobUtils.admobDateFormat, year, monthOfYear, dayOfMonth);
            Hawk.put(getString(R.string.admob_start_date_key), startDate);
            mAdmobDate.setSummary(startDate);

            //Update start load but do not start yet.
            startAdmobHistoricalLoad(false);
        }
    };
    //END Admob


    //START Tapjoy preferences

    private void startTapjoyHistoricalLoad(boolean startLoad) {
        //Admob valid - start historical data load
        if (TapjoyUtils.tapjoySetupCorrect(globalContext)) {
            String tapjoyValid = Hawk.get(getString(R.string.tapjoy_valid));

            //Check if tapjoy credentials are setup correctly
            if (TapjoyUtils.tapjoyCredentialsAreValid(globalContext)) {

                if (startLoad) {
                    Intent tapjoy = new Intent(globalContext, HistoricalLoadTapjoyService.class);
                    globalContext.startService(tapjoy);
                    try {
                        updateTapjoyParseStatus();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                mTapjoyStart.setSummary(tapjoyValid);

            }


        } else {
            StringBuilder message = new StringBuilder();
            message.append(Hawk.get(getString(R.string.tapjoy_start_date_key)) == null ? getString(R.string.start_date) + " " : "");
            message.append(Hawk.get(getString(R.string.tapjoy_email_key)) == null ? getString(R.string.tapjoy_email) + " " : "");
            message.append(Hawk.get(getString(R.string.tapjoy_apikey_key)) == null ? getString(R.string.tapjoy_apikey) + " " : "");
            message.append("required");
            mTapjoyStart.setSummary(message.toString());
        }

    }

    private void updateTapjoyParseStatus() throws ParseException {
        long daysInDatabase = TapjoyUtils.getDaysInDB();
        long totalDays = Utils.getDaysAgo(TapjoyUtils.tapjoyDateFormat, (String) Hawk.get(getString(R.string.tapjoy_start_date_key)));
        mTapjoyStart.setSummary("Data loading (" + daysInDatabase + "/" + (totalDays + 1) + ")");

    }

    private void updateTapjoyStats() {

        String lastParsedDateTapjoy = TapjoyUtils.getLastParsedDate(globalContext);
        long tapjoyDaysInDB = TapjoyUtils.getDaysInDB();
        long tapjoyAppsInDB = TapjoyUtils.getAppsInDB();
        mTapjoyStats.setSummary(String.format(getString(R.string.tapjoy_stats_summary), lastParsedDateTapjoy, tapjoyDaysInDB, tapjoyAppsInDB));


    }


    DatePickerDialog.OnDateSetListener tapjoyDataSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String startDate = Utils.getDateFromDatePicker(TapjoyUtils.tapjoyDateFormat, year, monthOfYear, dayOfMonth);
            Hawk.put(getString(R.string.tapjoy_start_date_key), startDate);
            mTapjoyDate.setSummary(startDate);

            startTapjoyHistoricalLoad(false);
        }
    };


    private void validateTapjoyCredentials(String newEmail, String newKey) {
        //Check if valid email address  and apikey(32)
        if (Utils.isValidEmail(newEmail) && newKey.length() == 32) {
            new validateTapjoyCredentialsTask(newEmail, newKey).execute();
        } else {
            //Invalid..
            Hawk.put(getString(R.string.tapjoy_valid), getString(R.string.invalid));
        }

    }

    private class validateTapjoyCredentialsTask extends AsyncTask<Void, Void, String> {
        String newEmail;
        String newKey;

        private validateTapjoyCredentialsTask(String newEmail, String newKey) {
            this.newEmail = newEmail;
            this.newKey = newKey;
        }

        @Override
        protected void onPreExecute() {
            Hawk.put(getString(R.string.tapjoy_valid), getString(R.string.validating));
            startTapjoyHistoricalLoad(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                ApiRequest.Tapjoy.validate(newEmail, newKey);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String revenue) {
            startTapjoyHistoricalLoad(false);
        }
    }

    //END Tapjoy


    //interval in ms, service intent of app.
    private void setSyncSchedule(int pos, Intent serviceIntent) {
        String[] mSyncFrequencyMs = getResources().getStringArray(R.array.syncFrequencyMS);
        long interval = Long.parseLong(mSyncFrequencyMs[pos]);

        AlarmManager alarmMgr = (AlarmManager) globalContext.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(globalContext, 0, serviceIntent, 0);
        alarmMgr.cancel(alarmIntent);
        Calendar calendar = Calendar.getInstance();


        alarmMgr.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), interval, alarmIntent);
    }

    private void showDateDialog(Advertiser advertiser, String startDate) {
        DatePickerDialog.OnDateSetListener dateSetListener = null;
        Calendar c = null;
        switch (advertiser) {
            case ADMOB:
                dateSetListener = admobDataSetListener;
                c = Utils.getCalendar(AdmobUtils.admobDateFormat, startDate);
                break;
            case TAPJOY:
                dateSetListener = tapjoyDataSetListener;
                c = Utils.getCalendar(TapjoyUtils.tapjoyDateFormat, startDate);
                break;
        }


        new DatePickerDialog(globalContext, R.style.DialogTheme, dateSetListener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void showResetDataDialog(final Advertiser advertiser) {
        String title = null;
        String message = null;
        switch (advertiser) {
            case ADMOB:
                title = String.format(getString(R.string.dialog_reset_title), AdmobUtils.admob);
                message = String.format(getString(R.string.dialog_reset_message), AdmobUtils.admob, "500ms");
                break;
            case TAPJOY:
                title = String.format(getString(R.string.dialog_reset_title), TapjoyUtils.tapjoy);
                message = String.format(getString(R.string.dialog_reset_message), AdmobUtils.admob, "30s");
                break;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(globalContext);
        builder.setMessage(message)
                .setTitle(title);
        builder.setPositiveButton(R.string.dialog_reset_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                switch (advertiser) {
                    case ADMOB:
                        AdmobUtils.clearDayAndAppTable();
                        if (Hawk.get(getString(R.string.admob_account_id_key), null) != null) {

                            Intent admob = new Intent(globalContext, UpdateAdmobService.class);
                            globalContext.startService(admob);
                            updateAdmobStats();
                        }
                        break;
                    case TAPJOY:
                        TapjoyUtils.clearDayAndAppTable();
                        if (Hawk.get(getString(R.string.tapjoy_email_key)) != null && Hawk.get(getString(R.string.tapjoy_apikey_key)) != null) {

                            Log.i("Api key", Hawk.get(getString(R.string.tapjoy_apikey_key)) + "");

                            //   Intent tapjoy = new Intent(globalContext, UpdateTapjoyService.class);
                            //  globalContext.startService(tapjoy);
                            Intent tapjoy = new Intent(globalContext, HistoricalLoadTapjoyService.class);
                            globalContext.startService(tapjoy);
                            updateTapjoyStats();
                        }
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.dialog_reset_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADMOB_REQUEST_PICK_GOOGLE_ACCOUNT:
                if (resultCode == Activity.RESULT_OK) {
                    //Get Admob email and collect other Admob data.
                    String email = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (email != null) {
                        //When has email, save it and continue to get admob details
                        Hawk.put(getString(R.string.admob_email_key), email);
                        mAdmobEmail.setSummary(email);
                        //Update start load but do not start yet.
                        startAdmobHistoricalLoad(false);
                        new getAdmobAccountsTask().execute();
                    } else {
                        pickGoogleUserAccount();
                    }

                } else if (resultCode == Activity.RESULT_CANCELED) {

                }
                break;
            default:
                break;
        }

        // Handle the result from exceptions
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        //remove keys
        editor.remove(getString(R.string.admob_email_key));
        editor.remove(getString(R.string.admob_account_name_key));
        editor.remove(getString(R.string.admob_account_id_key));
        editor.remove(getString(R.string.admob_start_date_key));
        editor.remove(getString(R.string.admob_syncfrequency_key));
        editor.remove(getString(R.string.admob_notify_key));
        editor.remove(getString(R.string.tapjoy_apikey_key));
        editor.remove(getString(R.string.tapjoy_email_key));
        editor.remove(getString(R.string.tapjoy_syncfrequency_key));
        editor.remove(getString(R.string.tapjoy_notify_key));
        editor.commit();
        super.onStop();
    }


    // Called in Android UI's main thread
    public void onEventMainThread(Message message) {
        switch (message.getAdvertiser()) {
            case ADMOB:
                updateAdmobStats();
                try {
                    updateAdmobParseStatus();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
            case TAPJOY:
                updateTapjoyStats();
                try {
                    updateTapjoyParseStatus();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (message.getType()) {
                    case UPDATE_API_STATUS:
                        startTapjoyHistoricalLoad(true);
                        break;

                }
            default:
                break;
        }
    }


}
