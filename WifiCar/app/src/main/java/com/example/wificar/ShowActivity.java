package com.example.wificar;
/*
版权声明：
河北师范大学职业技术学院电子信息教研室·智能运动与行为控制项目组版权所有
您可以任意修改本程序，并应用于自行研发的智能小车机器人及其他电子产品上，但是禁止用于商业牟利。
我们保留付诸法律起诉侵权的权利！2019-9-11
BY 电子信息教研室·智能运动与行为控制项目组 V1.0版本
*/
import android.os.Build;
import android.view.WindowManager;
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
        setStatusBar();
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

    /**
     * 调整标题栏与状态栏style
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//隐藏状态栏但不隐藏状态栏字体
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏，并且不显示字体
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏文字颜色为暗色

        }
    }
}
