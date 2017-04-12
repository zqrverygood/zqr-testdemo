package com.zqr.snake.mytest.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zqr.snake.mytest.R;

/**
 * Author       : yanbo
 * Date         : 2015-06-01
 * Time         : 15:09
 * Description  :
 */
public class GridLayoutFragment extends Fragment {
    private View mParentView;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.share_fragment, container, false);
        return mParentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mParentView.findViewById(R.id.recycler_view);
//        这是普通的GridLayout布局
//        mRecyclerView.setLayoutManager( new StaggeredGridLayoutManager(3,
//                StaggeredGridLayoutManager.VERTICAL));



//        这两种写法显示的效果是一致的，但是注意StaggeredGridLayoutManager构造的
// 第二个参数传一个orientation，如果传入的是StaggeredGridLayoutManager.VERTICAL代表有多少列；
// 那么传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行，比如本例如果改为：
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
    }
}
