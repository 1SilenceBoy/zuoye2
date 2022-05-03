package com.example.zuoye;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;

public class SettingFragment extends android.app.ListFragment{

  boolean dualPane;
  int curCheckPosition=0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, Data.titles));
        //获取布局文件中添加的FrameLayou帧布局管理器
        View detailFrame=getActivity().findViewById(R.id.detail);
        dualPane=detailFrame != null&&detailFrame.getVisibility() == View.VISIBLE;
        if(savedInstanceState != null){
            curCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
        if(dualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(curCheckPosition);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice",curCheckPosition);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }
    void showDetails(int Index){
        curCheckPosition = Index;
        if (dualPane){
            getListView().setItemChecked(Index, true);
            ResultFrag detail = (ResultFrag)getFragmentManager().findFragmentById(R.id.detail);
            if(detail == null || detail.getShownIndex() != Index){
                detail = ResultFrag.newInstance(Index);
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.detail, detail);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
    }
}
