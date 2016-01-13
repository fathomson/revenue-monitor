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

import sa.revenue.advertisers.admob.UpdateAdmobService;
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


    public static void startAllUpdateServices(Context context) {
        //Start admob when is initiated
        if (Hawk.get(context.getString(R.string.admob_start_date_key), null) != null && Hawk.get(context.getString(R.string.admob_account_id_key), null) != null) {
            Intent admob = new Intent(context, UpdateAdmobService.class);
            context.startService(admob);
        }

        //and should be valid to.
        if (Hawk.get(context.getString(R.string.tapjoy_start_date_key), null) != null && Hawk.get(context.getString(R.string.tapjoy_email_key)) != null && Hawk.get(context.getString(R.string.tapjoy_apikey_key)) != null) {
            Intent tapjoy = new Intent(context, UpdateTapjoyService.class);
            context.startService(tapjoy);
        }
    }

}
