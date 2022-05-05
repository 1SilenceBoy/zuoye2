package com.example.zuoye;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookKeppingActivity extends Activity {

    private final String[] columns = {"time","billname","money"};
    private Uri uri = Uri.parse("content://com.example.zuoye.bookKeeping");
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookkeeping);
        EditText billname=findViewById(R.id.billname);
        EditText money = findViewById(R.id.money);
        Button button = findViewById(R.id.submit);
        ListView bill= findViewById(R.id.bill);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = money.getText().toString();
                String s1=billname.getText().toString();
                list.clear();
                if (s != ""){
                    ContentResolver resolver = getContentResolver();
                    ContentValues contentValues = new ContentValues();

                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());

                    contentValues.put("time",formatter.format(date));
                    contentValues.put("billname",s1);
                    contentValues.put("money",s);
                    resolver.insert(uri,contentValues);
                    Cursor cursor = resolver.query(uri, null, null, null, null);
                    while (cursor.moveToNext()){
                        int columnIndex = cursor.getColumnIndex(columns[0]);
                        int columnIndex1 = cursor.getColumnIndex(columns[1]);
                        int columnIndex2=cursor.getColumnIndex(columns[2]);
                        String string = cursor.getString(columnIndex);
                        String string1 = cursor.getString(columnIndex1);
                        String string2=cursor.getString(columnIndex2);
                        list.add(string + string1+"   花费    "+string2);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BookKeppingActivity.this, android.R.layout.simple_list_item_1, list);
                    bill.setAdapter(arrayAdapter);
                    cursor.close();
                }
            }
        });
    }
}
