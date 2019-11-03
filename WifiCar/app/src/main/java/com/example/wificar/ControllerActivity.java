package com.example.wificar;

import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ControllerActivity extends AppCompatActivity {

    private SendUitl sendUitl;
    private Button BTN_GO_HEAD;
    private Button BTN_GO_BACK;
    private Button BTN_TURN_LEFT;
    private Button BTN_TURN_RIGHT;
    private Button BTN_STOP;
    private Button BTN_TURN_LEFT_FORWARD;
    private Button BTN_TURN_LEFT_BACK;
    private Button BTN_TURN_RIGHT_FORWARD;
    private Button BTN_TURN_RIGHT_BACK;
    private Button BTN_LEFT_ROTATION;
    private Button BTN_RIGHT_ROTATION;
    private CustomOnclickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        sendUitl = new SendUitl(ControllerActivity.this);
        getViews();
        registerListner();
    }

    /**
     * @Description 注册事件监听器
     * @Auther 孙建旺
     * @Date 下午 6:39 2019/11/03
     * @Param []
     * @return void
     */
    private void registerListner() {
        listener = new CustomOnclickListener();
        BTN_GO_HEAD.setOnClickListener(listener);
        BTN_GO_BACK.setOnClickListener(listener);
        BTN_STOP.setOnClickListener(listener);
        BTN_TURN_LEFT.setOnClickListener(listener);
        BTN_TURN_RIGHT.setOnClickListener(listener);
        BTN_TURN_LEFT_FORWARD.setOnClickListener(listener);
        BTN_TURN_LEFT_BACK.setOnClickListener(listener);
        BTN_TURN_RIGHT_FORWARD.setOnClickListener(listener);
        BTN_TURN_RIGHT_BACK.setOnClickListener(listener);
        BTN_LEFT_ROTATION.setOnClickListener(listener);
        BTN_RIGHT_ROTATION.setOnClickListener(listener);
    }

    /**
     * @Description 获取控件ID
     * @Auther 孙建旺
     * @Date 下午 6:33 2019/11/03
     * @Param []
     * @return void
     */
    private void getViews() {
        BTN_GO_HEAD = findViewById(R.id.BTN_GO_AHEAD);
        BTN_GO_BACK = findViewById(R.id.BTN_GO_BACK);
        BTN_TURN_LEFT = findViewById(R.id.BTN_TURN_LEFT);
        BTN_TURN_RIGHT = findViewById(R.id.BTN_TURN_RIGHT);
        BTN_STOP = findViewById(R.id.BTN_STOP);
        BTN_TURN_RIGHT_BACK = findViewById(R.id.BTN_TURN_RIGHT_BACK);
        BTN_TURN_RIGHT_FORWARD = findViewById(R.id.BTN_TURN_RIGHT_FORWARD);
        BTN_TURN_LEFT_BACK = findViewById(R.id.BTN_TURN_LEFT_BACK);
        BTN_TURN_LEFT_FORWARD = findViewById(R.id.BTN_TURN_LEFT_FORWARD);
        BTN_RIGHT_ROTATION = findViewById(R.id.BTN_RIGHT_ROTATION);
        BTN_LEFT_ROTATION = findViewById(R.id.BTN_LEFT_ROTATION);
    }

    class CustomOnclickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.BTN_GO_AHEAD:
                    sendUitl.sendInstruction(SendUitl.GO_AHEAD);
                    break;
                case R.id.BTN_GO_BACK:
                    sendUitl.sendInstruction(SendUitl.GO_BACK);
                    break;
                case R.id.BTN_STOP:
                    sendUitl.sendInstruction(SendUitl.STOP);
                    break;
                case R.id.BTN_TURN_LEFT:
                    sendUitl.sendInstruction(SendUitl.TURN_LEFT);
                    break;
                case R.id.BTN_TURN_RIGHT:
                    sendUitl.sendInstruction(SendUitl.TURN_RIGHT);
                    break;
                case R.id.BTN_TURN_LEFT_BACK:
                    sendUitl.sendInstruction(SendUitl.TURN_LEFT_BACK);
                    break;
                case R.id.BTN_TURN_LEFT_FORWARD:
                    sendUitl.sendInstruction(SendUitl.TURN_LEFT_FORWARD);
                    break;
                case R.id.BTN_TURN_RIGHT_BACK:
                    sendUitl.sendInstruction(SendUitl.TURN_RIGHT_BACK);
                    break;
                case R.id.BTN_TURN_RIGHT_FORWARD:
                    sendUitl.sendInstruction(SendUitl.TURN_RIGHT_FORWARD);
                    break;
                case R.id.BTN_RIGHT_ROTATION:
                    sendUitl.sendInstruction(SendUitl.RIGHT_ROTATION);
                    break;
                case R.id.BTN_LEFT_ROTATION:
                    sendUitl.sendInstruction(SendUitl.LEFT_ROTATION);
                    break;
            }
        }
    }
}
