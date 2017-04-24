package com.gavinfinancialgroup.googleplacesapi.JobService;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.gavinfinancialgroup.googleplacesapi.Activities.Next5Activity;

/**
 * Created by zhang on 4/22/2017.
 */

public class JobSchedulerService extends JobService {

    private static final int NOTIFICATION_ID = 1;
    @Override
    public boolean onStartJob(JobParameters params) {
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        createNotification(android.R.drawable.alert_dark_frame
                , "Broadcast Notification", "Next 5 days notification"
                , NOTIFICATION_ID, flags);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public void createNotification(int resID, String title, String text, int mID, int flags) {
        int nRequestCode = 0;
        //int sRequestCode = 1;
        Intent nIntent = new Intent(this, Next5Activity.class);
        //Intent sIntent = new Intent(this, ShareActivity.class);
        PendingIntent nPendingIntent = PendingIntent.getActivity(this, nRequestCode, nIntent, flags);
        //PendingIntent sPendingIntent = PendingIntent.getActivity(this, sRequestCode, sIntent, flags);
        Notification notification  = new NotificationCompat.Builder(this)
                .setSmallIcon(resID)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(nPendingIntent)
                .addAction(android.R.drawable.arrow_up_float, "Open", nPendingIntent)
//                .addAction(android.R.drawable.arrow_down_float, "Cancel", )
                .setAutoCancel(true)
                .build();
        @SuppressLint("ServiceCast")
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(mID, notification);
    }
}
