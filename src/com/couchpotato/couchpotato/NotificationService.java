package com.couchpotato.couchpotato;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends Service {

    private NotificationManager nm;
    private static final String TAG = "NotificationService";
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.v(TAG, "on onCreate");

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Log.v(TAG, "on onStartCommand");
        Intent mainActivityIntent = new Intent(this, TVShow.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, mainActivityIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("bothering you")
                .setContentText("Just bothering you from example code")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults|= Notification.DEFAULT_LIGHTS;
        nm.notify(0, notification); 

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}