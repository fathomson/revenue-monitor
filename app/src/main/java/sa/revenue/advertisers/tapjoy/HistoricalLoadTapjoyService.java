package sa.revenue.advertisers.tapjoy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;

import sa.revenue.R;
import sa.revenue.Utils;
import sa.revenue.general.ApiRequest;

//Only called at init or after full reset
public class HistoricalLoadTapjoyService extends Service {

    @Override
    public void onCreate() {
        try {
            //Get startdate as set in API settings
            String startDate = Hawk.get(getString(R.string.tapjoy_start_date_key));

            //Get days ago to start date
            long days = Utils.getDaysAgo(TapjoyUtils.tapjoyDateFormat, startDate);

            //Check if there are entries before this date, if so delete them
            TapjoyUtils.cleanUpDatabaseBefore(startDate);

            for (int i = 0; i <= days; i++) {

                //Only request when not yet in database.
                if (!TapjoyUtils.hasParsedDate(startDate))
                    ApiRequest.Tapjoy.send(startDate, false);

                //Get next day to parse
                startDate = Utils.nextDayString(TapjoyUtils.tapjoyDateFormat, startDate);

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
