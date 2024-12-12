package com.codesui.footballlatest.Utility;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

public class MyApplication extends Application {

    private NetworkChangeListener networkChangeListener;

    @Override
    public void onCreate() {
        super.onCreate();
        networkChangeListener = new NetworkChangeListener();
        // Register the receiver only if the application context is ready
        try {
            registerReceiver(networkChangeListener, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception to understand the crash
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // Unregister the receiver to avoid memory leaks
        if (networkChangeListener != null) {
            try {
                unregisterReceiver(networkChangeListener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
