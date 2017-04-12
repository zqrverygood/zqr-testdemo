package com.zqr.snake.mytest.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zqr.snake.mytest.R;

public class AnimShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_shop);
        final AnimShopButton animShopButton = (AnimShopButton) findViewById(R.id.btnReplenish);
        animShopButton.setReplenish(true);
    }

}
