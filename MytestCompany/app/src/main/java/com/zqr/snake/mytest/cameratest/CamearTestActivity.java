package com.zqr.snake.mytest.cameratest;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.zqr.snake.mytest.R;

public class CamearTestActivity extends AppCompatActivity implements View.OnClickListener,RectOnCamera.IAutoFocus{
    private CameraSurfaceView mCameraSurfaceView;
    private RectOnCamera mRectOnCamera;
    private Button takePicBtn, xuzhaun;
    private Button reset;
    private AnimationDrawable animationDrawable;
    private ImageView mNewTextImage;
    private float cwx = 100f;
    private float lastwx = 0f;

    private boolean isClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camear_test);
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.cameraSurfaceView);
        mRectOnCamera = (RectOnCamera) findViewById(R.id.rectOnCamera);
        takePicBtn= (Button) findViewById(R.id.takePic);
        reset= (Button) findViewById(R.id.reset);
        xuzhaun= (Button) findViewById(R.id.xuzhaun);
        mRectOnCamera.setIAutoFocus(this);
        takePicBtn.setOnClickListener(this);
        xuzhaun.setOnClickListener(this);
        reset.setOnClickListener(this);


        mNewTextImage = (ImageView)findViewById(R.id.new_text_image);
        animationDrawable=(AnimationDrawable)mNewTextImage.getDrawable();
        animationDrawable.start();


    }

    @Override
    public void autoFocus() {
        mCameraSurfaceView.setAutoFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePic:
                mCameraSurfaceView.takePicture();
                break;
            default:
                break;
            case R.id.reset:
                stratAnimator();
                break;
            case R.id.xuzhaun:
                stratXuanzhuanAnimator();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void stratXuanzhuanAnimator() {
        lastwx = cwx;
        cwx -= 100f;
        float curTranslationX = mNewTextImage.getTranslationX();
        float curX = mNewTextImage.getX();
        Log.e("aaaaaaaaaa","curTranslationX"+curTranslationX+"....x="+curX);
        Log.e("aaaaaaaaaa","lastwx"+lastwx+"....cwx="+cwx);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mNewTextImage, "translationX",-lastwx, -cwx);
        animator.setDuration(3000);
        animator.start();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void stratAnimator() {
        float curTranslationX = mNewTextImage.getTranslationX();
        float curX = mNewTextImage.getX();
        Log.e("aaaaaaaaaa","curTranslationX"+curTranslationX+"....x="+curX);
        Log.e("aaaaaaaaaa","lastwx"+lastwx+"....cwx="+cwx);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mNewTextImage, "translationX",-lastwx, -cwx);
        animator.setDuration(3000);
        animator.start();
        lastwx = cwx;
        cwx += 100f;
    }
}
