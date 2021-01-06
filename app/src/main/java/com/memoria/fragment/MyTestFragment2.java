package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.adapter.GridViewAdapter;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.decorator.GridItemView;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;
import java.util.List;

public class MyTestFragment2 extends Fragment {

    View view;
    Button start2;
    TextView comment;
    public static final int REQUEST_CODE = 100;

    private MyWordDBHelper myWordDBHelper;

    private List<String> groups = new ArrayList<>();
    private ArrayList<String> selectedStrings;


    ArrayList<MyWord> myWords = new ArrayList<>();
    GridViewAdapter adapter;
    GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test2, container, false);

        myWordDBHelper = new MyWordDBHelper(getContext());
        myWords = myWordDBHelper.selectWordGroupList();

        for(MyWord element:myWords){
            groups.add(element.getGroupName());
        }
        start2=view.findViewById(R.id.btn_test_start2);

        selectedStrings = new ArrayList<>();

        gridView = view.findViewById(R.id.test_grid_list2);
        adapter = new GridViewAdapter(groups ,getActivity());
        gridView.setAdapter(adapter);

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

        return view;
    }
}
