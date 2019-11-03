package com.example.wificar;
/*
版权声明：
河北师范大学职业技术学院电子信息教研室·智能运动与行为控制项目组版权所有
您可以任意修改本程序，并应用于自行研发的智能小车机器人及其他电子产品上，但是禁止用于商业牟利。
我们保留付诸法律起诉侵权的权利！2019-9-11
BY 电子信息教研室·智能运动与行为控制项目组 V1.0版本
*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;

/**
 * 指令发送工具类
 *
 * @author li
 * @date 2019/11/1
 * @time 下午3:36
 */
public class SendUitl {
    //指令初始化
    public static byte[] STOP = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF};
    public static byte[] GO_AHEAD = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0xFF};
    public static byte[] GO_BACK = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0xFF};
    public static byte[] TURN_LEFT = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0xFF};
    public static byte[] TURN_RIGHT = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0xFF};
    public static byte[] TURN_LEFT_FORWARD = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0xFF};
    public static byte[] TURN_LEFT_BACK = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0xFF};
    public static byte[] TURN_RIGHT_FORWARD = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0xFF};
    public static byte[] TURN_RIGHT_BACK = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0xFF};
    public static byte[] LEFT_ROTATION = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0xFF};
    public static byte[] RIGHT_ROTATION = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0xFF};
    //默认IP
    public static String IP = "192.168.1.1";
    //默认端口
    public static int PORT = 2001;
    //默认视频地址
    public static String VIDEO_PATH = "http://192.168.1.1:8080/?actio=snapshot";
    //本地存储文件
    private SharedPreferences car;
    //环境上下文
    private Context context;
    //链接对象
    private Socket client = null;
    private OutputStream outputStream = null;
    //消息接受handler
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if ("true".equals(msg.obj)) {
                try {
                    outputStream = client.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, "连接成功!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "连接失败，请重试!", Toast.LENGTH_SHORT).show();
            }
        }
    };


    /**
     * 功能描述 工具类构造方法，初始化工具类,需传环境上下文
     *
     * @param context
     * @return
     * @author li
     * @date 2019/11/1
     */
    public SendUitl(Context context) {
        this.context = context;
        //获取本地存储的用户自定义指令和设置,如果有则替换默认指令和设置
        car = context.getSharedPreferences("Car", 0);
        if (!car.getString("STOP", "").equals("")) {
            STOP = StringToOx(car.getString("STOP", ""));
        }
        if (!car.getString("GO_AHEAD", "").equals("")) {
            GO_AHEAD = StringToOx(car.getString("GO_AHEAD", ""));
        }
        if (!car.getString("BACK", "").equals("")) {
            GO_BACK = StringToOx(car.getString("BACK", ""));
        }
        if (!car.getString("TURN_LEFT", "").equals("")) {
            TURN_LEFT = StringToOx(car.getString("TURN_LEFT", ""));
        }
        if (!car.getString("TURN_RIGHT", "").equals("")) {
            TURN_RIGHT = StringToOx(car.getString("TURN_RIGHT", ""));
        }
        if (!car.getString("TURN_LEFT_FORWARD", "").equals("")) {
            TURN_LEFT_FORWARD = StringToOx(car.getString("TURN_LEFT_FORWARD", ""));
        }
        if (!car.getString("TURN_LEFT_BACK", "").equals("")) {
            TURN_LEFT_BACK = StringToOx(car.getString("TURN_LEFT_BACK", ""));
        }
        if (!car.getString("TURN_RIGHT_FORWARD", "").equals("")) {
            TURN_RIGHT_FORWARD = StringToOx(car.getString("TURN_RIGHT_FORWARD", ""));
        }
        if (!car.getString("TURN_RIGHT_BACK", "").equals("")) {
            TURN_RIGHT_BACK = StringToOx(car.getString("TURN_RIGHT_BACK", ""));
        }
        if (!car.getString("LEFT_ROTATION", "").equals("")) {
            LEFT_ROTATION = StringToOx(car.getString("LEFT_ROTATION", ""));
        }
        if (!car.getString("RIGHT_ROTATION", "").equals("")) {
            RIGHT_ROTATION = StringToOx(car.getString("RIGHT_ROTATION", ""));
        }
        if (!car.getString("IP", "").equals("")) {
            IP = car.getString("IP", "");
        }
        if (car.getInt("PORT", 0) != 0) {
            PORT = car.getInt("PORT", 0);
        }
        if (!car.getString("VIDEO_PATH", "").equals("")) {
            VIDEO_PATH = car.getString("VIDEO_PATH", "");
        }


        //如果不存在连接对象则进行连接，否则不重新连接
        if (client == null) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        client = new Socket(IP, PORT);
                        Message msg = new Message();
                        msg.obj = "true";
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /**
     * 功能描述 获得当前链接ip
     *
     * @param
     * @return java.lang.String
     * @author li
     * @date 2019/11/1
     */
    public String getIP() {
        return IP;
    }

    /**
     * 功能描述 设置连接ip
     *
     * @param IP
     * @return void
     * @author li
     * @date 2019/11/1
     */
    public void setIP(String IP) {
        SendUitl.IP = IP;
        SharedPreferences.Editor edit = car.edit();
        edit.putString("IP", IP);
        edit.commit();
    }

    /**
     * 功能描述 获得当前连接端口号
     *
     * @param
     * @return int
     * @author li
     * @date 2019/11/1
     */
    public int getPORT() {
        return PORT;
    }

    /**
     * 功能描述 设置连接端口号
     *
     * @param PORT
     * @return void
     * @author li
     * @date 2019/11/1
     */
    public void setPORT(int PORT) {
        SendUitl.PORT = PORT;
        SharedPreferences.Editor edit = car.edit();
        edit.putInt("PORT", PORT);
        edit.commit();
    }

    /**
     * 功能描述 获得视频地址
     * @author li
     * @date 2019/11/1
     * @param
     * @return java.lang.String
     */
    public String getVideoPath() {
        return VIDEO_PATH;
    }

    /**
     * 功能描述 设置视频地址
     * @author li
     * @date 2019/11/1
     * @param videoPath
     * @return void
     */
    public void setVideoPath(String videoPath) {
        VIDEO_PATH = videoPath;
        SharedPreferences.Editor edit = car.edit();
        edit.putString("VIDEO_PATH", videoPath);
        edit.commit();
    }

    /**
     * 功能描述 发送指令
     *
     * @param instruction
     * @return void
     * @author li
     * @date 2019/11/1
     */
    public void sendInstruction(final byte[] instruction) {
        new Thread(){
            @Override
            public void run() {
                try {
                    if (outputStream != null) {
                        outputStream.write(instruction);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 功能描述 修改指令值
     *
     * @param STOP
     * @return void
     * @author li
     * @date 2019/11/1
     */
    public void setSTOP(String STOP) {
        SendUitl.STOP = StringToOx(STOP);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("STOP", STOP);
        edit.commit();
    }

    public void setGoAhead(String goAhead) {
        SendUitl.GO_AHEAD = StringToOx(goAhead);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("GO_AHEAD", goAhead);
        edit.commit();
    }

    public void setGoBack(String goBack) {
        SendUitl.GO_BACK = StringToOx(goBack);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("GO_BACK", goBack);
        edit.commit();
    }

    public void setTurnLeft(String turnLeft) {
        SendUitl.TURN_LEFT = StringToOx(turnLeft);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("TURN_LEFT", turnLeft);
        edit.commit();
    }

    public void setTurnRight(String turnRight) {
        SendUitl.TURN_RIGHT = StringToOx(turnRight);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("TURN_RIGHT", turnRight);
        edit.commit();
    }

    public void setTurnLeftForward(String turnLeftForward) {
        SendUitl.TURN_LEFT_FORWARD = StringToOx(turnLeftForward);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("TURN_LEFT_FORWARD", turnLeftForward);
        edit.commit();
    }

    public void setTurnLeftBack(String turnLeftBack) {
        SendUitl.TURN_LEFT_BACK = StringToOx(turnLeftBack);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("TURN_LEFT_BACK", turnLeftBack);
        edit.commit();
    }

    public void setTurnRightForward(String turnRightForward) {
        SendUitl.TURN_RIGHT_FORWARD = StringToOx(turnRightForward);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("TURN_RIGHT_FORWARD", turnRightForward);
        edit.commit();
    }

    public void setTurnRightBack(String turnRightBack) {
        SendUitl.TURN_RIGHT_BACK = StringToOx(turnRightBack);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("TURN_RIGHT_BACK", turnRightBack);
        edit.commit();
    }

    public void setLeftRotation(String leftRotation) {
        SendUitl.LEFT_ROTATION = StringToOx(leftRotation);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("LEFT_ROTATION", leftRotation);
        edit.commit();
    }

    public void setRightRotation(String rightRotation) {
        SendUitl.GO_AHEAD = StringToOx(rightRotation);
        SharedPreferences.Editor edit = car.edit();
        edit.putString("RIGHT_ROTATION", rightRotation);
        edit.commit();
    }

    /**
     * 功能描述 十六进制字符串转换成十六进制字节数组
     *
     * @param str
     * @return byte[]
     * @author li
     * @date 2019/11/1
     */
    public static byte[] StringToOx(String str) {
        byte[] data = new BigInteger(str, 16).toByteArray();
        return data;
    }

    /**
     * 功能描述 十六进制转成对应字符串
     *
     * @param data
     * @return java.lang.String
     * @author li
     * @date 2019/11/1
     */
    public static String OxToString(byte[] data) {
        String result = "";
        for (int i = 0; i < data.length; i++) {
            if (Integer.toHexString(data[i]).length() == 1) {
                result += "0" + Integer.toHexString(data[i] & 0XFF);
            } else {
                result += Integer.toHexString(data[i] & 0XFF);
            }
        }
        return result.toUpperCase();
    }

}
