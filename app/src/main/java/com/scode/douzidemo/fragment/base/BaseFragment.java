package com.scode.douzidemo.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 知らないのセカイ on 2017/6/15.
 */

public class BaseFragment extends Fragment {
    public Activity mActivity;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    public void bindK(View view) {
        mUnbinder = ButterKnife.bind(this, view);//在父类中绑定View
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();//在父类中解除绑定
        }
    }
}
