package com.rnandroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class SendMessage extends AppCompatActivity {

    private Button btn ;
    private EditText editMsg;
    private TextView tv;
    public static SendMessage instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        btn =  findViewById(R.id.btn_send);
        editMsg = findViewById(R.id.edit_msg);
        final Intent intent = getIntent();
        tv = findViewById(R.id.tv_show);
        tv.setText(intent.getStringExtra("msg"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("跳转RN页面");
                MainApplication.reactCommPackage.reactCommModule.sendMsgToRN("toRn",intent.getStringExtra("msg"));
                finish();
            }
        });
    }
}
