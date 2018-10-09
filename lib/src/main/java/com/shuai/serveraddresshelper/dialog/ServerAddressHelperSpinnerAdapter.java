package com.shuai.serveraddresshelper.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shuai.serveraddresshelper.bean.AddressBean;
import com.shuai.serveraddresshelper.R;
import com.shuai.serveraddresshelper.utils.Util;

/**
 * 某个服务器环境下，不同服务器地址适配器
 */

public class ServerAddressHelperSpinnerAdapter extends BaseAdapter {

    Context mContext;
    private AddressBean[] mData;

    public ServerAddressHelperSpinnerAdapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(AddressBean[] datas) {
        this.mData = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.spinner_item_choose_server_env, null);
            holder.mTv = (TextView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //没有描述Description的情况下，选择host地址
        String noticeMsg = Util.isStrNullOrEmpty(mData[position].getAddressDescription()) ? mData[position].getAddressHost() : mData[position].getAddressDescription();

        if (position == 0) {
            noticeMsg = "请选择";
        }

        holder.mTv.setText(noticeMsg);

        return convertView;
    }

    private static class ViewHolder {
        TextView mTv;
    }
}
