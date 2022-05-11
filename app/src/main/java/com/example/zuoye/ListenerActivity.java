package com.example.zuoye;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ListenerActivity extends Activity {
    private LinearLayout linearLayout;
    private ListenerReceiver listenerReceiver;
    private TextView textView0,textView1,textView2,textView3;
    int x = 0;
    String maxMemory = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        maxMemory = getResources().getString(R.string.current_memory);
        textView0 = new TextView(this);
        textView1 = new TextView(this);
        textView2 = new TextView(this);
        textView3 = new TextView(this);
        textView0.setText("预定最大分配内存为："+maxMemory);
        linearLayout.addView(textView0);
        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);

        listenerReceiver = new ListenerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.test.broadcast.MEMORY_CHANGE");
        intentFilter.setPriority(100);
        registerReceiver(listenerReceiver,intentFilter);
    }
    class ListenerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);

            float maxMemory1 = (float) (Runtime.getRuntime().maxMemory() * 1.0/ (1024 * 1024));
            textView1.setText("最大分配内存为:"+maxMemory1);
            float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0/ (1024 * 1024));
            textView2.setText("当前分配的总内存为:"+totalMemory);
            float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0/ (1024 * 1024));
            textView3.setText("剩余内存为:"+freeMemory);

            TextView textView = new TextView(ListenerActivity.this);
            textView.setText(x+"s系统剩余内存为：" + String.valueOf(memoryInfo.availMem / (1024 * 1024)) + "MB");
            linearLayout.addView(textView);
            if (x == 5){
                if (maxMemory.equals(String.valueOf(maxMemory1))){
                    textView0.setText("最大分配内存相同");
                }
            }
            try {
                Thread.sleep(1000);
                x++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            {
                if (x <= 5){
                    intent.setAction("com.test.broadcast.MEMORY_CHANGE");
                    context.sendOrderedBroadcast(intent,null);
                }else if(x == 600){
                    intent.setAction("com.test.broadcast.MEMORY_CHANGE");
                    context.sendOrderedBroadcast(intent,null);
                }
                else{
                    abortBroadcast();
                }
            };
        }
    }

}
