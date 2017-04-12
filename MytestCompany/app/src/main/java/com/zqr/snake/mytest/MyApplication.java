package com.zqr.snake.mytest;

import android.app.Application;

import com.zqr.snake.mytest.material_design.SubActivity;
import com.zqr.snake.mytest.util.HookUtils;
import com.zqr.snake.mytest.util.MyLog;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //判断是debug还是release
        MyLog.syncIsDebug(getApplicationContext());
//        这是利用hook技术实现没有在清单文件注册activity实现的跳转
//        HookUtils hook = new HookUtils(this, SubActivity.class);
//        try {
//            hook.hookAms();
//            hook.hookSystemHandler();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
