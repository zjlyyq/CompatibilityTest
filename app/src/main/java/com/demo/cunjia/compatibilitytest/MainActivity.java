package com.demo.cunjia.compatibilitytest;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static  public  final String TAG = "MainActivity";
    static private final String activityName = "MainActivity:";
    int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
    int uiOption2;
    int uiOptions3;
    int uiOptions4;
    int count = 0;
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("RUN_TIMES",MODE_PRIVATE);
        int times = sharedPreferences.getInt("RUN_TIMES",0);
        sharedPreferences.edit().putInt("RUN_TIMES",times+1);
        textView = (TextView)findViewById(R.id.tv);
        textView.setText("这是第"+times+"次运行次软件");
        /*打印log*/
        Log.d(TAG,activityName+"oncreate");
        /*savedInstanceState可以用于保存一些生命周期内的信息*/

        /*淡化状态栏，美观app界面*/
        /*下面的clearSystemUiVisibility函数用来反复切换*/
        View decroView = getWindow().getDecorView();
        uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        decroView.setSystemUiVisibility(uiOptions);

        /*隐藏状态栏*/
        uiOption2 = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decroView.setSystemUiVisibility(uiOption2);
        /*状态栏隐藏了，不能单独显示工具栏，必须隐藏*/
        ActionBar actionBar = this.getActionBar();
        if (actionBar != null){
            Toast.makeText(this,"actionBar不为空",Toast.LENGTH_SHORT).show();
            actionBar.hide();
        }else {
            Toast.makeText(this,"actionBar为空",Toast.LENGTH_SHORT).show();
        }

        /*使内容出现在状态栏下面，就像网易云音乐那样*/
        uiOptions3 = View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN;
        decroView.setSystemUiVisibility(uiOptions3);

        /*隐藏导航栏(也就是屏内三大金刚键)*/
        uiOptions4 = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        //decroView.setSystemUiVisibility(uiOptions4);

        /*使app铺满整个屏幕*/
        int uiOptions5 = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decroView.setSystemUiVisibility(uiOptions5);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*打印log*/
        Log.d(TAG,activityName+"onstart");
    }
/*onSaveInstanceState方法改变了savedInstanceState才会在下次重新运行时调用此方法*/
/*但是很奇怪，只有当旋转设备方向的时候才调用了此方法，其他时候都没有调用，即使savedInstanceState已经改变了*/
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            Log.d(TAG,activityName+"onRestoreInstanceState");
            textView.setText(savedInstanceState.getString("LATE_RUN"));
        }else{
            Log.d(TAG,activityName+"savedInstanceState还是空？");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        Date date = new Date();
        outState.putString("LATE_RUN",date.toString());
        Log.d(TAG,activityName+"将"+"上次退出时间保存到outState");
        Log.d(TAG,activityName+outState.getString("LATE_RUN"));
        if (outState != null){
            Log.d(TAG,activityName+"outState");
            //textView.setText(outState.getString("LATE_RUN"));
        }else{
            Log.d(TAG,activityName+"outState还是空？");
        }
        super.onSaveInstanceState(outState);
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
    /*退出activity*/
    public void exitActivity(View view){
        finish();
    }
    /*发送email*/
    public void sendEmail(View view){
        Intent sendEmailIntent = new Intent();
        /*总是弹出选择器*/
        Intent chooser = Intent.createChooser(sendEmailIntent,"选择打开哪个应用");
        sendEmailIntent.setAction(Intent.ACTION_SEND);
        sendEmailIntent.setType("text/plain");
        /*数组用于保存接收人邮件地址*/
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("2868989685@qq.com");
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL,addresses);
        Log.d(TAG,activityName+"邮箱地址："+addresses.toString());
        startActivity(chooser);

    }
    /*状态栏淡化效果和隐藏切换*/
    public void clearSystemUiVisibility(View view){
        if (count % 2 == 0){
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        }else {
            getWindow().getDecorView().setSystemUiVisibility(uiOption2);
        }
        count ++;

    }
    /*重写此方法，实现确认退出的效果*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "请在地图上圈出你想要的区域", Toast.LENGTH_LONG).show();
    }
}
