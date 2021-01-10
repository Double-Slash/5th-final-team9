package com.memoria.activity.intro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.dbhelper.GoalDBHelper;
import com.memoria.modeldata.Goal;

import static android.content.Context.MODE_PRIVATE;


public class SampleSlide extends Fragment {
   private static final String ARG_LAYOUT_RES_ID = "layoutResId";
   private int layoutResId;

   public int goal;
   GoalDBHelper goalDBHelper;
   public SharedPreferences prefs;

   public static SampleSlide newInstance(int layoutResId){
      SampleSlide sampleSlide = new SampleSlide();

      Bundle args = new Bundle();
      args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
      sampleSlide.setArguments(args);

      return sampleSlide;
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState){
      super.onCreate(savedInstanceState);

      if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)){
         layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
      }
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view =inflater.inflate(layoutResId, container, false);
      EditText editText = view.findViewById(R.id.test_edit_intro);

      goalDBHelper = new GoalDBHelper(getContext());
      TextView pageNum = view.findViewById(R.id.page_num);
      String pageNumSting = pageNum.getText().toString();


      prefs = getActivity().getSharedPreferences("Pref", MODE_PRIVATE);
      boolean isFirstRun = prefs.getBoolean("isFirstRun", true);

      Goal goalDB = goalDBHelper.selectGoalByDate("나중에 날짜별로 조희");

      if (isFirstRun) {
         switch (pageNumSting) {
            case "1":
               editText.setText(String.valueOf(goalDB.getGoalWord()));
               break;
            case "2":
               editText.setText(String.valueOf(goalDB.getGoalMemory()));
               break;
            case "3":
               editText.setText(String.valueOf(goalDB.getGoalTest()));
               break;
            default:
               editText.setText(String.valueOf(goalDB.getGoalQuiz()));
               break;
         }
      }
      editText.addTextChangedListener(new TextWatcher() {

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 입력되는 텍스트에 변화가 있을 때
         }
         @Override
         public void afterTextChanged(Editable arg0) {
            // 입력이 끝났을 때
            String getEditGoal = editText.getText().toString();
            if (!TextUtils.isEmpty(getEditGoal)) goal = Integer.parseInt(getEditGoal);
            if ( goal > 100 && (pageNumSting.equals("3") || pageNumSting.equals("4")) ) {
               editText.setText("100");
               Toast.makeText(getContext(),"100%를 넘길 수 없어요!", Toast.LENGTH_SHORT).show();
            }
            switch (pageNumSting) {
               case "1":
                  goalDBHelper.updateGoalWord(goal);
                  break;
               case "2":
                  goalDBHelper.updateGoalMemory(goal);
                  break;
               case "3":
                  goalDBHelper.updateGoalTest(goal);
                  break;
               default:
                  goalDBHelper.updateGoalQuiz(goal);
                  break;
            }

         }
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 입력하기 전에
         }
      });
      return view;
   }
}
