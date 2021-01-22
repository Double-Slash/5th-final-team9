package com.memoria.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.memoria.R;
import com.memoria.activity.MainActivity;
import com.memoria.activity.TestActivity;
import com.memoria.adapter.GridViewAdapter;
import com.memoria.dbhelper.MyTestDBHelper;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.decorator.GridItemView;
import com.memoria.modeldata.MyTest;
import com.memoria.modeldata.MyWord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyLockTestFragment extends Fragment {
    View view;
    Button start2;
    private MyTestDBHelper myTestDBHelper;

    private MyWordDBHelper myWordDBHelper;

    OptionFragment fragment2;



    private List<String> groups = new ArrayList<>();
    private ArrayList<String> selectedStrings;


    ArrayList<MyWord> myWords = new ArrayList<>();
    GridViewAdapter adapter;
    GridView gridView;
    String GroupName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test2, container, false);

        fragment2=new OptionFragment();

        myWordDBHelper = new MyWordDBHelper(getContext());
        myWords = myWordDBHelper.selectWordGroupList();

        for(MyWord element:myWords){
            groups.add(element.getGroupName());
        }
        start2=view.findViewById(R.id.btn_test_start2);

        selectedStrings = new ArrayList<>();

        MyTest mytest = new MyTest();
        myTestDBHelper = new MyTestDBHelper(getContext());

        gridView = view.findViewById(R.id.test_grid_list2);
        adapter = new GridViewAdapter(groups ,getActivity());
        gridView.setAdapter(adapter);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdfNow.format(date);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int selectedIndex = adapter.selectedPositions.indexOf(position);
                if (selectedIndex > -1) {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((GridItemView) v).display(false);
                    selectedStrings.remove((String) parent.getItemAtPosition(position));
                } else {
                    adapter.selectedPositions.add(position);
                    ((GridItemView) v).display(true);
                    selectedStrings.add((String) parent.getItemAtPosition(position));
                }
                if(selectedStrings.size() ==0){
                    start2.setVisibility(View.INVISIBLE);
                }
                else{
                    start2.setVisibility(View.VISIBLE);
                }
            }
        });
        start2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for (String element : selectedStrings){
                    GroupName=GroupName+element+",";
                }
                GroupName = GroupName.substring(4, GroupName.length()-1);
                mytest.setGroup(GroupName);
                mytest.setTotal(0);
                mytest.setStatus("lock");
                mytest.setCorrect(0);
                mytest.setPercent(0);
                mytest.setDate(formatDate);
                myTestDBHelper.insertScore(mytest);
                ((MainActivity)getActivity()).replaceFragment(fragment2);

            }
        });

        return view;
    }
}
