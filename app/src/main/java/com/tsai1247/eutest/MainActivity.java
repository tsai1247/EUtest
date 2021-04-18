package com.tsai1247.eutest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private Button btn;
    private Button reset;
    private TextView tv;
    private TextView tv2;
    private TextView et;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        et = findViewById(R.id.editTextTextPersonName);
        reset = findViewById((R.id.button2));
        mAdView = findViewById(R.id.adView);

        try{
            et.setText( readFromFile("tmp.txt").substring(1) );
        }catch (Exception e)
        {
            et.setText("1");
        }
        try{
            tv2.setText( readFromFile("tmp2.txt").substring(1)  );
        }catch (Exception e)
        {
            tv2.setText("0");
        }

        btn.setOnClickListener(e->{
            int cur;
            try {

                cur = Integer.parseInt(et.getText().toString());

            }catch (Exception ee)
            {
                cur = 1;
            }

            if(cur<1)
                cur = 1;
            int answer = (int) Math.ceil(Math.random()*cur);
            if(answer==cur)
                et.setText(String.valueOf(cur+1));
            try {
                tv2.setText(String.valueOf(Integer.parseInt(tv2.getText().toString())+1));
            }catch (Exception ee)
            {
                tv2.setText("1");
            }
            writeToFile("tmp.txt", et.getText().toString());
            writeToFile("tmp2.txt", tv2.getText().toString());
        });
        reset.setOnClickListener(e->{
            et.setText("1");
            tv2.setText("0");

            writeToFile("tmp.txt", et.getText().toString());
            writeToFile("tmp2.txt", tv2.getText().toString());
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    private String precess(String str) {
        Log.e("str", "\"" + str + "\"");
        String ret = "";
        for(int i=0; i<str.length(); i++)
        {
            if(str.indexOf(i)<='9' &&  str.indexOf(i)>='0') {
                ret += String.valueOf(str.indexOf(i));
                Log.e("Success", ret);
            }
            else
                Log.e("i don\'t know", String.valueOf(str.indexOf(i)));
        }
        return ret;
    }


    private void writeToFile(String filename, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(String filename) {

        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }

        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}