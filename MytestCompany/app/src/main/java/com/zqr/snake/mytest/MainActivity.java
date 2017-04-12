package com.zqr.snake.mytest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zqr.snake.mytest.Animator.AnimatorActivity;
import com.zqr.snake.mytest.PathAnimView.PathAnimViewActivity;
import com.zqr.snake.mytest.RecyclerView.RecyclerViewActivity;
import com.zqr.snake.mytest.Retrofit.RetrofitActivity;
import com.zqr.snake.mytest.SwipeRefreshLayout.SwipeRefreshLayoutActivity;
import com.zqr.snake.mytest.broken_lib.BrokenActivity;
import com.zqr.snake.mytest.cameratest.Android6Activity;
import com.zqr.snake.mytest.demo.AnimShopActivity;
import com.zqr.snake.mytest.demo.BannerActivity;
import com.zqr.snake.mytest.demo.ClockActivity;
import com.zqr.snake.mytest.demo.DanmuActivity;
import com.zqr.snake.mytest.TagFlowLayout.FlowLayoutActivity;
import com.zqr.snake.mytest.demo.HookDemoActivity;
import com.zqr.snake.mytest.demo.LinnerlayutDemo;
import com.zqr.snake.mytest.demo.MoveMenu;
import com.zqr.snake.mytest.demo.PullToRefreshLayoutActivity;
import com.zqr.snake.mytest.demo.RxAndroidDemoActivity;
import com.zqr.snake.mytest.demo.dialogActivity;
import com.zqr.snake.mytest.gundongItem.GunDongItemActivity;
import com.zqr.snake.mytest.material_design.MaterialDesignActivity;
import com.zqr.snake.mytest.material_design.SubActivity;
import com.zqr.snake.mytest.photoview.PhotoViewActivity;
import com.zqr.snake.mytest.swipecaptcha.SwipeCaptchaActivity;
import com.zqr.snake.mytest.util.BlurBehind;
import com.zqr.snake.mytest.util.HookUtils;
import com.zqr.snake.mytest.util.LinkTestDemo;
import com.zqr.snake.mytest.util.MyLog;
import com.zqr.snake.mytest.util.OnBlurCompleteListener;
import com.zqr.snake.mytest.util.SizeUtil;
import com.zqr.snake.mytest.viewpager3d.ViewPagerActivity;

import java.io.IOException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;


public class MainActivity extends Activity implements View.OnClickListener,  MoveMenu.OnMenuClickListener {
    TextView txt, txt2;
    int i = 20;

//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            i--;
//            System.out.println("sadaf");
//            txt.setText("wo shi lao da"+i);
//            if (i<0){
//                System.out.println("xiaoyu fushu ");
//            }
//            handler.sendEmptyMessageDelayed(0, 1000);
//
//        }
//
//
//        };


    Button button;
    LinnerlayutDemo mlinnerlayutDemo;
    private RelativeLayout mRrSusMenu;
    private MoveMenu mMMoveMenu;//悬浮图标
    private static MediaPlayer mediaPlayer;
    private SoundPool pool;//dfjksh
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LoopTestUI.start();
//        mediaPlayer  = new MediaPlayer();
//
//        try {
//            AssetFileDescriptor fileDescriptor = getAssets().openFd("haha");
//            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
//                    fileDescriptor.getLength());
//            MyLog.e("77777777","777777777");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        mediaPlayer =  MediaPlayer.create(MainActivity.this, R.raw.coindrop);
//        mediaPlayer.setLooping(false);//是否重复播放


