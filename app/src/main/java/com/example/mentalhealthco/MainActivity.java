package com.example.mentalhealthco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.usage.UsageStats;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.app.usage.UsageStatsManager;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG,"the app has started!");

        //gatherData();

        getTopActivtyFromLolipopOnwards();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();

        WorkRequest myWorkRequest =
                new OneTimeWorkRequest.Builder(BackgroundWork.class)
                        .setConstraints(constraints)
                        .build();

         Log.e(TAG, "hello" + myWorkRequest.toString());

        WorkManager.getInstance(getApplicationContext()).enqueue(myWorkRequest);
    }

    public void gatherData(){

        //UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService("");
        //Map<String, UsageStats> data = mUsageStatsManager.queryAndAggregateUsageStats(11, 12);

    }

    public void getTopActivtyFromLolipopOnwards() {

        String topPackageName;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();

            Log.e(TAG, ""+time);
            // We get usage stats for the last 1000 seconds
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 100 * 10, time);
            // Sort the stats by the last time used

            if (stats != null) {
                SortedMap < Long, UsageStats > mySortedMap = new TreeMap < Long, UsageStats > ();
                for (UsageStats usageStats: stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }



                for(Long val : mySortedMap.keySet()){
                   Log.e(TAG, ""+mySortedMap.get(val).getPackageName());
                }


                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    Log.e("TopPackage Name", topPackageName);
                }
            }

        }
    }


}