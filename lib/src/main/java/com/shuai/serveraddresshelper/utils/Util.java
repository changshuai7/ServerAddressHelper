package com.shuai.serveraddresshelper.utils;

import android.support.annotation.Nullable;

/**
 * 简易工具类
 */

public class Util {

    /**
     * 判断String是否为空（内部trim校验）
     *
     * @param string
     * @return
     */
    public static boolean isStrNullOrEmpty(@Nullable String string) {
        return string == null || string.trim().length() == 0;
    }
}
