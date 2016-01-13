package sa.revenue.advertisers.admob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;

import sa.revenue.R;
import sa.revenue.general.ApiRequest;

/**
 * Created by un on 1/11/2016.
 */
public class UpdateAdmobReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //Database is up to date
            String lastParsedDate = AdmobUtils.getLastParsedDate(context);
            if (lastParsedDate.equals(AdmobUtils.todayString())) {
                boolean showNotification = Hawk.get(context.getString(R.string.admob_notify_key), false);
                new ApiRequest.Admob(context).send(lastParsedDate, showNotification);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}