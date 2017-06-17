package com.scode.douzidemo.fragment.pagefragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.scode.douzidemo.R;
import com.scode.douzidemo.adapter.PageMoiveAdapter;
import com.scode.douzidemo.adapter.base.BasePageAdapter;
import com.scode.douzidemo.fragment.base.BasePageFragment;
import com.scode.douzidemo.model.bean.SubjectBean;
import com.scode.douzidemo.utils.CacheUtils;
import com.scode.douzidemo.utils.Constants;
import com.scode.douzidemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 知らないのセカイ on 2017/6/17.
 */

public class MoviePageFragment extends BasePageFragment {
    private List<SubjectBean> mdata;
    private Subscriber<List<SubjectBean>> mListSubscriber;

    public MoviePageFragment() {
    }

    @SuppressLint("ValidFragment")
    public MoviePageFragment(int position) {
        super(position);
    }

    @Override
    public void updateDate() {

    }

    @Override
    public void loadData() {
        srlayout.setRefreshing(true);//开启刷新
        //创建订阅者用于刷新数据 和存储数据的操作
        mListSubscriber = new Subscriber<List<SubjectBean>>() {
            @Override
            public void onCompleted() {
                srlayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                srlayout.setRefreshing(false);
            }

            @Override
            public void onNext(final List<SubjectBean> subjectBean) {
                if (subjectBean != null) {
                    LogUtils.e("读取数据成功");
                    mAdapter.upDatas(subjectBean);//刷新数据
                    Observable.just(subjectBean)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(Schedulers.io())
                            .subscribe(new Action1<List<SubjectBean>>() {
                                @Override
                                public void call(List<SubjectBean> subjectBeen) {
                                    CacheUtils.savebean(getActivity(), subjectBean, CacheUtils.DataCache_movie, Constants.MOVIETITLE[position]);
                                }
                            });
                }
            }
        };

        //// TODO: 2017/6/17 请求网络数据
    }

    @Override
    public void onRecyclerViewListener() {
        mAdapter.setOnClickListener(new BasePageAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, String id, String img) {
                //// TODO: 2017/6/17 跳转电影的详细界面
            }

            @Override
            public void ItemLongClickListener(View view, int position) {

            }
        });
    }

    @Override
    public void initRecyclerView() {
        List<SubjectBean> mSubjectbean = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        mrv.setLayoutManager(mLayoutManager);
        mAdapter = new PageMoiveAdapter(mSubjectbean);
        footView = LayoutInflater.from(this.getActivity()).inflate(R.layout.item_footer, mrv, false);

        //读取保存的list
        Observable.just(mdata = CacheUtils.readbean(getActivity(), CacheUtils.DataCache_movie, Constants.MOVIETITLE[position]))
                .subscribeOn(Schedulers.io())//订阅在io线程
                .observeOn(AndroidSchedulers.mainThread())//结果发布在mainthread
                .subscribe(new Action1<List<SubjectBean>>() {//相当于订阅事件 自己创建oberveable 自己订阅事件
                    @Override
                    public void call(List<SubjectBean> subjectBeen) {
                        if (mdata != null) {
                            mAdapter.upDatas(mdata);
                        } else {
                            loadData();//因为这里会自己开启异步 不用当心运行在ui线程中
                        }
                    }
                });


        mAdapter.setFootView(footView);
        mrv.setAdapter(mAdapter);

    }
}
