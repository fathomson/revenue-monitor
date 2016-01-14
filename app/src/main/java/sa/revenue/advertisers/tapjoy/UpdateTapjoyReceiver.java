package sa.revenue.advertisers.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;

import sa.revenue.R;
import sa.revenue.Utils;
import sa.revenue.general.ApiRequest;

/**
 * Created by un on 1/11/2016.
 */
public class UpdateTapjoyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //Database is up to date
            String lastParsedDate = TapjoyUtils.getLastParsedDate(context);
            if (lastParsedDate.equals(Utils.todayString(TapjoyUtils.tapjoyDateFormat))) {
                boolean showNotification = Hawk.get(context.getString(R.string.tapjoy_notify_key), false);
                ApiRequest.Tapjoy.send(lastParsedDate, showNotification);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}