package com.baizhi.util;

import java.util.UUID;

/**
 * Created by dell on 2018/10/25.
 */
public class UUIDUtils {
    /**
     * 生成文件名
     */

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
