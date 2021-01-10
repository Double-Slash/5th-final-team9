package com.memoria.activity.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.memoria.R;
import com.memoria.activity.MainActivity;

public class GoalSettingActivity extends AppIntro {

    Fragment f1;
    Fragment f2;
    Fragment f3;
    Fragment f4;
    public SharedPreferences prefs;

    boolean isFirstRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        isFirstRun = prefs.getBoolean("isFirstRun", false);
        f1 = SampleSlide.newInstance(R.layout.goal_setting_word);
        f2 = SampleSlide.newInstance(R.layout.goal_setting_mymemory);
        f3 = SampleSlide.newInstance(R.layout.goal_setting_test);
        f4 = SampleSlide.newInstance(R.layout.goal_setting_quiz);
        addSlide(f1);
        addSlide(f2);
        addSlide(f3);
        addSlide(f4);

        int color_mint= getColor(R.color.main_mint);
        int color_white= getColor(R.color.white);
        // OPTIONAL METHODS
        // Override bar/separator color.
        setNextArrowColor(color_mint);
        setColorSkipButton(getColor(R.color.gray));
        setImageNextButton(getDrawable(R.drawable.btn_next));
        setBarColor(color_white);
        setDoneText("STRAT");
        setColorDoneText(color_mint);
        setIndicatorColor(color_mint, getColor(R.color.black));
        setSeparatorColor(color_white);

        // Hide Skip/Done button.
        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void startActivity() {
        if (!isFirstRun){
            Intent intent = new Intent(this, MainActivity.class);
            prefs.edit().putBoolean("isFirstRun", true).apply();
            startActivity(intent);
        }
        finish();
    }

    //edittext제외한 여백 클릭시 포커스해제(키보드내려감)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}