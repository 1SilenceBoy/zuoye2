package com.example.zuoye;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class IntentActivity extends Activity {

    private NetworkChange networkChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentshow);
        Intent intent = getIntent();
        int a = intent.getIntExtra("int",0);
        byte b = intent.getByteExtra("byte", (byte) 1);
        User user =(User) intent.getParcelableExtra("serializable");

        TextView textView1 = findViewById(R.id.textview1);
        TextView textView2 = findViewById(R.id.textview2);
        TextView textView3 = findViewById(R.id.textview3);
        textView1.setText("int值为:"+a);
        textView2.setText("byte值为:"+b);
        textView3.setText("用户名："+user.getName()+"密码："+user.getPwd());

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChange = new NetworkChange();
        registerReceiver(networkChange,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChange);
    }

    class NetworkChange extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();//获取网络状态
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                Toast.makeText(IntentActivity.this, "已连网！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(IntentActivity.this, "已断网！", Toast.LENGTH_LONG).show();
            }
        }
    }
}
