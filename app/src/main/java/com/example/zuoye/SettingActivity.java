package com.example.zuoye;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String character = bundle.getString("character");
        Toast.makeText(SettingActivity.this, "传过来的值为:"+character, Toast.LENGTH_LONG).show();

    }
    public static  class DetailActivity extends Activity{

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.setting_details);
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                finish();
                return;
            }
            if(savedInstanceState == null){
                ResultFrag details = new ResultFrag();
                details.setArguments(getIntent().getExtras());
                getFragmentManager().beginTransaction().add(android.R.id.content,details).commit();
            }
        }
    }
}
