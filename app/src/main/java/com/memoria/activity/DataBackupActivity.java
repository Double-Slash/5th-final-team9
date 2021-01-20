package com.memoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.memoria.R;
import com.memoria.dbhelper.GoalDBHelper;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.Goal;
import com.memoria.modeldata.MyMemory;
import com.memoria.modeldata.MyWord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataBackupActivity extends AppCompatActivity {

    private EditText editmail;
    private  EditText editpw;
    private EditText checkpw;
    private Button backupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_backup);

        if(android.os.Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        editmail = findViewById(R.id.editmail);
        editpw = findViewById(R.id.editpw);
        checkpw = findViewById(R.id.checkpw);
        backupButton = findViewById(R.id.backupButton);

        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("클릭","클릭");
                if (editpw.getText().toString().equals(checkpw.getText().toString())) {
                    Log.d("editpw",editpw.getText().toString());
                    Log.d("checkpw",checkpw.getText().toString());
                    try {
                        URL url = new URL("http://221.167.222.53:3001/data");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                        httpURLConnection.setDefaultUseCaches(false);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setRequestProperty("Accept", "application/json");
                        httpURLConnection.setRequestProperty("Content-type", "application/json");

                        //보낼 JSONObject 생성
                        JSONObject jsonObject = new JSONObject();
                        JSONObject dataObject = new JSONObject();

                        //Word
                        JSONObject wordObject = new JSONObject();
                        JSONArray wordArray = new JSONArray();
                        MyWordDBHelper myWordDBHelper = new MyWordDBHelper(DataBackupActivity.this);
                        ArrayList<MyWord> wordArrayList = myWordDBHelper.selectAllDataList();
                        for (MyWord m : wordArrayList){
                            wordObject.put("wGroupName",m.getGroupName());
                            wordObject.put("wEnglishWord",m.getEnglishWord());
                            wordObject.put("wKoreanWord",m.getKoreanWord());
                            wordObject.put("wDate",m.getDate());
                            wordArray.put(wordObject);
                        }
                        System.out.println(wordArray);

                        //Memory
                        JSONObject memoryObject = new JSONObject();
                        JSONArray memoryArray = new JSONArray();
                        MyMemoryDBHelper mymemoryDBHelper = new MyMemoryDBHelper(DataBackupActivity.this);
                        ArrayList<MyMemory> mymemoryArrayList = mymemoryDBHelper.selectAllDataList();
                        for (MyMemory m : mymemoryArrayList){
                            memoryObject.put("mEnglishMemory",m.getEnglishMemory());
                            memoryObject.put("mDate",m.getDate());
                            memoryArray.put(memoryObject);
                        }
                        System.out.println(memoryArray);

                        //Goal
                        JSONObject goalObject = new JSONObject();
                        JSONArray goalArray = new JSONArray();
                        GoalDBHelper goalDBHelper = new GoalDBHelper(DataBackupActivity.this);
                        ArrayList<Goal> goalArrayList = goalDBHelper.selectAllData();
                        for (Goal g : goalArrayList){
                            goalObject.put("gGoalWord",g.getGoalWord());
                            goalObject.put("gGoalMemory",g.getGoalMemory());
                            goalObject.put("gGoalTest",g.getGoalTest());
                            goalObject.put("gGoalQuiz",g.getGoalQuiz());
                            goalObject.put("gAchieveWord", g.getAchieveWord());
                            goalObject.put("gAchieveMemory", g.getAchieveMemory());
                            goalObject.put("gAchieveTest", g.getAchieveTest());
                            goalObject.put("gAchieveQUiz", g.getAchieveQuiz());
                            goalObject.put("gDate", g.getDate());
                            goalArray.put(goalObject);
                        }
                        System.out.println(goalArray);

                        dataObject.put("word",wordArray);
                        dataObject.put("memory",memoryArray);
                        dataObject.put("goal",goalArray);

                        jsonObject.accumulate("password", editpw.getText().toString());
                        jsonObject.accumulate("data", dataObject.toString());
                        System.out.println(jsonObject);

                        Log.d("성공3","성공3t");

                        String json = jsonObject.toString();
                        Log.d("성공4","성공4t");

                        OutputStream os = null;
                        os = httpURLConnection.getOutputStream();
                        System.out.println(json);
                        Log.d("성공41",os.toString());
                        os.write(json.getBytes("euc-kr"));
                        os.flush();
                        os.close();
                        Log.d("성공5", "성공5t");

                        InputStream is = null;
                        try{
                            int reponseCode = httpURLConnection.getResponseCode();
                            Log.d("서버 응답", Integer.toString(reponseCode));
                            BufferedReader buf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            String inputLine;
                            StringBuffer reponse = new StringBuffer();
                            while ((inputLine = buf.readLine()) != null){
                                reponse.append(inputLine);
                            }
                            buf.close();
                            Log.d("Message:", reponse.toString());
                        }finally {
                            httpURLConnection.disconnect();
                        }
                    } catch (MalformedURLException e) {
                            Log.d("MalformedURLException e","MalformedURLException e");
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.d("IOException e","IOException e");
                            e.printStackTrace();
                        } catch (JSONException e) {
                            Log.d("JSONException e","JSONException e");
                            e.printStackTrace();
                        }finally{
                            Intent intent = new Intent(DataBackupActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"백업에 성공했습니다.",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다.",Toast.LENGTH_LONG).show();
                    }

            }

        });
    }
}