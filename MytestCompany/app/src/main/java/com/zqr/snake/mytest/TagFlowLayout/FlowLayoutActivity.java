package com.zqr.snake.mytest.TagFlowLayout;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zqr.snake.mytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class FlowLayoutActivity extends Activity {
    private List<String> list = new ArrayList<>();
    private EditText ed_tag;
    private TextView tv_ok;
    private TagAdapter tagAdapter;
    private TagFlowLayout tagFlowLayout;
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);
        list.add("Hello");list.add("dsgfdgfdg");list.add("Hefgdfgdfllo");list.add("阿斯达所多");list.add("发鬼地方个");
        list.add("Hello");list.add("还会更好");list.add("会感觉更好");list.add("你麻痹");list.add("新东方风格");
        list.add("还发货吗");list.add("附近购买界面");list.add("给对方回复更好");list.add("就，看来");list.add("额外热帖");
        list.add("橘红颗粒");list.add("二姑夫的红米");
        final LayoutInflater mInflater = LayoutInflater.from(FlowLayoutActivity.this);
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.tagFlowLayout);
        ed_tag = (EditText) findViewById(R.id.ed_tag);
        tv_ok = (TextView) findViewById(R.id.tv_ok);

        tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };

        tagFlowLayout.setAdapter(tagAdapter);


        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                Toast.makeText(FlowLayoutActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed_tag.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    list.add(s);
                    ed_tag.setText("");
                    tagAdapter.notifyDataChanged();
                }
            }
        });
    }



}
