package com.shuai.serveraddresshelper.demo;

import android.app.Application;

import com.shuai.serveraddresshelper.bean.AddressBean;
import com.shuai.serveraddresshelper.ServerHelper;

/**
 * Created by changshuai on 2018/10/9.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ServerHelper.init(this, BuildConfig.DEBUG)
                .addServerAddress("KEY_1",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release0.key1.com", "      ", "Release地址"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key1.001.com", "1111", "第1个Debug地址")
                        })
                .addServerAddress("KEY_2",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release0.key2.address0.com", "", "Release地址"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key2.001.com", "3333", "第1个Debug地址"),
                                new AddressBean("ADDRESS_KEY_2", "http://debug.key2.002.com", "4444", "第2个Debug地址"),
                                new AddressBean("ADDRESS_KEY_3", "http://debug.key2.003.com", "5555", "第3个Debug地址")
                        })
                .addServerAddress("KEY_3",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release0.key3.address0.com", null, "Release地址"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key3.001.com", "7777", "第1个Debug地址"),
                                new AddressBean("ADDRESS_KEY_2", "http://debug.key3.002.com", "8888", "第2个Debug地址"),
                                new AddressBean("ADDRESS_KEY_3", "http://debug.key3.003.com", "9999", "第3个Debug地址"),
                                new AddressBean("ADDRESS_KEY_4", "http://debug.key3.004.com", "19999", "第4个Debug地址")
                        })
                .setThemeColor(getResources().getColor(R.color.colorChooseEnvDialog))
                .build();


    }
}
