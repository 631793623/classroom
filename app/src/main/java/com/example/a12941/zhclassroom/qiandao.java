package com.example.a12941.zhclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class qiandao extends AppCompatActivity implements View.OnClickListener {
private ImageView back1;
private TextView zong1,dao1,wei1;
private ListView s_list;
    private List<Map<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiandao);
        back1=findViewById(R.id.back1);
        zong1=findViewById(R.id.zong1);
        dao1=findViewById(R.id.dao1);
        wei1=findViewById(R.id.wei1);
        s_list=findViewById(R.id.s_list);
        String num = getIntent().getStringExtra("num");
        String ynum = getIntent().getStringExtra("ynum");
        String nnum = getIntent().getStringExtra("nnum");

        zong1.setText(num);
        dao1.setText(ynum);
        wei1.setText(nnum);



        ArrayList id = getIntent().getStringArrayListExtra("id");
        ArrayList name = getIntent().getStringArrayListExtra("name");

        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> info = new HashMap<>();

            info.put("id", i);
            info.put("name", "zz");
            info.put("state", "旷课");
            data.add(info);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(qiandao.this, data, R.layout.item_student, new String[]{"id", "name", "state"}, new int[]{R.id.id1, R.id.sname, R.id.state});
        s_list.setAdapter(simpleAdapter);
        back1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(qiandao.this,IndexActivity.class);
        startActivity(intent);
    }



}
