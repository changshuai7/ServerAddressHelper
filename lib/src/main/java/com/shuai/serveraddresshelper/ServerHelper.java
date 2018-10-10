package com.shuai.serveraddresshelper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.shuai.serveraddresshelper.bean.AddressBean;
import com.shuai.serveraddresshelper.utils.PrefUtil;
import com.shuai.serveraddresshelper.utils.Util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


/**
 * 服务器地址配置工具类
 */

public class ServerHelper {

    private static Context sAppContext;
    public static boolean sIsDebug;
    private static ServerHelper.Config sConfig;


    /**
     * 初始化服务器地址类型，建议在Application中完成配置
     *
     * @param context
     * @param isDebug
     * @return
     */
    public static ServerHelper.Config init(@NonNull final Context context, boolean isDebug) {
        sAppContext = context;
        sIsDebug = isDebug;
        if (sConfig == null) {
            sConfig = new ServerHelper.Config();
        }
        return sConfig;
    }

    public static class Config {

        private LinkedHashMap map = new LinkedHashMap<String, AddressBean[]>();//存储地址map

        public Config() {
        }

        /**
         * 添加一类型的服务器地址
         *
         * @param key
         * @param value
         * @return
         */
        public Config addServerAddress(String key, AddressBean[] value) {
            map.put(key, value);
            return this;
        }
    }

    /**
     * 获取完整服务器地址
     *
     * @param serverKey   服务器类型标识key
     * @param addressKey  key类型下地址类型标识key
     * @param serverField 服务器地址字段
     * @return
     */
    public synchronized static String getCompleteServerAddress(String serverKey, String addressKey, String serverField) {

        if (Util.isStrNullOrEmpty(serverField)){
            serverField = "";
        }

        AddressBean[] beans = (AddressBean[]) sConfig.map.get(serverKey);

        //如果是生产环境
        if (!sIsDebug) {
            String port = Util.isStrNullOrEmpty(beans[0].getAddressPort()) ? "" : ":" + beans[0].getAddressPort();
            return beans[0].getAddressHost() + port + serverField;
        }

        //如果是非生产环境
        if (beans.length > 0) {
            for (int i = 0; i < beans.length; i++) {
                if (addressKey.equals(beans[i].getAddressKey())) {
                    String port = Util.isStrNullOrEmpty(beans[i].getAddressPort()) ? "" : ":" + beans[i].getAddressPort();
                    return beans[i].getAddressHost() + port + serverField;

                }
            }
        }

        return "";
    }


    /**
     * 自动模式去获取服务器完整地址（通过dialog选择环境后，环境可实现自动识别）
     *
     * @param serverKey   服务器类型标识key
     * @param serverField 服务器地址字段
     * @return
     */
    public synchronized static String getAutoCompleteServerAddress(String serverKey, String serverField) {

        //这样做是为了保证Release下的高效率执行。
        if (!sIsDebug){
            return getCompleteServerAddress(serverKey, "", serverField);
        }

        AddressBean[] beans = (AddressBean[]) sConfig.map.get(serverKey);
        //考虑到对于只配置了两个地址的情况，debug模式下这里是取不到值的，返回0会造成去寻找release地址，产生bug，所以这里默认返回1（即第一个debug地址）
        int anInt = PrefUtil.getInt(sAppContext, serverKey, 1);
        AddressBean bean = beans[anInt];
        bean.getAddressKey();
        return getCompleteServerAddress(serverKey, bean.getAddressKey(), serverField);

    }


    /**
     * 获取服务器地址(仅服务器地址，不包含端口和拼接字段)
     *
     * @param serverKey  服务器类型key
     * @param addressKey key类型下地址类型key
     * @return
     */
    public synchronized static String getHostServerAddress(String serverKey, String addressKey) {
        AddressBean[] beans = (AddressBean[]) sConfig.map.get(serverKey);

        //如果生产环境
        if (!sIsDebug) {
            return beans[0].getAddressHost();//一定是[0]
        }
        //如果是非生产环境
        if (beans.length > 0) {
            for (int i = 0; i < beans.length; i++) {
                if (addressKey.equals(beans[i].getAddressKey())) {
                    return beans[i].getAddressHost();
                }
            }
        }
        return "";
    }

    /**
     * 获取需要选择服务器的Map集合
     *
     * @return
     */
    public static LinkedHashMap<String, AddressBean[]> getChooseEnvMap() {
        LinkedHashMap<String, AddressBean[]> map = sConfig.map;
        LinkedHashMap<String, AddressBean[]> targetMap = new LinkedHashMap();//存储地址map

        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            AddressBean[] value = map.get(key);
            if (value.length > 2) {
                targetMap.put(key, value);
            }
        }
        return targetMap;
    }


}
