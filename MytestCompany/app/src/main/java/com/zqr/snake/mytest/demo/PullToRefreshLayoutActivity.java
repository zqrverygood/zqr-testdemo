package com.zqr.snake.mytest.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zqr.snake.mytest.PullToRefreshLayout.AbPullToRefreshView;
import com.zqr.snake.mytest.R;
import com.zqr.snake.mytest.util.MyLog;

import java.util.ArrayList;
import java.util.List;

public class PullToRefreshLayoutActivity extends AppCompatActivity implements AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener{
    private AbPullToRefreshView mAbPullToRefreshView;
    private ListView listview;
    private List<String> list;
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            cancleLoading();
            super.handleMessage(msg);
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pull_to_refresh_layout);
           mAbPullToRefreshView = (AbPullToRefreshView) findViewById(R.id.mPullRefreshView);
            mAbPullToRefreshView.setOnHeaderRefreshListener(this);
            mAbPullToRefreshView.setOnFooterLoadListener(this);
//            mAbPullToRefreshView.setPullRefreshEnable(false);//禁止下拉刷新
//            mAbPullToRefreshView.setLoadMoreEnable(false);//禁止上拉刷新
            listview = (ListView) findViewById(R.id.listview);
            list = new ArrayList();
            for (int i = 0; i<10; i++){
                list.add("good boy"+i);
            }
            MyAdapter adapter = new MyAdapter();
            listview.setAdapter(adapter);


        }

        //上拉刷新
        @Override
        public void onFooterLoad(AbPullToRefreshView view) {
            hd.sendEmptyMessageDelayed(0,3000);
        }

        //下拉刷新
        @Override
        public void onHeaderRefresh(AbPullToRefreshView view) {
            hd.sendEmptyMessageDelayed(0,3000);
        }

        private void cancleLoading() {
            mAbPullToRefreshView.onFooterLoadFinish();//上拉刷新结束
            mAbPullToRefreshView.onHeaderRefreshFinish();//下拉刷新结束
        }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(PullToRefreshLayoutActivity.this, R.layout.temp_item, null);
            TextView textView = (TextView) convertView.findViewById(R.id.temp_textview);
            textView.setText(list.get(position));
            return convertView;

        }
    }

    }


