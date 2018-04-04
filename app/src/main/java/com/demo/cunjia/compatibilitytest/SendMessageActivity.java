package com.demo.cunjia.compatibilitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SendMessageActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        textView = (TextView)findViewById(R.id.tv);
        textView.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }
}
