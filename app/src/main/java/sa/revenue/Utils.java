package sa.revenue;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import sa.revenue.advertisers.admob.AdmobUtils;
import sa.revenue.advertisers.admob.HistoricalLoadAdmobService;
import sa.revenue.advertisers.admob.UpdateAdmobService;
import sa.revenue.advertisers.tapjoy.HistoricalLoadTapjoyService;
import sa.revenue.advertisers.tapjoy.TapjoyUtils;
import sa.revenue.advertisers.tapjoy.UpdateTapjoyService;

/**
 * Created by un on 12/29/2015.
 */
public class Utils {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Calendar updateCalendar(Calendar oldCalendar, int hour_of_day, int minute, int second, int millis) {
        Calendar newCalendar = (Calendar) oldCalendar.clone();
        newCalendar.set(Calendar.HOUR_OF_DAY, hour_of_day);
        newCalendar.set(Calendar.MINUTE, minute);
        newCalendar.set(Calendar.SECOND, second);
        newCalendar.set(Calendar.MILLISECOND, millis);
        return newCalendar;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static String formatDoubleAsCurreny(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }

    public static String getDateXdaysAgo(int days) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -days);
        return dateFormat.format(new Date(c.getTimeInMillis()));
    }


    public static void checkAndUpdateData(Context context) {
        //Start admob when is initiated
        if (AdmobUtils.admobSetupCorrect(context)) {
            try {
                String startDate = Hawk.get(context.getString(R.string.admob_start_date_key));
                long daysAgo = Utils.getDaysAgo(AdmobUtils.admobDateFormat, startDate);
                long daysInDB = AdmobUtils.getDaysInDB();

                //When database is up to date only update little, otherwise check which misses
                //TODO - Might be a better way.
                Intent admob;
                if (daysAgo == daysInDB || (daysAgo + 1) == daysInDB) {
                    admob = new Intent(context, UpdateAdmobService.class);
                } else {
                    admob = new Intent(context, HistoricalLoadAdmobService.class);
                }
                context.startService(admob);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        //and should be valid to.
        if (TapjoyUtils.tapjoyCredentialsAreValid(context)) {

            try {
                String startDate = Hawk.get(context.getString(R.string.tapjoy_start_date_key));
                long daysAgo = Utils.getDaysAgo(TapjoyUtils.tapjoyDateFormat, startDate);
                long daysInDB = TapjoyUtils.getDaysInDB();

                //When database is up to date only update little, otherwise check which misses
                //TODO - Might be a better way.
                Intent tapjoy;
                if (daysAgo == daysInDB || (daysAgo + 1) == daysInDB) {
                    tapjoy = new Intent(context, UpdateTapjoyService.class);
                } else {
                    tapjoy = new Intent(context, HistoricalLoadTapjoyService.class);
                }
                context.startService(tapjoy);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    //
    //  Advertiser date functions
    //

    // Get number of days to date in past
    public static long getDaysAgo(SimpleDateFormat dateFormat, String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(date));
        long diff = Calendar.getInstance().getTimeInMillis() - c.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(diff);
    }

    //Formats date picked from date picket to date
    public static String getDateFromDatePicker(SimpleDateFormat dateFormat, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return dateFormat.format(c.getTime());
    }

    //get today formated as date string
    public static String todayString(SimpleDateFormat dateFormat) throws ParseException {
        Calendar c = Calendar.getInstance();
        return dateFormat.format(new Date(c.getTimeInMillis()));
    }

    //Get the next day based on a date string
    public static String nextDayString(SimpleDateFormat dateFormat, String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(date));
        c.add(Calendar.DATE, 1);
        return dateFormat.format(new Date(c.getTimeInMillis()));
    }

    //When date string is after today retun false, else true
    public static boolean shouldParseNextDay(SimpleDateFormat dateFormat, String oldDate) throws ParseException {
        Calendar old = Calendar.getInstance();
        old.setTime(dateFormat.parse(oldDate));
        old.set(Calendar.HOUR_OF_DAY, 23);
        old.set(Calendar.MINUTE, 59);
        old.set(Calendar.SECOND, 59);
        return old.before(Calendar.getInstance());
    }

    //Convert date to calendar
    public static Calendar getCalendar(SimpleDateFormat dateFormat, String date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

}
