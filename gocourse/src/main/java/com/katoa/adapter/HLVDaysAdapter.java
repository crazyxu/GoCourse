package com.katoa.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.katoa.gocourse.R;
import com.katoa.util.Tools;

/**
 * Created by 徐灿 on 2015/4/10
 * Contact 384454501@qq.com
 * Introduce
 */
public class HLVDaysAdapter extends BaseAdapter{
    private String[] mTitles;
    private Context mContext;
    private LayoutInflater mInflater;
    //记录select索引
    private int selectIndex = -1;
    //记录当前标准星期数
    private int curDays=0;

    public HLVDaysAdapter(Context context, String[] titles){
        this.mContext = context;
        this.mTitles = titles;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        if (mTitles!=null)
            return mTitles.length;
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
            convertView = mInflater.inflate(R.layout.hlv_days_item, null);
            holder.tvDays=(TextView)convertView.findViewById(R.id.tv_days);
            holder.tvTimes=(TextView)convertView.findViewById(R.id.tv_times);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        //如果position为当前选择项，则定义为setSelected状态，通过selector改变其颜色
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }

        //如果position为当前标准日期，改变其颜色co_weeks_cur_blue
        if (curDays==position)
            holder.tvDays.setTextColor(mContext.getResources().getColor(R.color.co_weeks_cur_blue));

        //填充值
        holder.tvDays.setText(mTitles[position]);
        holder.tvTimes.setText(Tools.getDataByWeeksAndDays(Tools.getCurWeeks(),Tools.getCurDays()));
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvDays ;
        private TextView tvTimes;
    }

    //在Activity中通过setCurDays设置当前星期数，然后使用notifyDataSetChanged更新UI
    public void setCurDays(int i){
        this.curDays=i;
    }
    public void setSelectIndex(int i){
        this.selectIndex=i;
    }

}
