package com.example.wificar;

import android.webkit.WebView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.math.BigInteger;

public class ShowActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnSettings;
    private CustomOnclickListener onclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        registerListener();
    }

    /**
     * @Description 注册事件监听器
     * @Auther 孙建旺
     * @Date 下午 3:39 2019/10/31
     * @Param []
     * @return void
     */
    private void registerListener() {
        onclickListener = new CustomOnclickListener();
        btnSettings.setOnClickListener(onclickListener);
        btnStart.setOnClickListener(onclickListener);
    }

    /**
     * @Description 获取控件ID
     * @Auther 孙建旺
     * @Date 下午 3:38 2019/10/31
     * @Param []
     * @return void
     */
    private void getViews() {
        btnStart = findViewById(R.id.btnStart);
        btnSettings = findViewById(R.id.btnSettings);
    }

    /**
     * @Description 事件监听器
     * @Auther 孙建旺
     * @Date 下午 3:38 2019/10/31
     * @Param
     * @return
     */
    class CustomOnclickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnStart:
                    Intent intent = new Intent();
                    intent.setClass(ShowActivity.this,ControllerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnSettings:
                    Intent intent1 = new Intent();
                    intent1.setClass(ShowActivity.this,SettingsActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    }
}
