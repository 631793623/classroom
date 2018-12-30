package com.example.a12941.zhclassroom;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class geren extends AppCompatActivity implements View.OnClickListener {
    private Button tijiao, back;
    private ImageView photo1;
    private EditText school, academy, phonennum, email;
    private TextView user, name;
    private static final int IMAGE = 1;
    private String path = "no";
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);


        tijiao = findViewById(R.id.tijiao);
        back = findViewById(R.id.back);

        photo1 = findViewById(R.id.photo1);
        user = findViewById(R.id.user);

        name = findViewById(R.id.name);
        school = findViewById(R.id.school);
        academy = findViewById(R.id.academy);
        phonennum = findViewById(R.id.phonennum);
        email = findViewById(R.id.email);
        getall();

        tijiao.setOnClickListener(this);
        back.setOnClickListener(this);
        photo1.setOnClickListener(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {


                return false;
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent = new Intent();
                intent.setClass(geren.this, IndexActivity.class);
                startActivity(intent);
                break;
            case R.id.photo1:
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, IMAGE);
                break;
            case R.id.tijiao:
                upload();
                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            path = imagePath;
            c.close();
            photo1.setImageBitmap(BitmapFactory.decodeFile(imagePath));

        }
    }


    public void getall()//获取个人信息的默认值
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                int data = 1;
                String currentThreadName = Thread.currentThread().getName();
                Message message = new Message();
                message.what = 1;
                message.obj = data;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void upload()//上传信息
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //图片路径path，默认no,写个判断，school.getText().toString();academy.getText().toString();email.getText().toString();phonennum.getText().toString();

                int data = 1;
                String currentThreadName = Thread.currentThread().getName();
                Message message = new Message();
                message.what = 1;
                message.obj = data;
                handler.sendMessage(message);
            }
        }).start();
    }

}
