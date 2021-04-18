package com.tsai1247.eutest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends Activity {

    private Button btn;
    private Button reset;
    private TextView tv;
    private TextView tv2;
    private EditText et;
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
        });
        reset.setOnClickListener(e->{
            et.setText("1");
            tv2.setText("0");
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}