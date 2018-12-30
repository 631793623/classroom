package com.example.a12941.zhclassroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView geren, shezhi;
    private TextView class1, select_class, qiandao, zhuangtai, xinxi;
    private static final int IMAGE = 1;
    private Handler handler;
    private qdresult Qresult = new qdresult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        geren = findViewById(R.id.geren);

        class1 = findViewById(R.id.class1);
        select_class = findViewById(R.id.select_class);
        qiandao = findViewById(R.id.qiandao);
        zhuangtai = findViewById(R.id.zhuangtai);
        xinxi = findViewById(R.id.xinxi);
        class1.setText(Class11.getNAME());

        geren.setOnClickListener(this);

        select_class.setOnClickListener(this);
        qiandao.setOnClickListener(this);
        zhuangtai.setOnClickListener(this);
        xinxi.setOnClickListener(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // Qresult=(qdresult) msg.obj;
                int data = (int) msg.obj;
                if (data == 1)
                    new AlertDialog.Builder(IndexActivity.this).setTitle("提示").setMessage("成功扫描，点击确定查看详细信息").setPositiveButton("确定", hello).show();
                else if (data == 2)
                    new AlertDialog.Builder(IndexActivity.this).setTitle("提示").setMessage("成功扫描，点击确定查看详细信息").setPositiveButton("确定", hello1).show();


                return false;
            }
        });

    }

    DialogInterface.OnClickListener hello = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// 点击 确认，退出程序
                    Intent intent = new Intent();
                    Qresult.num = "88";
                    Qresult.ynum = "47";
                    Qresult.nnum = "41";
                    intent.putExtra("num", Qresult.num);
                    intent.putExtra("ynum", Qresult.ynum);
                    intent.putExtra("nnum", Qresult.nnum);
                    intent.putStringArrayListExtra("id", Qresult.id);
                    intent.putStringArrayListExtra("name", Qresult.name);
                    intent.setClass(IndexActivity.this, qiandao.class);
                    startActivity(intent);
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// 点击 取消 取消对话框
                    break;
                default:
                    break;
            }
        }
    };
    DialogInterface.OnClickListener hello1 = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// 点击 确认，退出程序
                    Intent intent = new Intent();
                    Qresult.num = "88";
                    Qresult.ynum = "47";
                    Qresult.nnum = "41";
                    intent.putExtra("num", Qresult.num);
                    intent.putExtra("ynum", Qresult.ynum);
                    intent.putExtra("nnum", Qresult.nnum);
                    intent.putStringArrayListExtra("id", Qresult.id);
                    intent.putStringArrayListExtra("name", Qresult.name);
                    intent.putStringArrayListExtra("state", Qresult.name);
                    intent.setClass(IndexActivity.this, zhuangtai.class);
                    startActivity(intent);
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// 点击 取消 取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.geren:
                Intent intent = new Intent();
                intent.setClass(IndexActivity.this, geren.class);
                startActivity(intent);
                break;

            case R.id.select_class:
                Intent intent2 = new Intent();
                intent2.setClass(IndexActivity.this, select_class.class);
                startActivity(intent2);
                break;
            case R.id.qiandao:
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, IMAGE);
                break;
            case R.id.zhuangtai:
                Intent intent4 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent4, 2);
                break;
            case R.id.xinxi:
                Intent intent5 = new Intent();
                intent5.setClass(IndexActivity.this, xinxi.class);
                startActivity(intent5);
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
            c.close();
            qiandao1(imagePath, Class11.getID());
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            c.close();
            zhuangtai1(imagePath, Class11.getID());
        }
    }

    public void qiandao1(final String path, final String id1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //qdresult data1=qiandao(path,id1);
                int data = 1;
                String currentThreadName = Thread.currentThread().getName();
                Message message = new Message();
                message.what = 1;
                message.obj = data;
                handler.sendMessage(message);

            }
        }).start();
    }

    public void zhuangtai1(final String path, final String id1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //qdresult data1=zhaungtai(path,id1);
                int data = 2;
                String currentThreadName = Thread.currentThread().getName();
                Message message = new Message();
                message.what = 1;
                message.obj = data;
                handler.sendMessage(message);

            }
        }).start();
    }
}
