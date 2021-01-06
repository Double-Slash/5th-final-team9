package com.memoria.service;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;


class CheckableLinearLayout extends LinearLayout implements Checkable {

    private boolean mIsChecked ;

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void setChecked(boolean b) {


    }

    @Override
    public boolean isChecked() {
        return mIsChecked ;
    }

    @Override
    public void toggle() {
        setChecked(mIsChecked ? false : true) ;
    }
}
