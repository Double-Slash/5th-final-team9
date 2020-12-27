package com.memoria.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.memoria.R;
import com.memoria.adapter.FragmentAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ArrayList<String> tabNames = new ArrayList<>();

    ViewPager viewpagerContent;
    TabLayout tabContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpagerContent = findViewById(R.id.viewPager);
        tabContent = findViewById(R.id.tab);
        tabContent.setupWithViewPager(viewpagerContent);

        loadTabName();
        setTabLayout();
        setViewPager();
        setupTabIcons();


    }

    @TargetApi(Build.VERSION_CODES.N)
    private void setTabLayout(){
        tabLayout = findViewById(R.id.tab);
        tabNames.stream().forEach(name ->tabLayout.addTab(tabLayout.newTab().setText(name)));
    }

    private void loadTabName(){
        tabNames.add("탭1");
        tabNames.add("탭2");
        tabNames.add("탭3");
        tabNames.add("탭4");
        tabNames.add("탭5");

    }

    private void setViewPager() {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setupTabIcons() {
        //아이콘설정
        View view1 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ImageView img1 = view1.findViewById(R.id.img_tab);
        TextView txt1 = view1.findViewById(R.id.txt_tab_name);
        img1.setImageResource(R.drawable.sample_tab);
        txt1.setText("1");

        View view2 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ImageView img2 = view2.findViewById(R.id.img_tab);
        TextView txt2 = view2.findViewById(R.id.txt_tab_name);
        img2.setImageResource(R.drawable.sample_tab);
        txt2.setText("2");

        View view3 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ImageView img3 = view3.findViewById(R.id.img_tab);
        TextView txt3 = view3.findViewById(R.id.txt_tab_name);
        img3.setImageResource(R.drawable.sample_tab);
        txt3.setText("3");

        View view4 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ImageView img4 = view4.findViewById(R.id.img_tab);
        TextView txt4 = view4.findViewById(R.id.txt_tab_name);
        img4.setImageResource(R.drawable.sample_tab);
        txt4.setText("4");

        View view5 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ImageView img5 = view5.findViewById(R.id.img_tab);
        TextView txt5 = view5.findViewById(R.id.txt_tab_name);
        img5.setImageResource(R.drawable.sample_tab);
        txt5.setText("5");


        tabContent.getTabAt(0).setCustomView(view1);
        tabContent.getTabAt(1).setCustomView(view2);
        tabContent.getTabAt(2).setCustomView(view3);
        tabContent.getTabAt(3).setCustomView(view4);
        tabContent.getTabAt(4).setCustomView(view5);
    }
}