package com.example.zuoye;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class Show extends Activity {

    final private  int ImageId[]=new int[]{R.drawable.a,R.drawable.b,R.drawable.c};
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);//显示水平进度条
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.show);
        linearLayout=(LinearLayout)findViewById(R.id.linear);
        new MyTack().execute();
    }

    class MyTack extends AsyncTask<Void,Integer,LinearLayout>{

        @Override
        protected void onPreExecute() {
            setProgressBarVisibility(true);
            setProgressBarIndeterminateVisibility(true);
            super.onPreExecute();
        }

        @Override
        protected LinearLayout doInBackground(Void... voids) {
            LinearLayout ll=new LinearLayout(Show.this);
            for (int i=0;i<3;i++){
                ImageView iv=new ImageView(Show.this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(245,108));
                iv.setImageResource(ImageId[i]);
                ll.addView(iv);
                iv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        android.app.AlertDialog dialog=new android.app.AlertDialog.Builder(Show.this).create();
                        dialog.setTitle("删除？");
                        dialog.setMessage("确认删除嘛？");
                        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int p) {
                            }
                        });
                        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int p) {

                            }
                        });
                        dialog.show();
                        return true;
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i+1);
            }
            return ll;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            setProgress(values[0]*2500);
        }

        @Override
        protected void onPostExecute(LinearLayout result) {
            setProgressBarVisibility(false);
            linearLayout.addView(result);
            super.onPostExecute(result);

        }
    }
}



