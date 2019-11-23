package com.example.wificar;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText GO_AHEAD;  //前进
    private EditText GO_BACK;   //后退
    private EditText TURN_LEFT;     //左转
    private EditText TURN_RIGHT;    //右转
    private EditText TURN_LEFT_FORWARD;     //左前
    private EditText TURN_RIGHT_FORWARD;    //右前
    private EditText TURN_LEFT_BACK;    //左后
    private EditText TURN_RIGHT_BACK;   //右后
    private EditText LEFT_ROTATION;     //左旋转
    private EditText RIGHT_ROTATION;    //右旋转
    private EditText STOP;      //停止
    private EditText CONTROLLER_PORT;   //控制端口
    private EditText IP;    //控制IP
    private EditText MEDIA_ADDRESS;     //视频地址1
    private EditText MEDIA_ADDRESS2;    //视频地址2
    private CustomerOnclickListener onclickListener;
    private Button cancel;
    private Button save;
    private SharedPreferences car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        car = getSharedPreferences("Car", 0);
        getViews();
        registerListener();
        getAllData();
        setStatusBar();
    }

    /**
     * @Description 填充所有数据
     * @Auther 孙建旺
     * @Date 下午 5:28 2019/11/02
     * @Param []
     * @return void
     */
    private void getAllData() {
        GO_AHEAD.setText(SendUitl.OxToString(SendUitl.GO_AHEAD));
        GO_BACK.setText(SendUitl.OxToString(SendUitl.GO_BACK));
        TURN_LEFT.setText(SendUitl.OxToString(SendUitl.TURN_LEFT));
        TURN_RIGHT.setText(SendUitl.OxToString(SendUitl.TURN_RIGHT));
        TURN_LEFT_FORWARD.setText(SendUitl.OxToString(SendUitl.TURN_LEFT_FORWARD));
        TURN_RIGHT_FORWARD.setText(SendUitl.OxToString(SendUitl.TURN_RIGHT_FORWARD));
        TURN_LEFT_BACK.setText(SendUitl.OxToString(SendUitl.TURN_LEFT_BACK));
        TURN_RIGHT_BACK.setText(SendUitl.OxToString(SendUitl.TURN_RIGHT_BACK));
        LEFT_ROTATION.setText(SendUitl.OxToString(SendUitl.LEFT_ROTATION));
        RIGHT_ROTATION.setText(SendUitl.OxToString(SendUitl.RIGHT_ROTATION));
        STOP.setText(SendUitl.OxToString(SendUitl.STOP));
        CONTROLLER_PORT.setText(SendUitl.PORT+"");
        IP.setText(SendUitl.IP);
        MEDIA_ADDRESS.setText(SendUitl.VIDEO_PATH);
        MEDIA_ADDRESS2.setText(SendUitl.VIDEO_PATH);
    }

    /**
     * @Description 注册事件监听器
     * @Auther 孙建旺
     * @Date 下午 6:33 2019/11/01
     * @Param []
     * @return void
     */
    private void registerListener() {
        onclickListener = new CustomerOnclickListener();
        save.setOnClickListener(onclickListener);
        cancel.setOnClickListener(onclickListener);
    }

    /**
     * @Description 获取控件ID
     * @Auther 孙建旺
     * @Date 下午 6:07 2019/11/01
     * @Param []        
     * @return void 
     */
    private void getViews() {
        GO_AHEAD = findViewById(R.id.GO_AHEAD);
        GO_BACK = findViewById(R.id.GO_BACK);
        TURN_LEFT = findViewById(R.id.TURN_LEFT);
        TURN_RIGHT = findViewById(R.id.TURN_RIGHT);
        TURN_LEFT_FORWARD = findViewById(R.id.TURN_LEFT_FORWARD);
        TURN_LEFT_BACK = findViewById(R.id.TURN_LEFT_BACK);
        TURN_RIGHT_FORWARD = findViewById(R.id.TURN_RIGHT_FORWARD);
        TURN_RIGHT_BACK = findViewById(R.id.TURN_RIGHT_BACK);
        RIGHT_ROTATION = findViewById(R.id.RIGHT_ROTATION);
        LEFT_ROTATION = findViewById(R.id.LEFT_ROTATION);
        STOP = findViewById(R.id.STOP);
        MEDIA_ADDRESS = findViewById(R.id.MEDIAADDRESS);
        MEDIA_ADDRESS2 = findViewById(R.id.MEDIAADDRESS2);
        CONTROLLER_PORT = findViewById(R.id.CONTROLLER_PORT);
        IP = findViewById(R.id.IP);
        cancel = findViewById(R.id.btnCancel);
        save = findViewById(R.id.btnSave);
    }

    /**
     * @Description 点击事件监听器
     * @Auther 孙建旺
     * @Date 下午 6:37 2019/11/01
     * @Param
     * @return
     */
    class CustomerOnclickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnCancel:
                    finish();
                    break;
                case R.id.btnSave:
                    SendUitl.setGoAhead(GO_AHEAD.getText().toString().trim());
                    SendUitl.setGoBack(GO_BACK.getText().toString().trim());
                    SendUitl.setTurnLeft(TURN_LEFT.getText().toString().trim());
                    SendUitl.setTurnRight(TURN_RIGHT.getText().toString().trim());
                    SendUitl.setTurnLeftForward(TURN_LEFT_FORWARD.getText().toString().trim());
                    SendUitl.setTurnRightForward(TURN_RIGHT_FORWARD.getText().toString().trim());
                    SendUitl.setTurnLeftBack(TURN_LEFT_BACK.getText().toString().trim());
                    SendUitl.setTurnRightBack(TURN_RIGHT_BACK.getText().toString().trim());
                    SendUitl.setLeftRotation(LEFT_ROTATION.getText().toString().trim());
                    SendUitl.setRightRotation(RIGHT_ROTATION.getText().toString().trim());
                    SendUitl.setIP(IP.getText().toString().trim());
                    SendUitl.setPORT(Integer.parseInt(CONTROLLER_PORT.getText().toString().trim()));
                    SendUitl.setVideoPath(MEDIA_ADDRESS.getText().toString().trim());
                    SendUitl.setSTOP(STOP.getText().toString().trim());
                    Toast toast = Toast.makeText(SettingsActivity.this,"保存成功！",Toast.LENGTH_SHORT);
                    toast.show();
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
