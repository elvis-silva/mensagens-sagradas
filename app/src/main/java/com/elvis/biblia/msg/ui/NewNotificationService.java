package com.elvis.biblia.msg.ui;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.elvis.biblia.msg.MainActivity;
import com.elvis.biblia.msg.R;
import com.elvis.biblia.msg.context.Contexts;

/**
 * Created by elvis on 10/07/15.
 */
public class NewNotificationService extends Service {
    private NotificationManager notificationManager;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = R.string.new_local_service_started;

//    private final NotificationCompat.Builder notificationBuilder;

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        NewNotificationService getService() {
            return NewNotificationService.this;
        }
    }

    @Override
    public void onCreate() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Display a notification about us starting.  We put an icon in the status bar.
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        notificationManager.cancel(NOTIFICATION);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
//        CharSequence text = getText(R.string.local_service_started);

        Contexts.getInstance().intent = 0;

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getText(R.string.app_name))
                .setContentText(getString(NOTIFICATION))
                .setAutoCancel(true);

        // Set the icon, scrolling text and timestamp
//        Notification notification = new Notification(R.mipmap.ic_launcher, text,
//                System.currentTimeMillis());

        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("intent_code", "c");
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);//PendingIntent.getActivity(this, 0, intent, 0);

        notificationBuilder.setContentIntent(contentIntent);

//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(NOTIFICATION, notificationBuilder.build());

//        notificationBuilder.build();
//
//        synchronized (notificationBuilder) {
//            notificationBuilder.build().notify();
//        }

        // Set the info for the views that show in the notification panel.
//        notification.setLatestEventInfo(this, "Mensagens Sagradas",
//                text, contentIntent);

        // Destroy on click.
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification.
//        notificationManager.notify(NOTIFICATION, notification);
    }
}