        if (getNetWorkStatus()){
            Toast.makeText(MainActivity.this,"网络连接正常",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,"网络连接错误，请检查",Toast.LENGTH_SHORT).show();
        }
         button = (Button) findViewById(R.id.btn);
        txt = (TextView) findViewById(R.id.txt);
        txt2 = (TextView) findViewById(R.id.txt2);
        // SpannableStringBuilder来调整字体的样式
        String text = String.format("￥%1$s  门市价:￥%2$s", 18.6, 22);
        int z = text.lastIndexOf("门");
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new AbsoluteSizeSpan((int) SizeUtil.Dp2Px(MainActivity.this,18)), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#afafaf")), z, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色
        style.setSpan(new AbsoluteSizeSpan((int) SizeUtil.Dp2Px(MainActivity.this,14)), z, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        txt2.setText(style);

        (findViewById(R.id.tv_Animator)).setOnClickListener(this);
        (findViewById(R.id.tv_dialog_activity)).setOnClickListener(this);
        (findViewById(R.id.tv_Retrofit)).setOnClickListener(this);
        (findViewById(R.id.tv_Danmu)).setOnClickListener(this);
        (findViewById(R.id.tv_android6)).setOnClickListener(this);
        (findViewById(R.id.tv_viewpager)).setOnClickListener(this);
        (findViewById(R.id.tv_chehua)).setOnClickListener(this);
        (findViewById(R.id.tv_clock)).setOnClickListener(this);
        (findViewById(R.id.tv_banner)).setOnClickListener(this);
        (findViewById(R.id.tv_PhotoPicker)).setOnClickListener(this);
        (findViewById(R.id.tv_IndexBar)).setOnClickListener(this);
        (findViewById(R.id.tv_broken_activity)).setOnClickListener(this);
        (findViewById(R.id.tv_gundongxinwentiao)).setOnClickListener(this);
        (findViewById(R.id.tv_layoutmanager)).setOnClickListener(this);
        (findViewById(R.id.tv_photoview)).setOnClickListener(this);
        (findViewById(R.id.btnDelete)).setOnClickListener(this);
        (findViewById(R.id.tv_PathAnimView)).setOnClickListener(this);
        (findViewById(R.id.tv_animshopbuttonView)).setOnClickListener(this);
        (findViewById(R.id.tv_SwipeCaptcha)).setOnClickListener(this);
        (findViewById(R.id.tv_material_design)).setOnClickListener(this);
        (findViewById(R.id.tv_hookTest)).setOnClickListener(this);
        (findViewById(R.id.tv_SwipeRefreshLayout)).setOnClickListener(this);
        (findViewById(R.id.tv_PullToRefreshLayout)).setOnClickListener(this);
        (findViewById(R.id.tv_SoundPool)).setOnClickListener(this);
        mlinnerlayutDemo  = (LinnerlayutDemo) findViewById(R.id.lineardemo);
        mlinnerlayutDemo.init();
        ((TextView)findViewById(R.id.rxandroid)).setOnClickListener(this);
        initSusMenu();//初始化悬浮窗











        mlinnerlayutDemo.setClinkListener(new LinnerlayutDemo.ClickListener() {
            @Override
            public void onclick() {
                System.out.println("点击了");
               txt.setText("点击了");
            }

            @Override
            public void Longclick() {
                System.out.println("长按了");
                txt.setText("长按了");
            }
        });














   //     button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(){
//                    public void run(){
//                        System.out.println("我是线程111");
//                    }
//
//            }
//        });

//        new Thread(){
//            public void run(){
//                handler.sendEmptyMessageDelayed(0,1000);
//            }
//        }.start();

//        new Thread() {
//            public void run() {
//                //handler.sendEmptyMessageDelayed(0,1000);
//
//        }.start();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDelete:
                Toast.makeText(MainActivity.this,"点击删除了", Toast.LENGTH_SHORT).show();
            break;
            case R.id.rxandroid:
                Intent intent = new Intent(MainActivity.this, RxAndroidDemoActivity.class);
                startActivity(intent);
            break;
            case R.id.tv_Animator:
                Intent intent2 = new Intent(MainActivity.this, AnimatorActivity.class);
                startActivity(intent2);
            break;
            case R.id.tv_Retrofit:
                Intent intent3 = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intent3);
            break;
            case R.id.tv_Danmu:
                Intent intent4 = new Intent(MainActivity.this, DanmuActivity.class);
                startActivity(intent4);
            break;
            case R.id.tv_viewpager:
                Intent intent5 = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent5);
            break;
            case R.id.tv_android6:
                Intent intent6 = new Intent(MainActivity.this, Android6Activity.class);
                startActivity(intent6);
            break;
            case R.id.tv_gundongxinwentiao:
                Intent intent7 = new Intent(MainActivity.this, GunDongItemActivity.class);
                startActivity(intent7);
            break;
            case R.id.tv_clock:
                Intent intent8 = new Intent(MainActivity.this, ClockActivity.class);
                startActivity(intent8);
                break;
            case R.id.tv_IndexBar:
                Intent intent9 = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent9);
                break;
            case R.id.tv_dialog_activity:
                BlurBehind.getInstance().execute(MainActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Intent intent10 = new Intent(MainActivity.this, dialogActivity.class);
                        intent10.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                    }
                });
                break;
            case R.id.tv_broken_activity:
                Intent intent11 = new Intent(MainActivity.this, BrokenActivity.class);
                startActivity(intent11);
                break;
            case R.id.tv_PhotoPicker:
                PhotoPickerIntent intent12 = new PhotoPickerIntent(MainActivity.this);
                intent12.setPhotoCount(9);
                intent12.setShowCamera(true);
                startActivityForResult(intent12, 1000);
                break;
            case R.id.tv_layoutmanager:
                Intent intent13 = new Intent(MainActivity.this, FlowLayoutActivity.class);
                startActivity(intent13);
                break;
            case R.id.tv_chehua:
                Intent intent14 = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent14);
                break;
            case R.id.tv_photoview:
                Intent intent15 = new Intent(MainActivity.this, PhotoViewActivity.class);
                startActivity(intent15);
                break;
            case R.id.tv_banner:
                Intent intent16 = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent16);
                break;
            case R.id.tv_SwipeCaptcha:
                Intent intent17 = new Intent(MainActivity.this, SwipeCaptchaActivity.class);
                startActivity(intent17);
                break;
            case R.id.tv_PathAnimView:
                Intent intent18 = new Intent(MainActivity.this, PathAnimViewActivity.class);
                startActivity(intent18);
                break;
            case R.id.tv_animshopbuttonView:
                Intent intent19 = new Intent(MainActivity.this,AnimShopActivity.class);
                startActivity(intent19);
                break;
            case R.id.tv_material_design:
                Intent intent20 = new Intent(MainActivity.this,MaterialDesignActivity.class);
                startActivity(intent20);
                break;
            case R.id.tv_hookTest:
