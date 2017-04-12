package com.zqr.snake.mytest.gundongItem;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zqr.snake.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class GunDongItemActivity extends AppCompatActivity {
    private List<AdverNotice> datas = new ArrayList<AdverNotice>();


    /**
     * 第二种方法用TextSwitcher完成滚动效果
     */

    private TextSwitcher textSwitcher_tag,textSwitcher_title;
    // 要显示的文本
    String[] tags = new String[]
            {
                    "最新",
                    "最火爆",
                    "HOT",
                    "new"
            };
    // 要显示的文本
    String[] titles = new String[]
            {
                    "瑞士维氏军刀 新品满200-50",
                    "家居家装焕新季，讲199减100！",
                    "带上相机去春游，尼康低至477",
                    "价格惊呆！电信千兆光纤上市"
            };
    private int curStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gundong);
        initData();
        final JDViewAdapter adapter = new JDViewAdapter(datas);
        final JDAdverView tbView = (JDAdverView) findViewById(R.id.jdadver);
        tbView.setAdapter(adapter);
        //开启线程滚东
        tbView.start();


        /**
         * 第二种方法用TextSwitcher完成滚动效果
         */

        textSwitcher_tag= (TextSwitcher) findViewById(R.id.textSwitcher_tag);
        textSwitcher_title= (TextSwitcher) findViewById(R.id.textSwitcher_title);
        textSwitcher_title.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                final TextView tv = new TextView(GunDongItemActivity.this);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv.setPadding(20, 20, 20, 20);
                return tv;
            }
        });
        textSwitcher_tag.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                final TextView tv = new TextView(GunDongItemActivity.this);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                tv.setPadding(30, 20, 20,20);
                tv.setTextColor(Color.RED);
                tv.setBackgroundResource(R.drawable.corner);
                return tv;
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textSwitcher_tag.setText(tags[curStr++ % tags.length]);
                textSwitcher_title.setText(titles[curStr++ % titles.length]);
                handler.postDelayed(this, 1000);
            }
        }, 1000);



    }

    private void initData() {
        datas.add(new AdverNotice("瑞士维氏军刀 新品满200-50","最新"));
        datas.add(new AdverNotice("家居家装焕新季，讲199减100！","最火爆"));
        datas.add(new AdverNotice("带上相机去春游，尼康低至477","HOT"));
        datas.add(new AdverNotice("价格惊呆！电信千兆光纤上市","new"));
    }

}
