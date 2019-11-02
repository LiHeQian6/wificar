package com.example.wificar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ControllerActivity extends AppCompatActivity {

    private SendUitl sendUitl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        sendUitl = new SendUitl(ControllerActivity.this);
    }
}
