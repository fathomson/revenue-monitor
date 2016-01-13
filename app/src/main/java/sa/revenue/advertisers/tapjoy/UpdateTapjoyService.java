package sa.revenue.advertisers.tapjoy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import sa.revenue.general.ApiRequest;

public class UpdateTapjoyService extends Service {

    @Override
    public void onCreate() {
        String startDate = TapjoyUtils.getLastParsedDate(this);
        ApiRequest.Tapjoy.send(startDate, false);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
