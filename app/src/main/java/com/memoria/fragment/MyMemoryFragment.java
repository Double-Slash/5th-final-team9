package com.memoria.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.activity.MemoryAddActivity;
import com.memoria.adapter.GridAdapter;
import com.memoria.adapter.MemoryListAdapter;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.modeldata.MyMemory;

import java.util.ArrayList;

public class MyMemoryFragment extends Fragment {

    View view;
    ImageButton addBtn;
    TextView editBtn;
    TextView completeBtn;
    boolean delVisible =false;

    public static final int REQUEST_CODE = 100;

    ArrayList<MyMemory> myMemoryArrayList;
    ListView listView;
    MemoryListAdapter memoryListAdapter;

    //DB관련
    private MyMemoryDBHelper myMemoryDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_mymemory, container, false);

        myMemoryDBHelper = new MyMemoryDBHelper(getContext());
        MyMemory myMemory= new MyMemory();
        myMemory.setEnglishMemory("The ultimate measure of a person is not where they\n" +
                "stand in moments of comfort and convenience, but\n" +
                "where they stand in times of challenge and controve\n" +
                "rsy.");
        myMemory.setDate("2020-12-28");

        MyMemory myMemory2= new MyMemory();
        myMemory2.setEnglishMemory("I haven’t achieved very much today.");
        myMemory2.setDate("2020-12-27");

        MyMemory myMemory3= new MyMemory();
        myMemory3.setEnglishMemory("Everything is just peachy.");
        myMemory3.setDate("2020-12-27");


        MyMemory myMemory4= new MyMemory();
        myMemory4.setEnglishMemory("put up with hardship");
        myMemory4.setDate("2020-12-27");
//        myMemoryDBHelper.insertMemory(myMemory);
//        myMemoryDBHelper.insertMemory(myMemory2);
//        myMemoryDBHelper.insertMemory(myMemory3);
//        myMemoryDBHelper.insertMemory(myMemory4);

        addBtn = view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MemoryAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        editBtn = view.findViewById(R.id.edit_btn);
        completeBtn = view.findViewById(R.id.complete_btn);

        View.OnClickListener onEditCompleteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.edit_btn) setEditBtn();
                else if (v.getId()==R.id.complete_btn) setCompleteBtn();
                memoryListAdapter = new MemoryListAdapter(getActivity(), 0, myMemoryArrayList, delVisible);
                listView.setAdapter(memoryListAdapter);
            }
        };

        if (delVisible) setEditBtn();
        else setCompleteBtn();

        editBtn.setOnClickListener(onEditCompleteClickListener);
        completeBtn.setOnClickListener(onEditCompleteClickListener);

        myMemoryArrayList = myMemoryDBHelper.selectAllMemoryList();

        listView = view.findViewById(R.id.memory_list);
        memoryListAdapter = new MemoryListAdapter(getContext(), 0, myMemoryArrayList, delVisible);

        listView.setAdapter(memoryListAdapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            myMemoryArrayList = myMemoryDBHelper.selectAllMemoryList();
            memoryListAdapter = new MemoryListAdapter(getActivity(), 0, myMemoryArrayList, delVisible);
            listView.setAdapter(memoryListAdapter);

        }
    }

    private void setEditBtn(){
        delVisible = true;
        editBtn.setVisibility(View.GONE);
        completeBtn.setVisibility(View.VISIBLE);
        addBtn.setEnabled(false);
    }

    private void setCompleteBtn(){
        delVisible = false;
        editBtn.setVisibility(View.VISIBLE);
        completeBtn.setVisibility(View.GONE);
        addBtn.setEnabled(true);
    }
}