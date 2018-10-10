package com.shuai.serveraddresshelper.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shuai.serveraddresshelper.ServerHelper;
import com.shuai.serveraddresshelper.dialog.ChooseServerEnvDialog;
import com.shuai.serveraddresshelper.utils.PrefUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvShowServerAddress;
    private Button mBtnGetServerAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvShowServerAddress = findViewById(R.id.tv_show_server_address);
        mBtnGetServerAddress = findViewById(R.id.btn_get_server_address);
        mBtnGetServerAddress.setOnClickListener(this);

        initChooseEnv();

    }

    /**
     * 弹框选择服务器模式
     */
    private void initChooseEnv() {
        ChooseServerEnvDialog.showDialog(MainActivity.this, new ChooseServerEnvDialog.chooseResult() {
            @Override
            public void onConfirm() {
                int key_2 = PrefUtil.getInt(MainActivity.this, "KEY_2", 0);
                int key_3 = PrefUtil.getInt(MainActivity.this, "KEY_3", 0);

                Toast.makeText(MainActivity.this, "onConfirm执行："+"key2=" + key_2 + ",key3=" + key_3, Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_get_server_address:
                String url1 = ServerHelper.getAutoCompleteServerAddress("KEY_1", "/rrc/sale/demo");
                String url2 = ServerHelper.getAutoCompleteServerAddress("KEY_2", "/rrc/sale/demo");
                String url3 = ServerHelper.getAutoCompleteServerAddress("KEY_3", null);

                mTvShowServerAddress.setText(url1 + "\n" + url2 + "\n" + url3);

                break;
        }

    }
}
