package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.memoria.R;

public class OptionFragment extends Fragment {

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_option, container, false);




        return view;
    }

}
