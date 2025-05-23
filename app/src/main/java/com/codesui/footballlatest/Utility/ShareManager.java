package com.codesui.footballlatest.Utility;

import android.content.Context;
import android.content.Intent;

import com.codesui.footballlatest.R;

public class ShareManager {
    Context context;

    public ShareManager(Context context) {
        this.context = context;
    }

    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        String shareMessage = "\nGet Football Livescores In Real Time\n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + context.getPackageName() + "\n\n";
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }
}
