package com.memoria.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.activity.WordGroupAddActivity;
import com.memoria.activity.WordListActivity;
import com.memoria.adapter.GridAdapter;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;

public class MyWordFragment extends Fragment {

    View view;
    ImageButton addBtn;

    public static final int REQUEST_CODE = 100;

    //DB관련
    private MyWordDBHelper myWordDBHelper;

    ArrayList<MyWord> myWords = new ArrayList<>();
    GridAdapter adapter;
    GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myword, container, false);


        //샘플데이터
        MyWord myWord = new MyWord();
        myWord.setGroupName("그룹1");
        myWord.setEnglishWord("banana");
        myWord.setKoreanWord("바나나");
        myWord.setDate("2020-12-27");
//        myWords.add(myWord);

        MyWord myWord2 = new MyWord();
        myWord2.setGroupName("그룹1");
        myWord2.setEnglishWord("apple");
        myWord2.setKoreanWord("사과");
        myWord2.setDate("2020-12-28");
//        myWords.add(myWord2);

        MyWord myWord3 = new MyWord();
        myWord3.setGroupName("그룹2");
        myWord3.setEnglishWord("Hi");
        myWord3.setKoreanWord("안녕");
        myWord3.setDate("2020-12-27");
//        myWords.add(myWord3);

        MyWord myWord4 = new MyWord();
        myWord4.setGroupName("그룹3");
        myWord4.setEnglishWord("kimsein");
        myWord4.setKoreanWord("김세인");
        myWord4.setDate("2020-12-26");
//        myWords.add(myWord4);

        ////////////

        myWordDBHelper = new MyWordDBHelper(getContext());
        myWords = myWordDBHelper.selectWordGroupList();

        gridView = view.findViewById(R.id.word_grid_list);
        adapter = new GridAdapter(getActivity(), 0, myWords);

        addBtn = view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WordGroupAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WordListActivity.class);
                intent.putExtra("groupName", myWords.get(position).getGroupName());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
//            String returnGroupName = data.getExtras().getString("groupAddName");
            myWords = myWordDBHelper.selectWordGroupList();
            adapter = new GridAdapter(getActivity(), 0, myWords);
            gridView.setAdapter(adapter);

        }
    }

}
