package com.shuai.serveraddresshelper.dialog;

import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.shuai.serveraddresshelper.bean.AddressBean;
import com.shuai.serveraddresshelper.R;
import com.shuai.serveraddresshelper.ServerHelper;
import com.shuai.serveraddresshelper.utils.PrefUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


/**
 * 选择服务器环境Dialog
 */

public class ChooseServerEnvDialog {

    public static void showDialog(final Context context, final chooseResult chooseResult) {

        /*Release下不选择服务器环境*/
        if (!ServerHelper.sIsDebug){
            chooseResult.onConfirm();
            return;
        }

        /*布局*/
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_choose_server_env, null);

        /*数据和适配器*/
        final LinkedHashMap<String, AddressBean[]> chooseEnvMap = ServerHelper.getChooseEnvMap();
        final ListView listView = dialogView.findViewById(R.id.lv_choose_server);
        final ServerAddressHelperListViewAdapter adapter = new ServerAddressHelperListViewAdapter(context);
        adapter.setDatas(chooseEnvMap);
        listView.setAdapter(adapter);

        /*弹窗*/
        final Dialog mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        mDialog.setContentView(dialogView);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();//3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
        final Window window = mDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        mDialog.show();
        window.setAttributes(lp);//注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        mDialog.setCancelable(false);

        /*点击事件*/
        Button bt_confirm = dialogView.findViewById(R.id.btn_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> keys = chooseEnvMap.keySet();
                Iterator<String> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    int anInt = PrefUtil.getInt(context, key, 0);
                    if (anInt == 0) {

                        Toast.makeText(context, "请选择服务器环境", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                mDialog.dismiss();
                chooseResult.onConfirm();
            }
        });
    }

    public interface chooseResult {
        void onConfirm();
    }

}
