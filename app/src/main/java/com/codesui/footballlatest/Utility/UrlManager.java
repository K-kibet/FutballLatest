package com.codesui.footballlatest.Utility;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.activities.ConditionsActivity;

public class UrlManager {
    Context context;
    public UrlManager(Context myContext) {
        context = myContext;
    }
    public void moreApps() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(context.getString(R.string.more_apps_url)));
            intent.setPackage(context.getPackageName());
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(context.getString(R.string.more_apps_url)));
            context.startActivity(intent);
        }
    }

    public void openTerms() {
        Intent intent = new Intent(context, ConditionsActivity.class);
        context.startActivity(intent);
    }
}
