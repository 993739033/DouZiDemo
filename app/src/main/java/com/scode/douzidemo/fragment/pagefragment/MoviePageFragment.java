package com.scode.douzidemo.fragment.pagefragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.scode.douzidemo.R;
import com.scode.douzidemo.adapter.PageMoiveAdapter;
import com.scode.douzidemo.fragment.base.BasePageFragment;
import com.scode.douzidemo.model.bean.SubjectBean;
import com.scode.douzidemo.utils.CacheUtils;
import com.scode.douzidemo.utils.Constants;

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

    }

    @Override
    public void onRecyclerViewListener() {

    }

    @Override
    public void initRecyclerView() {
        List<SubjectBean> mSubjectbean = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        mrv.setLayoutManager(mLayoutManager);
        mAdapter = new PageMoiveAdapter( mSubjectbean);
        footView = LayoutInflater.from(this.getActivity()).inflate(R.layout.item_footer, mrv, false);

        Observable.just(mdata = CacheUtils.readbean(getActivity(), CacheUtils.DataCache_movie, Constants.MOVIETITLE[position]))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SubjectBean>>() {
                    @Override
                    public void call(List<SubjectBean> subjectsBeen) {
                        if (mdata!=null) {
                            mAdapter.upDatas(mdata);
                        }else {
                            loadData();
                        }
                    }
                });

        mAdapter.setFootView(footView);
        mrv.setAdapter(mAdapter);

    }
}
