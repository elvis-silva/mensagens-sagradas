package com.elvis.biblia.msg;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.elvis.biblia.msg.impl.IActivity;
import com.elvis.biblia.msg.ui.AbstractFragment;
import com.elvis.biblia.msg.ui.NotificationService;

import java.util.Date;

/**
 * Created by elvis on 10/07/15.
 */
public class CalendarNotification extends AbstractFragment implements IActivity {

    DatePicker datePicker;
    TimePicker timePicker;

//    public CalendarNotification(Context pContext) {
//        super(pContext);
//    }

    @Override
    protected void initScreen() {
//        datePicker = (DatePicker) findViewById(R.id.datePicker);
//        timePicker = (TimePicker) findViewById(R.id.timePicker);
//
//        setOnClickListener(R.id.btn_notification);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setOnClickListener(int pViewResId) {
        findViewById(pViewResId).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
/*        switch (v.getId()) {
            case R.id.btn_notification:

                Intent intent = new Intent(context, NotificationService.class);
//                Calendar calendar = Calendar.getInstance();
//                remind(calendar.getTime(), "Mensagens Sagradas", "Você tem uma mensagem especial.");
//                calendar.set(Calendar.MONTH, datePicker.getMonth());
//                calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
//                calendar.set(Calendar.YEAR, datePicker.getYear());
//                calendar.set(Calendar.HOUR, timePicker.getCurrentHour());
//                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60 * 1000, pendingIntent);




//                Notification notification = new Notification.Builder(context)
//                        .setTicker("Você tem uma mensagem especial.")
//                        .setContentTitle("Você tem uma mensagem especial.")
//                        .setSmallIcon(R.drawable.ic_launcher)
//                        .getNotification();
//                notification.flags = Notification.FLAG_AUTO_CANCEL;
//                notification.setLatestEventInfo(context, "Mensagens Sagradas", "Você tem uma mensagem especial.",
//                        pendingIntent);
//
//                NotificationManager notificationManager =
//                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(0, notification);

//                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                break;
        }
*/    }

    public void remind(Date dateTime, String title, String message) {

        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("message", message);
        alarmIntent.putExtra("title", title);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //TODO: For demo set after 5 seconds.
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 5 * 1000, pendingIntent);

    }

//    @BroadcastReceiver
    public class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            String title = intent.getStringExtra("title");

            Intent notIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);

//            var style = new NotificationManagerCompat..BigTextStyle();
//            style.BigText(message);

//            int resourceId = R.drawable.ic_launcher;

//            var wearableExtender = new NotificationCompat.WearableExtender()
//                    .SetBackground(BitmapFactory.DecodeResource(context.Resources, resourceId))
//                    ;

            //Generate a notification with just short text and small icon
//            Notification notification = new Notification.Builder(context)
//                    .setContentIntent(contentIntent)
//                    .setSmallIcon (resourceId)
//                    .setContentTitle(title)
//                    .setContentText(message)
//                    .SetStyle(style)
//                    .SetWhen(Java.Lang.JavaSystem.CurrentTimeMillis())
//                    .setAutoCancel(true).getNotification();
//                    .Extend(wearableExtender);

//            var notification = builder.Build();
//            manager.notify(0, notification);
        }
    }
}
