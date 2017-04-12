package com.zqr.snake.mytest.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zqr.snake.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutActivity extends AppCompatActivity {
 GZoomSwifrefresh refreshLayout;
    RecyclerView recyclerView;
    /**context*/
    Context context;
    /**下部的加载标记*/
    boolean footIsLoad = false;
    /**间隔，从可视到需要加载的间隔*/
    static int FOOTNUM = 3;
    SwipeRefreshLayout s;

    /**访问页数*/
    int pageRead = 0;

    /**访问数目*/
    int readNum = 10;


    /**测试用*/
    TempAdapter tempAdapter;

    Handler hd = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            cancleLoading();
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        context = SwipeRefreshLayoutActivity.this;
        refreshLayout = (GZoomSwifrefresh) findViewById(R.id.tempfrag_swiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycleview);

     ;
        initRefreshLayout();
        //初始化recycleview
        initRecycleView();
        //开始加载
        //   loadData();

    }


    /**初始化刷新控件*/
    private void initRefreshLayout() {
        refreshLayout.setColorSchemeColors(Color.YELLOW,Color.RED,Color.GREEN,Color.BLUE);
        refreshLayout.setOnRefreshListener(new GZoomSwifrefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                loadData();
                //cancleLoading();
                refreshLayout.setBottomRefreshing(false);
                Snackbar.make(recyclerView,"下拉刷新了",Snackbar.LENGTH_SHORT).show();
                hd.sendEmptyMessageDelayed(0,3000);
            }
        });

        refreshLayout.setBottomColorSchemeColors(Color.GREEN,Color.BLUE,Color.YELLOW,Color.RED);
        //在这里新增上拉刷新方法
        refreshLayout.setOnBottomRefreshListenrer(new GZoomSwifrefresh.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                Snackbar.make(recyclerView,"上拉刷新了",Snackbar.LENGTH_SHORT).show();
                hd.sendEmptyMessageDelayed(0,3000);

            }
        });
    }


    /**加载新数据*/
    private void loadNewData() {
        Snackbar.make(recyclerView,"需要加载了",Snackbar.LENGTH_SHORT).show();

    }

    /**取消加载*/
    public void cancleLoading()
    {
        if(refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);
        refreshLayout.setBottomRefreshing(false);
    }





    /**初始化recycleview*/
    private void initRecycleView() {
//        datas.results=new ArrayList<>();
        //   adapterone  = new AndroidMainAdapter(datas,context);
        List<String> tempS = new ArrayList<>();
        for(int i = 0; i<10 ;i++)
        {
            tempS.add("GZ"+i);
        }
        tempAdapter = new TempAdapter(context,tempS);
        //recyclerView.setAdapter(adapterone);
        recyclerView.setAdapter(tempAdapter);
        LinearLayoutManager man = new LinearLayoutManager(context);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(man);

    }





}
