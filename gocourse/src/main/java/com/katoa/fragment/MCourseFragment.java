package com.katoa.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.katoa.adapter.HLVDaysAdapter;
import com.katoa.adapter.HLVWeeksAdapter;
import com.katoa.gocourse.R;
import com.katoa.ui.HorizontalListView;
import com.katoa.util.Constant;
import com.katoa.util.MyApplication;

/**
 * Created by 徐灿 on 2015/4/13
 * Contact 384454501@qq.com
 * 我的课表
 */
public class MCourseFragment extends Fragment {
    private HorizontalListView hlvWeeks;    //横向ListVIew，显示周次
    private HorizontalListView hlvDays;     //横向ListVIew，显示一周天次

    //HLVAdapter对象
    private HLVWeeksAdapter weeksAdapter;
    private HLVDaysAdapter daysAdapter;

    //记录select本周次,0表示第一周
    private int secWeeks;
    //记录当前周次
    private int curWeeks;
    //记录本日次，0表示星期一
    private int secDays;
    //记录当前日次
    private int curDays;

    private View rootView;


    MyApplication app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_mcourse, container, false);
        initApp();
        initStatic();
        initHLV();
        return rootView;
    }

    public static MCourseFragment newInstance(Bundle args) {
        MCourseFragment fragment = new MCourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MCourseFragment() {
        // Required empty public constructor
    }

    void initApp(){
        app=(MyApplication)getActivity().getApplication();
        app.setCurBarTitle("我的课表");
        app.setCurMenuId(R.menu.menu_main);
        //更新菜单，sdk 11以下需要调用supportInvalidateOptionsMenu
        if (Build.VERSION.SDK_INT>=11)
            getActivity().invalidateOptionsMenu();
        else
            getActivity().supportInvalidateOptionsMenu();
    }

    //处理一些常量
    void initStatic(){
        //自动获取当前周次（服务器获取，或者推算）及星期数(系统获取)，暂时处理...
        curDays=0;
        curWeeks=0;
        //默认选择项为当前标准日期
        secWeeks=curWeeks;
        secDays=curDays;

    }

    //初始化HorizontalListView，显示周次和日次
    void initHLV(){
        //获取HorizontalListView组件
        hlvWeeks=(HorizontalListView)rootView.findViewById(R.id.hlv_weeks);
        hlvDays=(HorizontalListView)rootView.findViewById(R.id.hlv_days);

        //实例化HLVAdapter
        weeksAdapter=new HLVWeeksAdapter(getActivity(), Constant.arrWeeks);
        daysAdapter=new HLVDaysAdapter(getActivity(),Constant.arrDays);

        //设置Adapter
        hlvWeeks.setAdapter(weeksAdapter);
        hlvDays.setAdapter(daysAdapter);

        //设置标准周次和星期数
        weeksAdapter.setCurWeek(curWeeks);
        daysAdapter.setCurDays(curDays);

        //记录select索引，并更新adapter
        weeksAdapter.setSelectIndex(secWeeks);
        daysAdapter.setSelectIndex(secDays);

        //更新ui
        weeksAdapter.notifyDataSetChanged();
        daysAdapter.notifyDataSetChanged();

        //设置监听
        hlvWeeks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secWeeks=position;
                //记录select索引，并更新adapter
                weeksAdapter.setSelectIndex(position);
                weeksAdapter.notifyDataSetChanged();

            }
        });
        hlvDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //记录select索引，并更新adapter
                secDays=position;
                daysAdapter.setSelectIndex(position);
                daysAdapter.notifyDataSetChanged();
            }
        });
    }



}
