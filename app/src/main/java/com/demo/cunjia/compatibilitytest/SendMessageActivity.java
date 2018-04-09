package com.demo.cunjia.compatibilitytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SendMessageActivity extends AppCompatActivity {
    static  public  final String TAG = "MainActivity";
    static private final String activityName = "SendMessageActivity:";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        textView = (TextView)findViewById(R.id.tv);
        textView.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        Log.d(TAG,activityName+"oncreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        /*打印log*/
        Log.d(TAG,activityName+"onstart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        /*打印log*/
        Log.d(TAG,activityName+"onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*打印log*/
        Log.d(TAG,activityName+"onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*打印log*/
        Log.d(TAG,activityName+"onstop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,activityName+"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*打印log*/
        Log.d(TAG,activityName+"ondestory");
    }
}
