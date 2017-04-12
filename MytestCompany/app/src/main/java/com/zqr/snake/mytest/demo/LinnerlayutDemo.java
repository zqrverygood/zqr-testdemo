package com.zqr.snake.mytest.demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zqr.snake.mytest.R;

/**
 * Created by Administrator on 2016/3/9.
 */
public class LinnerlayutDemo extends LinearLayout {

    public Button mBtn;

    /**
     * 这是要回调的函数，有两个方法
     */
    public interface ClickListener{
            void onclick();
            void Longclick();
    }

    private ClickListener mClinkListener;
    //点击的方法，里面的参数是事件类型，就是自己定义的接口
    public void setClinkListener(ClickListener listener){
     mClinkListener = listener;
       // init(this);
    }


    /**
     * 初始化点击事件，里面有linearlayout和button的点击事件和长按事件
     */
    public void init ( ){
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mClinkListener != null){
                    mClinkListener.Longclick();
                }
                return true;
                //该方法的返回值为一个boolean类型的变量，当返回true时，表示已经完整地处理了这个事件，
                // 并不希望其他的回调方法再次进行处理；
                // 当返回false时，表示并没有完全处理完该事件，更希望其他方法继续对其进行处理。
            }
        });
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClinkListener != null) {
                    mClinkListener.onclick();
                }
            }
        });

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mClinkListener.onclick();
            }
        });

        mBtn.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClinkListener.Longclick();
                return true;
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LinnerlayutDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinnerlayutDemo(Context context) {
        super(context);
    }

    public LinnerlayutDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
