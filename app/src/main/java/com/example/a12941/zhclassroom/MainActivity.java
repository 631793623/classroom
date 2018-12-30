package com.example.a12941.zhclassroom;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_user, login_pas;
    private TextView sign_in_btn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        login_user = (EditText) findViewById(R.id.login_user);
        login_pas = (EditText) findViewById(R.id.login_pas);
        sign_in_btn = (TextView) findViewById(R.id.sign_in_btn);
        sign_in_btn.setOnClickListener(this);
       handler=new Handler(new Handler.Callback() {
           @Override
           public boolean handleMessage(Message msg) {
               String currentThreadName = Thread.currentThread().getName();
               int data=(int)msg.obj;
               if(data==1)
               {
                   Intent intent=new Intent();
                   intent.setClass(MainActivity.this,IndexActivity.class);
                   startActivity(intent);
               }
              else
               {
                   new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("输入有误,点击返回登陆页面").setPositiveButton("确定", hello).show();

               }
               return false;
           }
       });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_btn:

                login();

                break;

        }
    }


    DialogInterface.OnClickListener hello = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// 点击 确认，退出程序
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// 点击 取消 取消对话框
                    break;
                default:
                    break;
            }
        }
    };


    public void login()//登录判定
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String a1 = login_user.getText().toString();
                String a2 = login_pas.getText().toString();
                //int data=login_check(a1,a2);
                int data=1;
                String currentThreadName = Thread.currentThread().getName();
                Message message = new Message();
                message.what = 1;
               message.obj = data;
                handler.sendMessage(message);
            }
        }).start();

    }
}
