package sa.revenue.advertisers.admob;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import sa.revenue.general.ApiRequest;

public class UpdateAdmobService extends Service {

    @Override
    public void onCreate() {
        String startDate = AdmobUtils.getLastParsedDate(this);
        new ApiRequest.Admob(UpdateAdmobService.this).send(startDate, false);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}