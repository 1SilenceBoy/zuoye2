package com.example.zuoye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Success extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ListView listView = (ListView) findViewById(R.id.lv1);
        int[] Id = new int[]{1, 2, 3, 4, 5};
        String[] title = new String[]{"进度条", "亮度", "连网", "手机设置", "记账本"};
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (int i = 0;i<Id.length;i++){
            Map<String,Object>map = new HashMap<>();
            map.put("id", Id[i]);
            map.put("title", title[i]);
            list.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,list, R.layout.main, new String[]{"title","id"}, new int[]{
                R.id.title2, R.id.title1
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(Success.this, Show.class);
                    startActivity(intent);
                }else if(i == 1){
                    Intent intent = new Intent(Success.this, SettingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("character", "1111");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(i == 2){
                    User user=new User();
                    user.setName("username");
                    user.setPwd("password");
                    Intent intent=new Intent();
                    intent.putExtra("int", 1);
                    intent.putExtra("byte", 0);
                    intent.putExtra("serializable", user);
                    intent.setClass(Success.this, IntentActivity.class);
                    startActivity(intent);
                }else if (i == 3){
                    Intent intent = new Intent();
                    intent.setClass(Success.this,PhoneActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent();
                    intent.setClass(Success.this,BookKeppingActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}
