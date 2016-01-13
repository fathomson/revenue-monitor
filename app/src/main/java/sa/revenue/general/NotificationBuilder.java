package sa.revenue.general;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import sa.revenue.InitActivity;
import sa.revenue.R;

/**
 * Created by un on 1/11/2016.
 */
public class NotificationBuilder {
    public static int NOTIFICATION_UPDATE_ID = 1;

    public static void createNotification(Context context, String title, String text) {
        //Wrtie neat function to show only when is in need.

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, InitActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(getNotificationIcon())
                        .setContentTitle(title)
                        .setContentText(text);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(NOTIFICATION_UPDATE_ID, mBuilder.build());
    }

    private static int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.icon_notification : R.mipmap.ic_launcher;
    }
}
