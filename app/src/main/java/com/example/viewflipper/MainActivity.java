package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private List<String> list;
    private Context context;
    private View view;
    private TextView tv_show,tv_showTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        viewFlipper = findViewById(R.id.viewFlipper);

        viewFlipper.clearFocus();

        for (int i=0;i<10;i++){

            view = LayoutInflater.from(context).inflate(R.layout.viewflipper_item,null);
            tv_show = view.findViewById(R.id.tv_show);
            tv_showTwo = view.findViewById(R.id.tv_showTwo);
            tv_show.setText("立元"+i);

            i++;
            tv_showTwo.setText("立元"+i);

            viewFlipper.addView(view);
        }

        viewFlipper.setInAnimation(context,R.anim.come_in);
        viewFlipper.setOutAnimation(context,R.anim.go_out);
        viewFlipper.setFlipInterval(2000);

        // 1、设置幻灯片的形式滚动
        // viewFlipper.startFlipping();

        // 2、设置自动翻页滚动
        viewFlipper.setAutoStart(true);
        viewFlipper.isAutoStart();

    }

}
