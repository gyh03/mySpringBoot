package com.gyh.base.spring.db;

/**
 * @author guoyanhong
 * @date 2018/9/18 14:40
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> dbIds = new ThreadLocal<>();

    public static void setDbId(String dbId) {
        dbIds.set(dbId);
    }

    public static String getDbId() {
        return dbIds.get();
    }

    public static void clearDb() {
        dbIds.remove();
    }
}
