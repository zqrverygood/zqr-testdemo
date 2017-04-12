package com.zqr.snake.mytest.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zqr.snake.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends Activity {
    private ViewPager mViewPager;
    private boolean isStop = false;
    private List<ImageView> ivList;
    private LinearLayout llPointGroup;
    private int previousPosition = 0;
    /**
     * 判断是否自动滚动
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        mViewPager = (ViewPager) findViewById(R.id.pointviewpager);
        llPointGroup = (LinearLayout) findViewById(R.id.home_ll_point_group);
        init();

    }

    private void init() {
        ivList = new ArrayList<>();
        int[] imageResIDs = { R.drawable.img1,R.drawable.img2,R.drawable.img3,
                R.drawable.img4,R.drawable.img5,};
        ImageView iv;
        View pointView;
        LinearLayout.LayoutParams params;
        // 生成图片及对应的点
        for (int i = 0; i < imageResIDs.length; i++){
            iv = new ImageView(BannerActivity.this);
            iv.setBackgroundResource(imageResIDs[i]);
            ivList.add(iv);


            pointView = new View(BannerActivity.this);
            params = new LinearLayout.LayoutParams(10,10);
            params.setMargins(0,0,5,0);
            pointView.setLayoutParams(params);
            pointView.setEnabled(false);
            pointView.setBackgroundResource(R.drawable.selector_vpad_spot);
            llPointGroup.addView(pointView);
        }

        MyPagerAdapter mpa = new MyPagerAdapter();
        mViewPager.setAdapter(mpa);

        // 通过这样的计算，得到的值除以图片的集合数目余0，这样正好就显示在开始的位置。
        //int item = (Integer.MAX_VALUE / 2)
           //     - ((Integer.MAX_VALUE / 2) % ivList.size());
        //mViewPager.setCurrentItem(item);
        llPointGroup.getChildAt(previousPosition).setEnabled(true);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                llPointGroup.getChildAt(position % ivList.size()).setEnabled(
                        true);
                llPointGroup.getChildAt(previousPosition).setEnabled(false);
                previousPosition = position % ivList.size();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onResume() {
        isStop = false;
        changUi();
        super.onResume();
    }

    @Override
    public void onPause() {
        isStop = true;
        super.onPause();
    }


    private void changUi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 每两秒钟向主线程发送一条消息, 切换viewpager的界面
                while (!isStop) {
                    SystemClock.sleep(2000);
                    handler.sendEmptyMessage(0);
                    //handler.sendEmptyMessageDelayed(0, 2000);
                }
            }
        }).start();
    }
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            mViewPager.removeView(ivList.get(position % ivList.size()));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mViewPager.addView(ivList.get(position % ivList.size()));
            return ivList.get(position % ivList.size());
        }
    }

}
