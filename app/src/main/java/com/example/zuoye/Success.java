package com.example.zuoye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Success extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ListView listView=(ListView) findViewById(R.id.lv1);
        int[] Id=new int[]{1,2,3,4,5};
        String[] title=new String[]{"Sam","Dam","Lili","Jack","Yoh"};
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for (int i=0;i<Id.length;i++){
            Map<String,Object>map =new HashMap<>();
            map.put("id",Id[i]);
            map.put("title",title[i]);
            list.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.main,new String[]{"title","id"},new int[]{
                R.id.title2,R.id.title1
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent=new Intent(Success.this,Show.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Success.this,"你点击了第"+(i+1)+"项",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
