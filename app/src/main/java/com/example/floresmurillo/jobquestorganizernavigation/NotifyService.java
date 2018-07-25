package com.example.floresmurillo.jobquestorganizernavigation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by vanessaflores on 4/30/16.
 */
public class NotifyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Job Quest Organizer")
                .setContentText("Update your Job Quest!")
                .setSmallIcon(R.drawable.jqo_logo)
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(1, notification);
    }
}
