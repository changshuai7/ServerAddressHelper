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
                                new AddressBean("ADDRESS_KEY_0", "http://release0.key1.com", "      ", "Release第1个"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key1.001.com", "1111", "Debug第1个")
                        })
                .addServerAddress("KEY_2",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release0.key2.address0.com", "", "Release第1个"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key2.001.com", "3333", "Debug第1个"),
                                new AddressBean("ADDRESS_KEY_2", "http://debug.key2.002.com", "4444", "Debug第2个"),
                                new AddressBean("ADDRESS_KEY_3", "http://debug.key2.003.com", "5555", "Debug第3个")
                        })
                .addServerAddress("KEY_3",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release0.key3.address0.com", null, "Release第1个"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key3.001.com", "7777", "Debug第1个"),
                                new AddressBean("ADDRESS_KEY_2", "http://debug.key3.002.com", "8888", "Debug第2个"),
                                new AddressBean("ADDRESS_KEY_3", "http://debug.key3.003.com", "9999", "Debug第3个"),
                                new AddressBean("ADDRESS_KEY_4", "http://debug.key3.004.com", "19999", "Debug第4个")
                        });


    }
}
