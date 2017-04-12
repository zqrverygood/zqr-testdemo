package com.zqr.snake.mytest.material_design;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zqr.snake.mytest.R;
import com.zqr.snake.mytest.util.MyLog;


/**
 * Author       : yanbo
 * Date         : 2015-06-02
 * Time         : 10:15
 * Description  :
 */
public class SubActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_input_delete);
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("详情界面");




        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.e("aaaaaaaaaaaa", "aaaaaaaaa");

                } else {

                }
            }
        };
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what =1;
//                handler.sendMessage(message);
//            }
//        }).start();

        new Thread(){
            public void run(){
                Message message = new Message();
                message.what =1;
                handler.sendMessage(message);
            }
        }.start();






    }


}