//                这是利用hook技术实现没有在清单文件注册activity实现的跳转
                HookUtils hook = new HookUtils(this, SubActivity.class);
                try {
                    hook.hookAms();
                    hook.hookSystemHandler();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent21 = new Intent(MainActivity.this,HookDemoActivity.class);
                startActivity(intent21);
                break;
            case R.id.tv_SwipeRefreshLayout:
                Intent intent22 = new Intent(MainActivity.this,SwipeRefreshLayoutActivity.class);
                startActivity(intent22);
                break;
            case R.id.tv_SoundPool:
//                playVoice();
                playPool();
//                    mediaPlayer.start();

//

                break;
            case R.id.tv_PullToRefreshLayout:
                Intent intent23 = new Intent(MainActivity.this,PullToRefreshLayoutActivity.class);
                startActivity(intent23);
                break;

        }
    }

    //获取图片的回调
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1000) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                for (String s:photos) {
                    Log.e("sssss","........"+s);
                }
            }
        }
    }

    MoveMenu getSusMenu(){
        return mMMoveMenu;
    }

    //初始化悬浮图标
    private void initSusMenu() {
        mRrSusMenu= (RelativeLayout) findViewById(R.id.rl_main);
        View menuIcon =getLayoutInflater().inflate(R.layout.item_sus_menu, null);
        // 实例化悬浮窗对象，最后一个参数是按钮的View对象
        mMMoveMenu = new MoveMenu(this, this, menuIcon);
        // 设置悬浮窗移动类型
        mMMoveMenu.setMoveType(MoveMenu.MOVETYPE_STOP_IN_LEFT_AND_RIGHT);
        // 设置悬浮窗菜单激活时背景
        mMMoveMenu.setBackgroundColor(0x60000000);
        // 设置悬浮窗靠边停留时的比例
        mMMoveMenu.setStopShowScale(MoveMenu.SHOW_SCALE_FULL);
        mMMoveMenu.setIconSize(getResources().getDimensionPixelSize(R.dimen.sus_icon_width),getResources().getDimensionPixelSize(R.dimen.sus_icon_height));

        //确保已经获得布局的宽高
        mRrSusMenu.postDelayed(new Runnable() {
            @Override public void run() {

                if(mRrSusMenu.getWidth()==0 || mRrSusMenu.getHeight()==0){
                    mRrSusMenu.postDelayed(this,100);
                }else{
                    mMMoveMenu.setParentSize(mRrSusMenu.getWidth(),mRrSusMenu.getHeight());
                    // 显示悬浮窗
                    mMMoveMenu.showInView(mRrSusMenu);
                }
            }
        },100);
        mMMoveMenu.setVisibility(View.VISIBLE);
    }


    //设置是否可见
    void setMoveMenuShow(String picId){
        if (mMMoveMenu!=null){
            mMMoveMenu.setVisibility(View.VISIBLE);
        }

    }

    void setMoveMenuHidden(){
        if (mMMoveMenu!=null){
            mMMoveMenu.setVisibility(View.INVISIBLE);
        }

    }
