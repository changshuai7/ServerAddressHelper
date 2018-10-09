package com.shuai.serveraddresshelper.bean;

import java.io.Serializable;

/**
 * 服务器地址bean
 */

public class AddressBean implements Serializable {

    private String addressKey;  //标识
    private String addressHost; //地址
    private String addressPort; //端口
    private String addressDescription;//描述

    public AddressBean() {

    }

    public AddressBean(String addressKey, String addressHost, String addressPort, String addressDescription) {
        this.addressKey = addressKey;
        this.addressHost = addressHost;
        this.addressPort = addressPort;
        this.addressDescription = addressDescription;
    }

    public String getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(String addressKey) {
        this.addressKey = addressKey;
    }

    public String getAddressHost() {
        return addressHost;
    }

    public void setAddressHost(String addressHost) {
        this.addressHost = addressHost;
    }

    public String getAddressPort() {
        return addressPort;
    }

    public void setAddressPort(String addressPort) {
        this.addressPort = addressPort;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }
}
