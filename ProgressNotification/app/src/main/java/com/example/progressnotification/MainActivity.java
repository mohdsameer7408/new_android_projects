package com.example.progressnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final String channel_id = "personal notification";
    public static final int notification_id = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNotification(View view) {
        createNotificationChannel();

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_id);
        builder.setSmallIcon(R.drawable.ic_download);
        builder.setContentTitle("Image Download");
        builder.setContentText("Download in progress");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        final int max_progress = 100;
        int initial_progress = 0;

        builder.setProgress(max_progress, initial_progress, false);

        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(notification_id, builder.build());

        Thread thread = new Thread() {
            @Override
            public void run() {
                int count = 0;
                try {
                    while (count <= 100) {
                        count += 10;
                        sleep(1000);

                        builder.setProgress(max_progress, count, false);
                        notificationManagerCompat.notify(notification_id, builder.build());

                    }

                    builder.setContentText("Download Complete");
                    builder.setProgress(0, 0, false);
                    notificationManagerCompat.notify(notification_id, builder.build());
                } catch (InterruptedException e) {}

            }
        };

        thread.start();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "personal notification";
            String description = "include all personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(channel_id, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}