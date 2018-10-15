package com.shuai.serveraddresshelper;

import android.content.Context;
import android.support.annotation.ColorInt;
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
    public static ServerHelper.Config sConfig;


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

        private LinkedHashMap<String, AddressBean[]> map = new LinkedHashMap();//存储地址map
        private LinkedHashMap<String, AddressBean[]> envMap = new LinkedHashMap();//存储存在多个debug地址的map

        public LinkedHashMap<String, AddressBean[]> getEnvMap() {
            return envMap;
        }
        public LinkedHashMap<String, AddressBean[]> getMap() {
            return map;
        }

        private @ColorInt  int mThemeColor;//主题色

        public int getThemeColor() {
            return mThemeColor;
        }

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

        public Config setThemeColor(@ColorInt  int themeColor){
            this.mThemeColor = themeColor;
            return this;
        }

        public void build(){
            initMap();
        }

        private  void initMap(){
            Set<String> keys = map.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                AddressBean[] value = map.get(key);
                if (value.length > 2) {
                    envMap.put(key, value);
                }
            }
        }
    }



    /**
     * 获取完整服务器地址（Debug下低效）
     *
     * @param serverKey   服务器类型标识key
     * @param addressKey  key类型下地址类型标识key
     * @param addressPort 自定义的端口号(优先)
     * @param spliceField 服务器地址字段
     * @return
     */
    public synchronized static String getCompleteServerAddress(String serverKey, String addressKey, String addressPort, String spliceField) {
        if (Util.isStrNullOrEmpty(spliceField)){
            spliceField = "";
        }
        AddressBean[] beans = (AddressBean[]) sConfig.map.get(serverKey);

        //如果是Release环境:
        if (!sIsDebug) {
            String port = Util.isStrNullOrEmpty(beans[0].getAddressPort()) ? "" : ":" + beans[0].getAddressPort();
            return beans[0].getAddressHost() + port + spliceField;
        }

        //如果是Debug环境（循环，低效）
        if (beans.length > 0) {
            for (int i = 0; i < beans.length; i++) {
                if (addressKey.equals(beans[i].getAddressKey())) {
                    String port = Util.isStrNullOrEmpty(beans[i].getAddressPort()) ? "" : ":" + beans[i].getAddressPort();
                    if (!Util.isStrNullOrEmpty(addressPort)){
                        port = ":" + addressPort;
                    }
                    return beans[i].getAddressHost() + port + spliceField;

                }
            }
        }

        return "";
    }


    public synchronized static String getCompleteServerAddress(String serverKey, String addressKey, String spliceField){
        return getCompleteServerAddress(serverKey,addressKey,null,spliceField);
    }


    /**
     * 自动模式去获取服务器完整地址（通过dialog选择环境后，环境可实现自动识别）
     *
     * @param serverKey   服务器类型标识key、
     * @param addressPort 自定义的端口号(优先)
     * @param spliceField 服务器地址字段
     * @return
     */
    public synchronized static String getAutoCompleteServerAddress(String serverKey, String addressPort, String spliceField) {
        if (Util.isStrNullOrEmpty(spliceField)){
            spliceField = "";
        }
        AddressBean[] beans = (AddressBean[]) sConfig.map.get(serverKey);

        //如果是Release环境:
        if (!sIsDebug){
            String port = Util.isStrNullOrEmpty(beans[0].getAddressPort()) ? "" : ":" + beans[0].getAddressPort();
            return beans[0].getAddressHost() + port + spliceField;
        }

        //如果是Debug环境
        AddressBean bean;
        if (sConfig.envMap.keySet().contains(serverKey)){//这是多个debug地址的情况下（这里因为读取了SP，效率会有所降低）
            int anInt = PrefUtil.getInt(sAppContext, serverKey, 1);
            if (anInt < beans.length){//防止数组角标越界
                bean = beans[anInt];
            }else{
                bean = beans[1];
            }

        }else{//一个release一个debug的情况（高效）
            bean = beans[1];
        }

        String port = Util.isStrNullOrEmpty(bean.getAddressPort()) ? "" : ":" + bean.getAddressPort();
        if (!Util.isStrNullOrEmpty(addressPort)){
            port = ":" + addressPort;
        }
        return bean.getAddressHost() + port + spliceField;


    }

    public synchronized static String getAutoCompleteServerAddress(String serverKey, String spliceField){
        return getAutoCompleteServerAddress(serverKey,null,spliceField);
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
//    public static LinkedHashMap<String, AddressBean[]> getChooseEnvMap() {
//        LinkedHashMap<String, AddressBean[]> map = sConfig.map;
//        LinkedHashMap<String, AddressBean[]> targetMap = new LinkedHashMap();//存储地址map
//
//        Set<String> keys = map.keySet();
//        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()) {
//            String key = iterator.next();
//            AddressBean[] value = map.get(key);
//            if (value.length > 2) {
//                targetMap.put(key, value);
//            }
//        }
//        return targetMap;
//    }




}
