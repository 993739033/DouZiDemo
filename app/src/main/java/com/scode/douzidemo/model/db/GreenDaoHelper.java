package com.scode.douzidemo.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 此类用于创建数据库提供一些实例
 *
 * Created by 知らないのセカイ on 2017/6/13.
 */

public class GreenDaoHelper {
    private static final String dbName = "douzi_db";//创建数据库
    private static SQLiteDatabase db;
    private static GreenDaoHelper mInstance;
    private DaoMaster.DevOpenHelper mOpenHelper;//DevOpenHelper 实例
    private static  DaoMaster mDaoMaster ;//DaoMaster 实例
    private static  DaoSession mDaoSession; //DaoSession 实例
    private Context mContext ;

    private GreenDaoHelper(Context context) {//设置为private 防止通过new 实例化获取GreenDaoHelper 单例模式
        mContext=context;
        mOpenHelper = new DaoMaster.DevOpenHelper(context, dbName, null);//实例化
        db=mOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);//实例化
        mDaoSession = mDaoMaster.newSession();//实例化
    }

    public static GreenDaoHelper getInstance(Context context) {
        //单例模式
        if (mInstance == null) {
            synchronized (GreenDaoHelper.class) {
                mInstance = new GreenDaoHelper(context);
                return mInstance;
            }
        }
        return mInstance;
    }

    public static DaoSession getmDaoSession(){
        return mDaoSession;
    }

    public static SQLiteDatabase getDb(){
        return db;
    }


}
