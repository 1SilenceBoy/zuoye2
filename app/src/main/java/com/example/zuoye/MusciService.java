package com.example.zuoye;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MusciService extends Service {

    public final IBinder binder = new MyBinder();
    private String path;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        path = intent.getStringExtra("path");
        init();
        return binder;
    }
    public class MyBinder extends Binder{
        MusciService getService(){
            return MusciService.this;
        }
    }
    public static MediaPlayer mp = new MediaPlayer();

    public MusciService() {

    }
    public void init(){
        try {
            System.out.println("22222"+path);
            mp.setDataSource(path);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void play(){
        mp.start();
    }
    public void stop() throws IOException {
        if (mp != null){
            mp.stop();
            mp.prepare();
            mp.seekTo(0);
        }
    }

}
