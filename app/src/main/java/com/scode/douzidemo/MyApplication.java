package com.scode.douzidemo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.scode.douzidemo.model.db.GreenDaoUtils;

/**
 * Created by 知らないのセカイ on 2017/6/13.
 */

public class MyApplication extends Application {
    private static  GreenDaoUtils mGreenDaoUtils;
    private static  Context mcontext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=getApplicationContext();//初始化全局的Context
        mGreenDaoUtils = new GreenDaoUtils(getApplicationContext());
    }

    //检查手机是否能够上网
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Context getContext(){
      return mcontext;//通过方法获取全局application
    };

    public static GreenDaoUtils getGreenDaoUtils(){
        return mGreenDaoUtils;
    }

}
