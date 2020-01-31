package com.example.capturetheapp;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.rvalerio.fgchecker.AppChecker;

import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;

public class ForegroundWorker extends Worker {
    private AppChecker appChecker = new AppChecker();
    private String packageName;
    private OutputStreamWriter outputStreamWriter;
    private String previousPackageName;
    public Boolean flag;

    public ForegroundWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        previousPackageName = "";
        flag = true;
        try {
            outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("text.txt", Context.MODE_PRIVATE));
        } catch (Exception e) {
            Log.d("APP", "ForegroundWorker: " + e);
        }
    }

    @Override
    public Result doWork() {
        appChecker
                .whenAny(new AppChecker.Listener() {
                    @Override
                    public void onForeground(String packageName) {

                        //Log.d("APP", "onForeground: " + packageName + " " + previousPackageName);
                        if(packageName!=null) {
                            if (flag && packageName.equals("com.whatsapp")) {
                                flag = false;
                                Intent screenShotActivity = new Intent(getApplicationContext(), ScreenCaptureImageActivity.class);
                                screenShotActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(screenShotActivity);
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        flag = true;
                                    }
                                }, 121000);
                            }
                        }

                        setProgressAsync(new Data.Builder().putString("package", packageName).build());

                        previousPackageName = packageName;
                        ForegroundWorker.this.packageName = packageName;
                        try {
                            ForegroundWorker.this.outputStreamWriter.write(packageName + '\n');
                        } catch (Exception e) {
                            Log.e("APP", "run: ", e);
                        }
                    }
                })
                .timeout(2000)
                .start(getApplicationContext());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                appChecker.stop();
                try {
                    ForegroundWorker.this.outputStreamWriter.close();
                } catch (Exception e) {
                    Log.e("APP", "run: ", e);
                }
            }
        }, 890000);

        Data outputData = new Data.Builder()
                .putString(SyncStateContract.Constants._ID, packageName)
                .build();

        return Result.success(outputData);
    }
}
