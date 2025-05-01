package com.codesui.footballlatest.Utility;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.codesui.footballlatest.R;

public class ApiPollingService extends Service {

    private static final String API_CHANNEL_ID = "api_channel";
    private static final String USER_CHANNEL_ID = "user_channel";

    private Handler handler = new Handler();
    private Runnable task;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Foreground service notification
        Notification notification = new NotificationCompat.Builder(this, API_CHANNEL_ID)
                .setContentTitle("Football API Polling")
                .setContentText("Checking for updates every 10 seconds")
                .setSmallIcon(R.drawable.ic_football)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        startForeground(1, notification);

        // Repeated task
        task = new Runnable() {
            @Override
            public void run() {
                queryApi();
                handler.postDelayed(this, 10000); // 10 seconds
            }
        };
        handler.post(task);

        return START_STICKY;
    }

    private void queryApi() {
        // TODO: Replace with actual API call logic
        String result = "New football update available!";

        // Notify the user with result
        sendUserNotification(result);
    }

    private void sendUserNotification(String message) {
        // For API 33+ (Android 13+), check runtime notification permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission("android.permission.POST_NOTIFICATIONS") != PackageManager.PERMISSION_GRANTED) {
                return; // Permission not granted; skip sending the notification
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "user_channel")
                .setSmallIcon(R.drawable.ic_epl_banner)
                .setContentTitle("New Match Update")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Ensure the channel exists for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "user_channel",
                    "User Alerts",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }



    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel apiChannel = new NotificationChannel(
                    API_CHANNEL_ID,
                    "API Polling",
                    NotificationManager.IMPORTANCE_LOW
            );
            apiChannel.setDescription("Service notification for continuous background polling");

            NotificationChannel userChannel = new NotificationChannel(
                    USER_CHANNEL_ID,
                    "User Alerts",
                    NotificationManager.IMPORTANCE_HIGH
            );
            userChannel.setDescription("Notifies users of new API data");

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(apiChannel);
                manager.createNotificationChannel(userChannel);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(task);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
