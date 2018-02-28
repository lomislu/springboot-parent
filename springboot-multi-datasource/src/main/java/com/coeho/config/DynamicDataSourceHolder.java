package com.coeho.config;

/**
 * 动态数据源操作
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-24 16:34
 */
public class DynamicDataSourceHolder {

    // 主数据库标识
    public static final String MASTER = "master";

    // 从数据库标识
    public static final String SLAVE = "slave";

    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public DynamicDataSourceHolder() {
    }

    public static void put(String key) {
        holder.set(key);
    }

    public static String get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }

    public static boolean isMaster() {
        return holder.get().equals(MASTER);
    }
}
