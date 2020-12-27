package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.adapter.MemoryListAdapter;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.modeldata.MyMemory;

import java.util.ArrayList;

public class MyMemoryFragment extends Fragment {

    View view;

    //DB관련
    private MyMemoryDBHelper myMemoryDBHelper;

    public MyMemoryFragment() {
        // Required empty public constructor
    }

    public static MyMemoryFragment newInstance() {
        MyMemoryFragment fragment = new MyMemoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mymemory, container, false);

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

        ArrayList<MyMemory> myMemoryArrayList = myMemoryDBHelper.selectAllMemoryList();

        ListView listView = view.findViewById(R.id.memory_list);
        MemoryListAdapter memoryListAdapter = new MemoryListAdapter(getContext(), 0, myMemoryArrayList);

        listView.setAdapter(memoryListAdapter);


        return view;
    }

}