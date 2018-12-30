package com.example.a12941.zhclassroom;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class select_class extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back1;
    private ListView c_list;
    private List<Map<String, Object>> data;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        back1 = findViewById(R.id.back1);
        c_list = findViewById(R.id.c_list);

        back1.setOnClickListener(this);
        c_list.setOnItemClickListener(this);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                String currentThreadName = Thread.currentThread().getName();
                int data1=(int)msg.obj;
                data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    HashMap<String, Object> info = new HashMap<>();

                    info.put("id", i);
                    info.put("name", "语文");
                    info.put("teacher", "余立功");
                    data.add(info);
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(select_class.this, data, R.layout.item_class, new String[]{"id", "name", "teacher"}, new int[]{R.id.id1, R.id.cname, R.id.tname});
                c_list.setAdapter(simpleAdapter);
                return false;
            }
        });
        getall();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(select_class.this, IndexActivity.class);
        startActivity(intent);
    }

    public void getall() {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView ID1=view.findViewById(R.id.id1);
        TextView CNAME=view.findViewById(R.id.cname);
        TextView TNAME=view.findViewById(R.id.tname);
        Class11.setID(ID1.getText().toString());
        Class11.setNAME(CNAME.getText().toString());
        Intent intent=new Intent();
        intent.setClass(select_class.this,IndexActivity.class);
        startActivity(intent);
    }
}
