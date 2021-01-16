package com.memoria.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.memoria.R;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends ArrayAdapter<MyWord> {

    private ArrayList<MyWord> items;
    private boolean deleteVisible;
    public List<Integer> selectedPositions;
    Dialog guideDilaog; // 커스텀 다이얼로그

    MyWordDBHelper myWordDBHelper;

    public GridAdapter(Context context, int textViewResourceId, ArrayList<MyWord> objects, boolean deleteVisible) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.deleteVisible = deleteVisible;
        selectedPositions = new ArrayList<>();

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        myWordDBHelper = new MyWordDBHelper(getContext());

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.grid_item_myword, null);
        }

        if (deleteVisible){
            ImageButton deleteXBtn = v.findViewById(R.id.delete_x_btn);
            deleteXBtn.setVisibility(View.VISIBLE);
        }
        ImageButton deleteXBtn = v.findViewById(R.id.delete_x_btn);

        deleteXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });

        TextView textView = v.findViewById(R.id.group_name);
        textView.setText(items.get(position).getGroupName());

        return v;
    }

    public void showDialog(int position){

        guideDilaog = new Dialog(getContext());
        guideDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        guideDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        guideDilaog.setContentView(R.layout.dialog_warning);
        guideDilaog.show();

        TextView warnigText = guideDilaog.findViewById(R.id.warning_txt);
        warnigText.setText( items.get(position).getGroupName()+ " 그룹의 \n모든 단어가 삭제됩니다.");

        Button cancelBtn = guideDilaog.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideDilaog.dismiss();
            }
        });

        Button okBtn = guideDilaog.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWordDBHelper.deleteGroub(items.get(position).getGroupName());
                items.remove(position);
                notifyDataSetChanged();
                guideDilaog.dismiss();
            }
        });
    }
}