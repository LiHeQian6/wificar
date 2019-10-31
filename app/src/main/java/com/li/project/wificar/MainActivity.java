package com.li.project.wificar;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.DiscoverySession;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import javax.net.SocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private Button send;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            text.setText((String) msg.obj);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        send=findViewById(R.id.send);

//        new Thread(){
//            @Override
//            public void run() {
//                try {
//
//                    //3.获取输入流，输出流对象
//                    while (true){
//                        ServerSocket serverSocket= null;
//                        serverSocket = new ServerSocket(2001);
//                        Socket client = serverSocket.accept();
//                        System.out.println("有客户端连接请求");
//                        InputStream in = client.getInputStream();
//                        //创建Scanner对象
//                        byte[] data = new byte[255];
//                        in.read(data);
//                        String msg = new String(data);
//                        Message message = new Message();
//                        message.obj=msg;
//                        handler.sendMessage(message);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


        //1.先创建Socket连接对象
        WifiManager mWifiManager= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
        int ipAddress = connectionInfo.getIpAddress();
        DhcpInfo dhcpInfo = mWifiManager.getDhcpInfo();
        final String ip = intToIp(ipAddress);
        Toast.makeText(this, ip ,Toast.LENGTH_SHORT).show();
        Log.e("ip",ip);
        Log.e("ip2",intToIp(dhcpInfo.ipAddress));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                Socket client = new Socket(ip,2001);
                                InputStream in = client.getInputStream();
                                OutputStream out = client.getOutputStream();
                                String msg = text.getText().toString().trim();
                                out.write(msg.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();


            }
        });
    }
    private String intToIp(int paramInt)  {
      return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "."
              + (0xFF & paramInt >> 24);
    }
}
