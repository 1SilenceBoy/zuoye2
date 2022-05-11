package com.example.zuoye;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ResourceBundle;

public class ListenerReceiver extends BroadcastReceiver {
    private static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION)){
            Toast.makeText(context,"开机自启动", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(context, BackActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        }else {
            Toast.makeText(context,"开机自启动失败", Toast.LENGTH_SHORT).show();
        }
    }


}
