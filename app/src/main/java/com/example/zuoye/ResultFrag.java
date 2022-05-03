package com.example.zuoye;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class ResultFrag extends  android.app.Fragment {

    public static ResultFrag newInstance(int aIndex){
        ResultFrag resultFrag = new ResultFrag();
        Bundle bundle = new Bundle();
//        把aIndex作为参数存进bundle中
        bundle.putInt("aIndex", aIndex);
        resultFrag.setArguments(bundle);
        return resultFrag;
    }
    //    得到索引方法
    public int getShownIndex(){
        return getArguments().getInt("aIndex", 0);
    }
    //    创建一个文本视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null){
            return null;
        }
        ScrollView scrollView = new ScrollView(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setPadding(10,10,10,10);
        scrollView.addView(textView);
        textView.setText(Data.detail[getShownIndex()]);
        return scrollView;
    }

}
