package com.example.mentalhealthco;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundWork extends Worker {
    private static final String TAG = "Background Worker";
        public BackgroundWork(
                @NonNull Context context,
                @NonNull WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {

            // Do the work here--in this case, upload the images.
           Log.e(TAG, "Doing some secret work in the background");

            // Indicate whether the work finished successfully with the Result
            return Result.success();
        }
    }


