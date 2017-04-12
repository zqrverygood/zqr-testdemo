package com.zqr.snake.mytest.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.zqr.snake.mytest.R;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class HookDemoActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_hook_test);
    }
}
