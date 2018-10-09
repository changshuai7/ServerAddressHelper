package com.shuai.serveraddresshelper.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.shuai.serveraddresshelper.ServerHelper;
import com.shuai.serveraddresshelper.dialog.ChooseServerEnvDialog;
import com.shuai.serveraddresshelper.utils.PrefUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 弹框选择服务器模式
         */
        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseServerEnvDialog.showDialog(MainActivity.this, new ChooseServerEnvDialog.chooseResult() {
                    @Override
                    public void onConfirm() {
                        int key_2 = PrefUtil.getInt(MainActivity.this, "KEY_2", 0);
                        int key_3 = PrefUtil.getInt(MainActivity.this, "KEY_3", 0);

                        Toast.makeText(MainActivity.this, "key2="+key_2+",key3="+key_3, Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

        /**
         * 获取服务器地址
         */
        findViewById(R.id.btn_get_server_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1 = ServerHelper.getAutoCompleteServerAddress("KEY_1", "/rrc/sale/demo");
                String url2 = ServerHelper.getAutoCompleteServerAddress("KEY_2", "/rrc/sale/demo");
                String url3 = ServerHelper.getAutoCompleteServerAddress("KEY_3", "/rrc/sale/demo");


                Toast.makeText(MainActivity.this, url1+"\n"+url2+"\n"+url3, Toast.LENGTH_LONG).show();


            }
        });
    }
}
