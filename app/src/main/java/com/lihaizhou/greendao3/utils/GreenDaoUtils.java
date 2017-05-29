package com.lihaizhou.greendao3.utils;

/**
 * Created by Administrator on 2017/5/29.
 */

import android.database.sqlite.SQLiteDatabase;

import com.lihaizhou.greendao3.MyApplication;
import com.lihaizhou.greendao3.dao.DaoMaster;
import com.lihaizhou.greendao3.dao.DaoSession;

/**
 * Created by lidongzhi on 2017/2/3.
 */

public class GreenDaoUtils {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static GreenDaoUtils greenDaoUtils;

    private GreenDaoUtils(){}

    public static GreenDaoUtils getSingleTon(){
        if (greenDaoUtils==null){
            greenDaoUtils=new GreenDaoUtils();
        }
        return greenDaoUtils;
    }

    private void initGreenDao(){
        mHelper=new DaoMaster.DevOpenHelper(MyApplication.getApplication(),"greenDaoDB",null);
        db=mHelper.getWritableDatabase();
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        if (mDaoMaster==null){
            initGreenDao();
        }
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        if (db==null){
            initGreenDao();
        }
        return db;
    }

}