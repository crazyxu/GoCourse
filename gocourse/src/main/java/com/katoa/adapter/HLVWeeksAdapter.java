package com.katoa.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.katoa.gocourse.R;

/**
 * Created by 徐灿 on 2015/4/10
 * Contact 384454501@qq.com
 * Introduce
 */
public class HLVWeeksAdapter extends BaseAdapter{
    private String[] wTitle;
    private Context mContext;
    private LayoutInflater mInflater;
    private int selectIndex = -1;       //记录当前select项，用以改变（保留）selected背景色
    private int curWeek=0;              //记录当前周，从0开始

    public HLVWeeksAdapter(Context context, String[] wTitle){
        this.mContext = context;
        this.wTitle = wTitle;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        if (wTitle!=null)
            return wTitle.length;
        return 0;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.hlv_weeks_item, null);
            holder.tvWeeks=(TextView)convertView.findViewById(R.id.tv_weeks);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }
        holder.tvWeeks.setText(wTitle[position]);

        //当前日期字体用co_weeks_cur_blue
        if (curWeek==position){
            holder.tvWeeks.setTextColor(mContext.getResources().getColor(R.color.co_weeks_cur_blue));
        }
        return convertView;
    }
    public void setSelectIndex(int i){
        selectIndex=i;
    }
    public void setCurWeek(int i){
        this.curWeek=i;
    }

        private static class ViewHolder {
        private TextView tvWeeks ;
    }
}
