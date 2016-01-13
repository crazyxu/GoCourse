package com.katoa.gocourse;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.katoa.fragment.MCourseFragment;
import com.katoa.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 徐灿 on 2015/4/13
 * Contact 384454501@qq.com
 * 我的课表
 */
public class MainActivity extends ActionBarActivity {
    private MyApplication app;
    //DrawerLayout对象
    private DrawerLayout drawer;
    //fragment内容
    private RelativeLayout drawerLayout;
    //ActionBar
    private ActionBar bar;
    //实现箭头
    private ActionBarDrawerToggle drawerToggle;

    ///侧滑部分
    private ListView drawerLv;

    private String[] drawerLvTitle=new String[]{"我的课表","添加课表","个人信息","关于"};
    private int[] drawerLvIcon=new int[]{R.drawable.ic_my_course,R.drawable.ic_add_course,R.drawable.ic_user_info,R.drawable.ic_about_app};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
        initBar();
        initDrawer();
        initDrawerLv();

    }
    //初始化与MyApplication相关内容
    void initApp(){
        app=(MyApplication)getApplication();
    }
    //初始化ActionBar
    void initBar(){
        //获取兼容ActionBar
        bar=getSupportActionBar();
        //需要Home箭头
        bar.setDisplayHomeAsUpEnabled(true);
        //自定义标题
        bar.setTitle(getResources().getString(R.string.my_course));
        //设置背景颜色，需要用drawable，而不是color
        bar.setBackgroundDrawable(getResources().getDrawable(R.mipmap.mcourse_bar_bg));
        bar.show();
    }

    //初始化侧滑栏listView
    void initDrawerLv(){
        drawerLv=(ListView)super.findViewById(R.id.drawer_lv);

        //SimpleAdapter数据
        List<Map<String,Object>> listData=new ArrayList<Map<String,Object>>();
        for (int i=0;i<drawerLvTitle.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put(drawerLvTitle[i],drawerLvIcon[i]);
            listData.add(map);
        }
        //SimpleAdapter
        SimpleAdapter adapter=new SimpleAdapter(this,listData,R.layout.drawer_menu_lv_item,
                new String[]{"title","icon"},new int[]{R.id.iv_icon,R.id.tv_title});
        //设置adapter
        drawerLv.setAdapter(adapter);
        //设置监听
        drawerLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    //初始化DrawerLayout
    void initDrawer(){
        drawer = (DrawerLayout) super.findViewById(R.id.dl_drawer);
        drawerLayout= (RelativeLayout) super.findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawer,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                bar.setTitle(app.getCurFra());
                // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                bar.setTitle(getResources().getString(R.string.app_name));
                //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer.setDrawerListener(drawerToggle);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, MCourseFragment.newInstance(null));
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.notifyAll();
        return true;
    }
    //每次调用invalidateOptionsMenu时会执行此方法
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //更新ActionBar title
        bar.setTitle(app.getCurBarTitle());
        ///更新菜单
        getMenuInflater().inflate(app.getCurMenuId(), menu);
        menu.notifyAll();
        return super.onPrepareOptionsMenu(menu);
    }

    //菜单监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
