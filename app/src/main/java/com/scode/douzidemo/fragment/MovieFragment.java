package com.scode.douzidemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scode.douzidemo.R;
import com.scode.douzidemo.fragment.base.BaseFragment;
import com.scode.douzidemo.fragment.pagefragment.MoviePageFragment;
import com.scode.douzidemo.utils.Constants;
import com.scode.douzidemo.utils.SpUtils;
import com.scode.douzidemo.view.animation.DepthPageTransformer;

import butterknife.BindView;

/**
 * 获取shareprefence 中获取moivetitle数组并赋值给constants，给viewpager设置adapter
 *
 * Created by 知らないのセカイ on 2017/6/15.
 */

public class MovieFragment extends BaseFragment {
    @BindView(R.id.fragment_movie_tablayout)
    TabLayout movie_tablayout;
    @BindView(R.id.fragment_movie_vp)
    ViewPager movie_vp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] stringArray = SpUtils.getStringArray(getActivity(), Constants.MOVIEKEY);
        if (stringArray != null && stringArray.length > 1) {
            Constants.MOVIETITLE = SpUtils.getStringArray(getActivity(), Constants.MOVIEKEY);
            Constants.CHANGELABEL_MOVIE = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        bindK(view);//调用父类绑定view
        if (Constants.MOVIETITLE.length<=5){
            movie_tablayout.setTabMode(TabLayout.MODE_FIXED);
        }
        else{
            movie_tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        
        initData();
        return view;
    }

    private void initData() {
        movie_vp.setAdapter(new MoviePageAdapter(getChildFragmentManager()));
        movie_vp.setPageTransformer(true,new DepthPageTransformer());//true 表示倒序 false 表示顺序
        movie_vp.setOffscreenPageLimit(Constants.MOVIETITLE.length);

        movie_tablayout.setupWithViewPager(movie_vp);
    }

    class MoviePageAdapter extends FragmentStatePagerAdapter
    {
        public MoviePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MoviePageFragment(position);//// TODO: 2017/6/15 ？？这里需要new 一个moivepagefragment 传入position？？
        }

        @Override
        public int getCount() {
            return Constants.MOVIETITLE.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return Constants.MOVIETITLE[position];
        }
    }

}
