package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.adapter.BgAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    ListView listBg,listData;

    List<Integer> data = new ArrayList<>();

    BgAdapter adapter;

    Button btnBottom,btnTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        listBg = findViewById(R.id.listBg);

        for (int i = 0; i < 8;i ++){
            data.add(i);
        }


        adapter = new BgAdapter(data,this);


        listBg.setAdapter(adapter);


        btnBottom = findViewById(R.id.btnBottom);

        btnTop = findViewById(R.id.btnTop);

        btnBottom.setOnClickListener(v->{
            int select = adapter.getNowSelect();
            Log.d("bottom select: - - >",select+"") ;

            if(select < 7){
                adapter.moveBottom();
            }else{
                // 移动到底部了。
            }
        });


        btnTop.setOnClickListener(v->{

            int select = adapter.getNowSelect();

            Log.d("top select: - - >",select+"") ;

            if(select > 0){
                adapter.moveTop();
            }else{
                // 移动到顶部了。
            }
        });

    }
}
