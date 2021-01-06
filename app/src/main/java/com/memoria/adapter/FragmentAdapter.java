package com.memoria.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.memoria.fragment.CalendarFragment;
import com.memoria.fragment.MyMemoryFragment;
import com.memoria.fragment.MyWordFragment;
import com.memoria.fragment.OptionFragment;
import com.memoria.fragment.MyTestFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) { super(fm); }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MyWordFragment.newInstance();
            case 1:
                return MyMemoryFragment.newInstance();
            case 2:
                return MyTestFragment.newInstance();
            case 3:
                return CalendarFragment.newInstance();
            case 4:
                return OptionFragment.newInstance();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 5;
    }
}
