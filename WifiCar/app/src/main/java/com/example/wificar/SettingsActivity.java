package com.example.wificar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        getViews();
        registerListener();
        getAllData();
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
        GO_AHEAD.setOnClickListener(onclickListener);
        GO_BACK.setOnClickListener(onclickListener);
        TURN_LEFT.setOnClickListener(onclickListener);
        TURN_RIGHT.setOnClickListener(onclickListener);
        TURN_LEFT_FORWARD.setOnClickListener(onclickListener);
        TURN_LEFT_BACK.setOnClickListener(onclickListener);
        TURN_RIGHT_FORWARD.setOnClickListener(onclickListener);
        TURN_RIGHT_BACK.setOnClickListener(onclickListener);
        RIGHT_ROTATION.setOnClickListener(onclickListener);
        LEFT_ROTATION.setOnClickListener(onclickListener);
        STOP.setOnClickListener(onclickListener);
        MEDIA_ADDRESS.setOnClickListener(onclickListener);
        MEDIA_ADDRESS2.setOnClickListener(onclickListener);
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
                case R.id.GO_AHEAD:

                    break;
                case R.id.GO_BACK:

                    break;
                case R.id.TURN_LEFT:

                    break;
                case R.id.TURN_RIGHT:

                    break;
                case R.id.TURN_LEFT_FORWARD:

                    break;
                case R.id.TURN_LEFT_BACK:

                    break;
                case R.id.TURN_RIGHT_FORWARD:

                    break;
                case R.id.TURN_RIGHT_BACK:

                    break;
                case R.id.RIGHT_ROTATION:

                    break;
                case R.id.LEFT_ROTATION:

                    break;
                case R.id.STOP:

                    break;
                case R.id.MEDIAADDRESS:

                    break;
                case R.id.MEDIAADDRESS2:

                    break;
            }
        }
    }
}
