package com.demo.cunjia.compatibilitytest;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static  private  final String TAG = "MainActivity";
    TextView textView ;
            ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.tv);
        /*打印log*/
        Log.d(TAG,"oncreate");
        /*savedInstanceState可以用于保存一些生命周期内的信息*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*打印log*/
        Log.d(TAG,"onstart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            Log.d(TAG,"onRestoreInstanceState");
            textView.setText(savedInstanceState.getString("LATE_RUN"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        Date date = new Date();
        outState.putString("LATE_RUN",date.toString());
        Log.d(TAG,"将"+"上次退出时间保存到outState");
        Log.d(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*打印log*/
        Log.d(TAG,"onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*打印log*/
        Log.d(TAG,"onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*打印log*/
        Log.d(TAG,"onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*打印log*/
        Log.d(TAG,"ondestory");
    }




    /*测试下intent-filter*/
    public void sendMessage(View view){
        Intent sendIntent = new Intent();
        /*总是弹出选择器*/
        Intent chooser = Intent.createChooser(sendIntent,"chooser");
        sendIntent.setAction(Intent.ACTION_SEND);
        //sendIntent.setType("text/plain");
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_TEXT,"zjl");
        startActivity(chooser);
    }
}
