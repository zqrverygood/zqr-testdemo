package com.zqr.snake.mytest.Animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zqr.snake.mytest.R;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_demo;
    private MyAnimView myAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_animator);
        (findViewById(R.id.alpha)).setOnClickListener(this);
        (findViewById(R.id.translationX)).setOnClickListener(this);
        (findViewById(R.id.rotation)).setOnClickListener(this);
        (findViewById(R.id.zuhe)).setOnClickListener(this);
        (findViewById(R.id.tv_rest)).setOnClickListener(this);
        myAnimView = (MyAnimView) findViewById(R.id.myanimview);
        tv_demo = (TextView) findViewById(R.id.tv_demo);
        tv_demo.setOnClickListener(this);

    }







    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.translationX:
                //这里我们先是调用了TextView的getTranslationX()方法来获取到当前TextView的translationX的位置，
                // 然后ofFloat()方法的第二个参数传入"translationX"，
                // 紧接着后面三个参数用于告诉系统TextView应该怎么移动
                float curTranslationX = tv_demo.getTranslationX();
                ObjectAnimator animator = ObjectAnimator.ofFloat(tv_demo, "translationX", curTranslationX, -500f, curTranslationX);
                //动画的事件监听可以看到，我们需要实现接口中的四个方法，
                // onAnimationStart()方法会在动画开始的时候调用，
                // onAnimationRepeat()方法会在动画重复执行的时候调用，
                // onAnimationEnd()方法会在动画结束的时候调用，
                // onAnimationCancel()方法会在动画被取消的时候调用。
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.e("aaaaaaaaaaaa","动画开始了");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.e("aaaaaaaaaaaa","动画结束了");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.e("aaaaaaaaaaaa","动画取消了");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Log.e("aaaaaaaaaaaa","动画重复了");
                    }
                });
                animator.setDuration(5000);
                animator.start();
                break;
            case R.id.rotation:
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(tv_demo, "rotation", 0f, 360f);
                animator2.setDuration(5000);
                animator2.start();
                break;
            case R.id.alpha:
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(tv_demo, "alpha", 1f, 0f, 1f);
                animator3.setDuration(5000);
                animator3.start();
                break;
            case R.id.zuhe:

                ObjectAnimator moveIn = ObjectAnimator.ofFloat(tv_demo, "translationX", -500f, 0f);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(tv_demo, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tv_demo, "alpha", 1f, 0f, 1f);
                AnimatorSet animSet = new AnimatorSet();
//              after(Animator anim)   将现有动画插入到传入的动画之后执行
//              after(long delay)   将现有动画延迟指定毫秒后执行
//              before(Animator anim)   将现有动画插入到传入的动画之前执行
//              with(Animator anim)   将现有动画和传入的动画同时执行
                animSet.play(rotate).with(fadeInOut).after(moveIn);
                animSet.setDuration(5000);
                animSet.start();
                break;
            case R.id.tv_demo:
                Toast.makeText(this,"who are you",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_rest:
                myAnimView.cleadPoint();
                break;
        }
    }
}
