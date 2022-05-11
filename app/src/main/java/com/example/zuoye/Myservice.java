package com.example.zuoye;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Myservice extends Service {
    public Myservice() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("wp", "MyService onStartCommand");
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                int time = 0;
                while(true) {
                    Log.i("wp", "time:" + time);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time++;
                }
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }
}

