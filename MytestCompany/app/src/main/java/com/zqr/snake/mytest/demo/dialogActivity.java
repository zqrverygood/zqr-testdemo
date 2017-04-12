package com.zqr.snake.mytest.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.zqr.snake.mytest.R;
import com.zqr.snake.mytest.util.BlurBehind;

public class dialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        BlurBehind.getInstance()//在你需要添加模糊或者透明的背景中只需要设置这几行简单的代码就可以了
                .withAlpha(90)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        finish();
        return true;
    }
    public void tip(View view)
    {
        Toast.makeText(this, "点击弹出框外部关闭窗口~", Toast.LENGTH_SHORT).show();
    }

}
