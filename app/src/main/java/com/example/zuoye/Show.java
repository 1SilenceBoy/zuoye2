package com.example.zuoye;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Show extends Activity {

    final private  int ImageId[] = new int[]{R.drawable.a, R.drawable.b, R.drawable.c};
    String[] title = {"图片1", "图片2", "图片3"};
    ArrayList<Map<String, Object>> list = new ArrayList<>();
    private ProgressBar progressBar;
    private GridView gridView;
    private SimpleAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.show);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
        progressBar = findViewById(R.id.bar);
        gridView = findViewById(R.id.gridview);
        new MyTack().execute();
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Show.this);
                builder.setTitle("是否删除？");
                builder.setMessage("是否真的要删除嘛？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }
    class MyTack extends AsyncTask<Void,Integer,SimpleAdapter>{
        @Override
        protected SimpleAdapter doInBackground(Void... params) {
            for (int i = 0; i < ImageId.length; i++) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("imageview",ImageId[i]);
                map.put("titleview",title[i]);
                try {
                    list.add(map);
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            adapter = new SimpleAdapter(Show.this,
                    list, R.layout.items, new String[]{"titleview", "imageview"}, new int[]{R.id.titleview, R.id.imageview});
            return adapter;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]*40);
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(SimpleAdapter result) {
            progressBar.setVisibility(View.GONE);
            gridView.setAdapter(result);
            super.onPostExecute(result);
        }
    }

}



