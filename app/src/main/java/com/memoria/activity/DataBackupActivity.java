package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.dbhelper.GoalDBHelper;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.dbhelper.MyTestDBHelper;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.Goal;
import com.memoria.modeldata.MyMemory;
import com.memoria.modeldata.MyTest;
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
    private EditText editpw;
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
                    Log.d("editpw", editpw.getText().toString());
                    Log.d("checkpw", checkpw.getText().toString());

                    if (!(editpw.getText().toString().equals(checkpw.getText().toString()))){
                        Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        URL url = new URL("http://222.107.252.206:3001/data");
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
                        JSONArray wordArray = new JSONArray();
                        MyWordDBHelper myWordDBHelper = new MyWordDBHelper(DataBackupActivity.this);
                        ArrayList<MyWord> wordArrayList = myWordDBHelper.selectAllDataList();
                        for (MyWord m : wordArrayList) {
                            JSONObject wordObject = new JSONObject();
                            wordObject.put("wGroupName", m.getGroupName());
                            wordObject.put("wEnglishWord", m.getEnglishWord());
                            wordObject.put("wKoreanWord", m.getKoreanWord());
                            wordObject.put("wDate", m.getDate());
                            System.out.println(m.getGroupName() + " , " + m.getEnglishWord() + ", " + m.getKoreanWord());
                            wordArray.put(wordObject);
                        }

                        //Memory
                        JSONArray memoryArray = new JSONArray();
                        MyMemoryDBHelper mymemoryDBHelper = new MyMemoryDBHelper(DataBackupActivity.this);
                        ArrayList<MyMemory> mymemoryArrayList = mymemoryDBHelper.selectAllDataList();
                        for (MyMemory m : mymemoryArrayList) {
                            JSONObject memoryObject = new JSONObject();
                            memoryObject.put("mEnglishMemory", m.getEnglishMemory());
                            memoryObject.put("mDate", m.getDate());
                            memoryArray.put(memoryObject);
                        }
                        System.out.println(memoryArray);

                        //Goal
                        JSONArray goalArray = new JSONArray();
                        GoalDBHelper goalDBHelper = new GoalDBHelper(DataBackupActivity.this);
                        ArrayList<Goal> goalArrayList = goalDBHelper.selectAllData();
                        for (Goal g : goalArrayList) {
                            JSONObject goalObject = new JSONObject();
                            goalObject.put("gGoalWord", g.getGoalWord());
                            goalObject.put("gGoalMemory", g.getGoalMemory());
                            goalObject.put("gGoalTest", g.getGoalTest());
                            goalObject.put("gGoalQuiz", g.getGoalQuiz());
                            goalObject.put("gAchieveWord", g.getAchieveWord());
                            goalObject.put("gAchieveMemory", g.getAchieveMemory());
                            goalObject.put("gAchieveTest", g.getAchieveTest());
                            goalObject.put("gAchieveQUiz", g.getAchieveQuiz());
                            goalObject.put("gDate", g.getDate());
                            goalArray.put(goalObject);
                        }

//                       //Test
                        JSONArray testArray = new JSONArray();
                        MyTestDBHelper myTestDBHelper = new MyTestDBHelper(DataBackupActivity.this);
                        ArrayList<MyTest> testArrayList = myTestDBHelper.selectAllData();
                        for (MyTest t : testArrayList){
                            JSONObject testObject = new JSONObject();
                            testObject.put("tStatus",t.getStatus());
                            testObject.put("tTotal",t.getTotal());
                            testObject.put("tCorrect",t.getCorrect());
                            testObject.put("tPercent",t.getPercent());
                            testObject.put("tGroup",t.getGroup());
                            testObject.put("tDate",t.getDate());
                            testArray.put(testObject);
                        }
                        System.out.println(testArray);

                        dataObject.put("word", wordArray);
                        dataObject.put("memory", memoryArray);
                        dataObject.put("goal", goalArray);
                        dataObject.put("test", testArray);

                        jsonObject.accumulate("password", editpw.getText().toString());
                        jsonObject.accumulate("data", dataObject.toString());

                        Log.d("성공3", "성공3t");

                        String json = jsonObject.toString();
                        Log.d("성공4", "성공4t");

                        OutputStream os = null;
                        os = httpURLConnection.getOutputStream();
                        Log.d("성공41", os.toString());
                        os.write(json.getBytes("UTF-8"));
                        os.flush();
                        os.close();
                        Log.d("성공5", "성공5t");

                        InputStream is = null;
                        try {
                            int reponseCode = httpURLConnection.getResponseCode();
                            Log.d("서버 응답", Integer.toString(reponseCode));
                            BufferedReader buf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            String inputLine;
                            StringBuffer reponse = new StringBuffer();
                            while ((inputLine = buf.readLine()) != null) {
                                reponse.append(inputLine);
                            }
                            buf.close();
                            Log.d("Message:", reponse.toString());
                        } finally {
                            httpURLConnection.disconnect();
                        }
                    } catch (MalformedURLException e) {
                        Log.d("MalformedURLException e", "MalformedURLException e");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.d("IOException e", "IOException e");
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Log.d("JSONException e", "JSONException e");
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(DataBackupActivity.this, DataBackupResultActivity.class);
                        startActivity(intent);
                        finish();

//                            Toast.makeText(getApplicationContext(),"백업에 성공했습니다.",Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }
}