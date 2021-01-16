package com.memoria.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.memoria.R;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.modeldata.MyMemory;

import java.util.ArrayList;

public class MemoryListAdapter extends ArrayAdapter<MyMemory> {

    private ArrayList<MyMemory> items;
    private boolean deleteVisible;

    Dialog guideDilaog; // 커스텀 다이얼로그
    MyMemoryDBHelper myMemoryDBHelper;


    public MemoryListAdapter(Context context, int textViewResourceId, ArrayList<MyMemory> objects, boolean deleteVisible) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.deleteVisible = deleteVisible;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        myMemoryDBHelper = new MyMemoryDBHelper(getContext());

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_mymemory, null);
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

        TextView tvName = v.findViewById(R.id.memory_text);
        tvName.setText(items.get(position).getEnglishMemory());

        return v;
    }

    public void showDialog(int position){

        guideDilaog = new Dialog(getContext());
        guideDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        guideDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        guideDilaog.setContentView(R.layout.dialog_warning);
        guideDilaog.show();

        TextView warnigText = guideDilaog.findViewById(R.id.warning_txt);
        String memory = items.get(position).getEnglishMemory();
        memory = memory.length()<10 ? items.get(position).getEnglishMemory() : items.get(position).getEnglishMemory().substring(0,10)+"...";
        warnigText.setText(  memory+ " 해당 문장을 삭제합니다.");

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
                myMemoryDBHelper.deleteMemory(items.get(position).getEnglishMemory());
                items.remove(position);
                notifyDataSetChanged();
                guideDilaog.dismiss();
            }
        });
    }
}