//悬浮点击的事件
    @Override
    public void onMenuClick() {
//        Toast.makeText(MainActivity.this,"悬浮点击了，666",Toast.LENGTH_LONG).show();
        LinkTestDemo.makeLog(MainActivity.this,"悬浮点击了，666").show();
        MyLog.e("mmmmmmmmm","mmmmmmmmmmmmmm");

    }

    //判断网络状态，记得加权限
//    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    private boolean getNetWorkStatus() {

        boolean netSataus = false;
        ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        cwjManager.getActiveNetworkInfo();

        if (cwjManager.getActiveNetworkInfo() != null) {
            netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
        }

        if (!netSataus) {
            AlertDialog.Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络")
                    .setMessage("是否对网络进行设置？");
            b.setPositiveButton("是", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    if(android.os.Build.VERSION.SDK_INT>10){
                        Intent mIntent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        mIntent.setAction("android.intent.action.VIEW");
                        startActivityForResult(mIntent,0);
                    }else {
                        Intent mIntent = new Intent();
                        ComponentName comp = new ComponentName(
                                "com.android.settings",
                                "com.android.settings.WirelessSettings");
                        mIntent.setAction("android.intent.action.VIEW");
                        mIntent.setComponent(comp);
                        startActivityForResult(mIntent,0);
                    }
                }
            }).setNeutralButton("否", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).show();
        }

        return netSataus;
    }

public void playPool(){
//    指定声音池的最大音频流数目为10，声音品质为5
            pool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
    final int sourceid = pool.load(this, R.raw.c2, 0);//载入音频流，返回在池中的id
    pool.play(sourceid, 1, 1, 0, 0, 1);
}
        public  void playVoice( ){
            try {
//                mediaPlayer  = new MediaPlayer();
//                AssetFileDescriptor fileDescriptor = getAssets().openFd("haha.wav");
//            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
//                    fileDescriptor.getLength());
                mediaPlayer= MediaPlayer.create(this, R.raw.coin);
                mediaPlayer.start();
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        mediaPlayer.start();
//                    }
//                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }












}

//   class th extends Thread{
//       public void run(){
//           for (int i = 0; i < 5; i++){
//           System.out.println("子线程（2)"+i);
//       }
//   }



