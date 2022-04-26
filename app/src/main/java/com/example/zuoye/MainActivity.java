package com.example.zuoye;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText username;
    EditText password;
    private SharedPreferences loginPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username =(EditText)findViewById(R.id.UserName);
        password=(EditText) findViewById(R.id.Password);
        Button bt=(Button) findViewById(R.id.button);
        loginPreference = getSharedPreferences("login", MODE_PRIVATE);
        String saved_username = loginPreference.getString("USERNAME", "");
        String saved_password = loginPreference.getString("PASSWORD","");
        username.setText(saved_username);
        password.setText(saved_password);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = loginPreference.edit();
                //新建一个Editor对象来存储键值对用户名和密码
                editor.putString("USERNAME", username.getText().toString());
                editor.putString("PASSWORD",password.getText().toString());
                //提交数据
                editor.commit();
                Intent intent=new Intent(MainActivity.this,Success.class);
                startActivity(intent);
            }
        });
    }

}