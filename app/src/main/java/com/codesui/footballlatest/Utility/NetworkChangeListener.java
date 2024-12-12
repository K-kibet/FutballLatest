package com.codesui.footballlatest.Utility;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import com.codesui.footballlatest.R;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectedToInternet(context)) {
            showNoInternetDialog(context, intent);
        } else {
            // Handle network available case
        }
    }

    private void showNoInternetDialog(final Context context, final Intent intent) {
        try {
            // Create an AlertDialog for UI contexts (like Activity)
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialogue, null);
            builder.setView(layout_dialog);

            AppCompatButton btnRetry = layout_dialog.findViewById(R.id.btnRetry);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRetry.setOnClickListener(view -> {
                dialog.dismiss();
                onReceive(context, intent); // Retry network check
            });

        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to notification if AlertDialog fails
            showNetworkIssueNotification(context);
        }
    }

    private void showNetworkIssueNotification(Context context) {
        // Create a notification channel for Android 8.0 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "network_channel", "Network Issues", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create the notification
        Notification notification = new NotificationCompat.Builder(context, "network_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("No Internet Connection")
                .setContentText("Please check your network connection.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build();

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);  // Notification ID: 1
    }
}
