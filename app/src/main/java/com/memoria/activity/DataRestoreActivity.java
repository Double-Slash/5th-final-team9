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

public class DataRestoreActivity extends AppCompatActivity {

    private Button restoreButton;
    private EditText findpw;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_restore);

        if(android.os.Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        findpw = findViewById(R.id.findpw);
        result = "";
        restoreButton = findViewById(R.id.restoreButton);
        restoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findpw.getText().toString() != null){
                    try {
                        URL url = new URL("http://221.167.222.53:3001/data/" + findpw.getText().toString());
                        System.out.println(findpw.getText().toString());
                        System.out.println(url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        Log.d("ㅇ","ㅇ");

                        httpURLConnection.setDefaultUseCaches(false);
                        //httpURLConnection.setDoInput(true);
                        //httpURLConnection.setDoOutput(true);
                        httpURLConnection.setRequestMethod("GET");
                        Log.d("ㅇ","ㅇ");

                        InputStream is = httpURLConnection.getInputStream();
                        StringBuilder sb = new StringBuilder();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                        String line;
                        Log.d("ㅇ","ㅇ");

                        while((line = br.readLine()) != null){
                            sb.append(line);
                        }
                        Log.d("ㅇ","ㅇ");

                        result = sb.toString();
                        System.out.println(result);

                        br.close();
                        Log.d("ㅇ","ㅇ");

                        addItem(result);
                        Log.d("ㅇ","ㅇ");

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray arr = jsonObject.getJSONArray("msg");
                            Log.d("jsonfiletttt", arr.get(0).toString());
                            String arr2 = arr.getString(0);
                            Log.d("st1", arr2);
                            JSONObject temp = new JSONObject(arr2);
                            Log.d("st1", temp.toString());
                            String arr3 = temp.getString("data");
                            JSONObject temp2 = new JSONObject(arr3);

                            Log.d("st2", arr3.toString() );
                        } catch(JSONException error){
                            Log.d("오류", error.toString() );
                        }


                    } catch (MalformedURLException e) {
                        Log.d("MalformedURLException e", "MalformedURLException e");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.d("IOException e", "IOException e");
                        e.printStackTrace();
                    }finally{
                        Intent intent = new Intent(DataRestoreActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addItem(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray msg = jsonObject.getJSONArray("msg");
            Log.d("msg",msg.toString());

            String data = msg.getString(0);
            JSONObject dataObject = new JSONObject(data);
            Log.d("data",dataObject.toString());

            String menu = dataObject.getString("data");
            JSONObject menuObject = new JSONObject(menu);
            Log.d("menu",menuObject.toString());

            String word = menuObject.getString("word");
            JSONObject wordObject = new JSONObject(word);
            Log.d("word",wordObject.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}