package com.shuai.serveraddresshelper.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.shuai.serveraddresshelper.bean.AddressBean;
import com.shuai.serveraddresshelper.R;
import com.shuai.serveraddresshelper.utils.PrefUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 服务器列表适配器
 */

public class ServerAddressHelperListViewAdapter extends BaseAdapter {

    Context mContext;
    private LinkedHashMap<String, AddressBean[]> mData;

    public ServerAddressHelperListViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(LinkedHashMap<String, AddressBean[]> datas) {
        this.mData = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_choose_server, null);

            holder.mTvDes = convertView.findViewById(R.id.tv_env_description);
            holder.mSpinnerEnv = convertView.findViewById(R.id.spinner_env);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Iterator<Map.Entry<String, AddressBean[]>> it = mData.entrySet().iterator();

        //取出第position个元素
        for (int i = 0; it.hasNext() && i <= position; i++) {
            Map.Entry<String, AddressBean[]> pair = it.next();

            if (i == position) {
                final String key = pair.getKey();
                AddressBean[] value = pair.getValue();
                holder.mTvDes.setText(key);

                ServerAddressHelperSpinnerAdapter spinnerAdapter = new ServerAddressHelperSpinnerAdapter(mContext);
                spinnerAdapter.setDatas(value);
                holder.mSpinnerEnv.setAdapter(spinnerAdapter);

                int anInt = PrefUtil.getInt(mContext, key, 0);
                if (anInt < value.length){
                    holder.mSpinnerEnv.setSelection(anInt);//选择上次选中的
                }else{
                    //有一种可能，如果服务器地址设置为5个，默认选择的为第5个地址(已存储SP)。后续修改为4个。那么运行会角标越界。
                    //针对这种情况，则需要重新去选择
                    holder.mSpinnerEnv.setSelection(0);//防止数组角标越界。
                }

                holder.mSpinnerEnv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        PrefUtil.putInt(mContext, key, position);//存储Spinner选择的位置
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

        }

        return convertView;
    }

    private static class ViewHolder {
        TextView mTvDes;//描述
        Spinner mSpinnerEnv;//Spinner选择器
    }
}
