package sa.revenue.advertisers.admob;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;

import sa.revenue.R;
import sa.revenue.Utils;
import sa.revenue.general.ApiRequest;

//Only called at init or after full reset
public class HistoricalLoadAdmobService extends Service {

    @Override
    public void onCreate() {
        try {
            //Get startdate as set in API settings
            String startDate  = Hawk.get(getString(R.string.admob_start_date_key));

            //Get days ago to start date
            long days = Utils.getDaysAgo(AdmobUtils.admobDateFormat, startDate);

            //Check if there are entries before this date, if so delete them
            AdmobUtils.cleanUpDatabaseBefore(startDate);

            for (int i = 0; i <= days; i++) {

                //Only request when not yet in database.
                if (!AdmobUtils.hasParsedDate(startDate))
                    new ApiRequest.Admob(HistoricalLoadAdmobService.this).send(startDate, false);

                //Get next day to parse
                startDate = Utils.nextDayString(AdmobUtils.admobDateFormat, startDate);

                //stop self when has parsed all days to today
                if (i == days) {
                    stopSelf();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
