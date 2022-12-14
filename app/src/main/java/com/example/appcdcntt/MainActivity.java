package com.example.appcdcntt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Console;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {
    Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.1.4:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSocket.connect();
    }
}