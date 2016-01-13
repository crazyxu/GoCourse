package com.katoa.util;

import android.app.Application;

/**
 * Created by 徐灿 on 2015/4/12
 * Contact 384454501@qq.com
 * Introduce 单例模式，存放全局变量,需修改AndroidManifest.xml
 */
public class MyApplication extends Application{
    private int curWeeks;   //当前周次
    private int curDays;    //当前星期数
    //当前fragment
    private String curFra;
    public String getCurFra() {
        return curFra;
    }
    public void setCurFra(String curFra) {
        this.curFra = curFra;
    }

    //保存ActionBar title
    private String curBarTitle;
    public String getCurBarTitle() {
        return curBarTitle;
    }
    public void setCurBarTitle(String curBarTitle) {
        this.curBarTitle = curBarTitle;
    }

    //菜单项资源id
    private int curMenuId;
    public int getCurMenuId() {
        return curMenuId;
    }
    public void setCurMenuId(int curMenuId) {
        this.curMenuId = curMenuId;
    }




    @Override
    public void onCreate() {
        super.onCreate();
    }
}
