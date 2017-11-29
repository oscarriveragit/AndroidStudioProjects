package net.aibarra.skiing.Analitica;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import net.aibarra.skiing.R;

import static com.google.android.gms.analytics.internal.zzy.v;

/**
 * Created by cice on 23/11/17.
 */



public class AnalyticsApplication extends Application {
    private Tracker mTracker;

    Button button;


    /*
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);




        }
        return mTracker;

    }




}




