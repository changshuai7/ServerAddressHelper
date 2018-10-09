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
                                new AddressBean("ADDRESS_KEY_0", "http://release.key1.address0.com", "      ", "Release第1个"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key1.address1.com", "1111", "Debug第1个")
                        })
                .addServerAddress("KEY_2",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release.key2.address0.com", null, "Release第1个"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key2.address1.com", "3333", "Debug第1个"),
                                new AddressBean("ADDRESS_KEY_2", "http://debug.key2.address2.com", "4444", "Debug第2个"),
                                new AddressBean("ADDRESS_KEY_3", "http://debug.key2.address3.com", "5555", "Debug第3个")
                        })
//                .addServerAddress("KEY_21",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_22",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_23",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_24",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_25",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_26",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_27",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_28",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_29",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
//                .addServerAddress("KEY_291",
//                        new AddressBean[]{
//                                new AddressBean("ADDRESS_KEY_0", "http://key2.address0.com", "2222", "Release第1个"),
//                                new AddressBean("ADDRESS_KEY_1", "http://key2.address1.com", "3333", "Debug第1个"),
//                                new AddressBean("ADDRESS_KEY_2", "http://key2.address2.com", "4444", "Debug第2个"),
//                                new AddressBean("ADDRESS_KEY_3", "http://key2.address3.com", "5555", "Debug第3个")
//                        })
                .addServerAddress("KEY_3",
                        new AddressBean[]{
                                new AddressBean("ADDRESS_KEY_0", "http://release.key3.address0.com", null, "Release第1个"),
                                new AddressBean("ADDRESS_KEY_1", "http://debug.key3.address1.com", "7777", "Debug第1个"),
                                new AddressBean("ADDRESS_KEY_2", "http://debug.key3.address2.com", "8888", "Debug第2个"),
                                new AddressBean("ADDRESS_KEY_3", "http://debug.key3.address3.com", "9999", "Debug第3个"),
                                new AddressBean("ADDRESS_KEY_4", "http://debug.key3.address4.com", "19999", "Debug第4个")
                        });


    }
}
