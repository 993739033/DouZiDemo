package com.scode.douzidemo.fragment.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scode.douzidemo.MyApplication;
import com.scode.douzidemo.R;
import com.scode.douzidemo.adapter.base.BasePageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 为pagefragment初始化一些共同的数据
 *
 * Created by 知らないのセカイ on 2017/6/16.
 */

public abstract class BasePageFragment extends Fragment {
    public final static int RECORD_COUNT = 18;//缓存数？
    public int position;//？？根据position创建不同的fragement？？
    private Unbinder mUbinder;
    public View footView;
    public BasePageAdapter mAdapter;
    @BindView(R.id.page_base_fab)
    public FloatingActionButton fab;//浮动按钮
    @BindView(R.id.page_base_fresh)
    public SwipeRefreshLayout srlayout;//下拉刷新控件
    @BindView(R.id.page_base_rv)
    public RecyclerView mrv;//recyclerview

    @SuppressLint("ValidFragment")
    public BasePageFragment(int position) {
        this.position = position;//获取position
    }

    public BasePageFragment() {
        //创建构造方法时需要构建无参的构造方法
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagefragment_base, container, false);
        mUbinder = ButterKnife.bind(view);

        initData();

        initListener();

        return view;
    }


    //初始化监听事件
    private void initListener() {

        //初始化recyclerview的单击长按事件
        onRecyclerViewListener();

        //设置swipe 下拉时刷新事件更新数据
        srlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        //设置floatbutton的点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mrv.getAdapter() != null) {
                    mrv.scrollToPosition(0);
                }
            }
        });

        mrv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果不能滑动且网络可以访问时刷新界面
                if (recyclerView.canScrollVertically(1) && MyApplication.isNetworkAvailable(getContext())) {
                    updateDate();
                    footView.setVisibility(View.VISIBLE);
                    mrv.scrollToPosition(mrv.getAdapter().getItemCount()-1);//??
                }
            }
        });


    }

    public abstract void updateDate();
    public abstract void loadData();
    public abstract void onRecyclerViewListener();
    public abstract void initRecyclerView();


    //初始化数据
    private void initData() {
        srlayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary);//设置颜色变化
        srlayout.setProgressViewOffset(false, 0, 58);//设置progress大小？
        initRecyclerView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUbinder != null) {
            mUbinder.unbind();
        }
    }
}
