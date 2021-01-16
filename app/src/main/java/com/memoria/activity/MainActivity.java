package com.memoria.activity;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.memoria.R;
import com.memoria.fragment.CalendarFragment;
import com.memoria.fragment.MyMemoryFragment;
import com.memoria.fragment.MyTestFragment;
import com.memoria.fragment.MyWordFragment;
import com.memoria.fragment.OptionFragment;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNV;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MyWordFragment fragment1 = new MyWordFragment();
    private MyMemoryFragment fragment2 = new MyMemoryFragment();
    private MyTestFragment fragment3 = new MyTestFragment();
    private CalendarFragment fragment4 = new CalendarFragment();
    private OptionFragment fragment5 = new OptionFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNV = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment1).commitAllowingStateLoss();
        //바텀 네비게이션뷰 안의 아이템들 설정
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_1: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment1).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_2: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment2).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_3: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment3).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_4: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment4).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_5: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment5).commitAllowingStateLoss();
                        return true;
                    }
                    default: return false;
                }
            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, fragment).commit();
        // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }
}

