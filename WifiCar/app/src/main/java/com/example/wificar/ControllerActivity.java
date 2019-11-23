package com.example.wificar;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ControllerActivity extends AppCompatActivity {

    private SendUitl sendUitl;
    private ImageView BTN_GO_HEAD;
    private ImageView BTN_GO_BACK;
    private ImageView BTN_TURN_LEFT;
    private ImageView BTN_TURN_RIGHT;
    private ImageView BTN_STOP;
    private Button BTN_TURN_LEFT_FORWARD;
    private Button BTN_TURN_LEFT_BACK;
    private Button BTN_TURN_RIGHT_FORWARD;
    private Button BTN_TURN_RIGHT_BACK;
    private Button BTN_LEFT_ROTATION;
    private Button BTN_RIGHT_ROTATION;
    private CustomOnclickListener listener;
    private CustomerOntouchListener ontouchListener;
    private WebView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        video=findViewById(R.id.video);
        video.loadUrl(SendUitl.VIDEO_PATH);
        sendUitl = new SendUitl(ControllerActivity.this);
        getViews();
        registerListner();
        setStatusBar();
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
        ontouchListener = new CustomerOntouchListener();
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
        BTN_GO_HEAD.setOnTouchListener(ontouchListener);
        BTN_TURN_RIGHT.setOnTouchListener(ontouchListener);
        BTN_TURN_LEFT.setOnTouchListener(ontouchListener);
        BTN_STOP.setOnTouchListener(ontouchListener);
        BTN_GO_BACK.setOnTouchListener(ontouchListener);
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

    /**
     * @Description 图片触摸监听事件
     * @Auther 孙建旺
     * @Date 下午 4:21 2019/11/09
     * @Param
     * @return
     */
    class CustomerOntouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()){
                case R.id.BTN_GO_AHEAD:
                    if(event.getAction()==MotionEvent.ACTION_DOWN)
                        BTN_GO_HEAD.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.forward_red));
                    else
                        BTN_GO_HEAD.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.forward));
                    break;
                case R.id.BTN_GO_BACK:
                    if(event.getAction()==MotionEvent.ACTION_DOWN)
                        BTN_GO_BACK.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.back_red));
                    else
                        BTN_GO_BACK.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.back));
                    break;
                case R.id.BTN_TURN_LEFT:
                    if(event.getAction()==MotionEvent.ACTION_DOWN)
                        BTN_TURN_LEFT.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.left_red));
                    else
                        BTN_TURN_LEFT.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.left));
                    break;
                case R.id.BTN_TURN_RIGHT:
                    if(event.getAction()==MotionEvent.ACTION_DOWN)
                        BTN_TURN_RIGHT.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.red_right));
                    else
                        BTN_TURN_RIGHT.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.right));
                    break;
                case R.id.BTN_STOP:
                    if(event.getAction()==MotionEvent.ACTION_DOWN)
                        BTN_STOP.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.stop_gray));
                    else
                        BTN_STOP.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.stop));
                    break;
            }
            return false;
        }
    }


    /**
     * @Description 点击事件,点击发送指令
     * @Auther 孙建旺
     * @Date 下午 3:25 2019/11/09
     * @Param
     * @return
     */
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
