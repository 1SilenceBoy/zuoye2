package com.example.zuoye;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneActivity extends ListActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int[] list_image = {R.drawable.wifi, R.drawable.lanya, R.drawable.liuliang, R.drawable.liangdu
                , R.drawable.shengyin, R.drawable.tongzhi, R.drawable.mima, R.drawable.yinsi, R.drawable.anquan
                , R.drawable.dianchi, R.drawable.cunchu, R.drawable.gengxin};
        super.onCreate(savedInstanceState);
        String[] list_title = {"WLAN", "蓝牙", "移动网络", "显示和亮度", "声音和震动", "通知与状态栏", "指纹、面部与密码", "隐私"
                , "安全", "电池", "存储空间", "软件更新"};
        List<Map<String,Object>> item = new ArrayList<>();
        for (int i = 0; i < list_title.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("image",list_image[i]);
            map.put("title",list_title[i]);
            item.add(map);
        }
        SimpleAdapter ad = new SimpleAdapter(this, item, R.layout.phone, new String[]{ "image", "title"}, new int[]{R.id.item_image, R.id.item_title});
        this.setListAdapter(ad);
    }

}
