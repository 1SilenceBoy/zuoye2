package com.example.zuoye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动service
        Intent service = new Intent(BackActivity.this, Myservice.class);
        BackActivity.this.startService(service);
    }

    //8.0以上手机需要添加此代码才能正常运行
    public void onResume() {
        super.onResume();
        finish();
    }

}
