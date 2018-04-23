package com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by lihaifeng on 17/3/1.
 */

public class SharePersistent {


    /**
     * 缓存路径
     */
    public static final String FILE_NAME = "txtCache";

    private static SharePersistent ins;

//    public static SharePersistent getInstance() {
//        return ins == null ? ins = new SharePersistent() : ins;
//    }

    public static SharePersistent getInstance() {
        if (ins == null) {
            synchronized (SharePersistent.class) {
                ins = new SharePersistent();
            }
        }
        return ins;
    }

    /**
     * 设置数据
     *
     * @param context 上下文
     * @param key     存储键
     * @param value   和键对应的值（String类型）
     */
    public boolean put(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 设置数据
     *
     * @param context 上下文
     * @param key     存储键
     * @param value   和键对应的值（int类型）
     */
    public boolean put(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 设置数据
     *
     * @param context 上下文
     * @param key     存储键
     * @param value   和键对应的值（int类型）
     */
    public boolean put(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 设置数据
     *
     * @param context 上下文
     * @param key     存储键
     * @param value   和键对应的值（long类型）
     */
    public boolean put(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 设置数据
     *
     * @param context  上下文
     * @param filename 文件名称
     * @param key      存储键
     * @param value    和键对应的值（String类型）
     */
    public boolean put(Context context, String filename, String key,
                       String value) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 保存集合到sp
     *
     * @param context
     * @param list
     * @return
     */
    public boolean saveArray(Context context, List<String> list) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("Status_size", list.size());

        for (int i = 0; i < list.size(); i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, list.get(i));
        }
        return mEdit1.commit();
    }


    /**
     * 取值
     */
    public List<String> loadArray(Context mContext, List<String> list) {

        SharedPreferences mSharedPreference1 = mContext.getSharedPreferences(FILE_NAME, mContext.MODE_MULTI_PROCESS);
//        list.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);
        for (int i = 0; i < size; i++) {
            list.add(mSharedPreference1.getString("Status_" + i, null));
        }
        return list;
    }

    /**
     * 设置数据
     *
     * @param context  上下文
     * @param filename 文件名称
     * @param key      存储键
     * @param value    和键对应的值（Boolean类型）
     */
    public boolean put(Context context, String filename, String key,
                       boolean value) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 设置数据
     *
     * @param context  上下文
     * @param filename 文件名称
     * @param key      存储键
     * @param value    和键对应的值（int类型）
     */
    public boolean put(Context context, String filename, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0); // 读取文件,如果没有则会创建
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 获取数据
     *
     * @param context 上下文
     * @param key     存储数据时所对应的键
     * @return 和键对应的值（String类型）
     */
    public String getString(Context context, String key, String defValue) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0);
            return settings.getString(key, defValue);
        }
        return null;
    }

    /**
     * 获取数据
     *
     * @param context 上下文
     * @param key     存储数据时所对应的键
     * @return 和键对应的值（int类型）
     */
    public int getInt(Context context, String key, int defVlaue) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0);
        return settings.getInt(key, defVlaue);
    }

    /**
     * 获取数据
     *
     * @param context 上下文
     * @param key     存储数据时所对应的键
     * @return 和键对应的值（long类型）
     */
    public long getLong(Context context, String key, long defValue) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0);
        return settings.getLong(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param context  上下文
     * @param filename 文件名称
     * @param key      存储数据时所对应的键
     * @return 和键对应的值（String类型）
     */
    public String getString(Context context, String filename, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0);
        return settings.getString(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param context  上下文
     * @param filename 文件名称
     * @param key      存储数据时所对应的键
     * @return 和键对应的值（boolean类型）
     */
    public boolean getBoolean(Context context, String filename, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0);
        return settings.getBoolean(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param context  上下文
     * @param filename 文件名称
     * @param key      存储数据时所对应的键
     * @return 和键对应的值（int类型）
     */
    public int getInt(Context context, String filename, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0);
        return settings.getInt(key, defValue);
    }

    /**
     * 清空key对应的数据
     *
     * @param context 上下文
     * @param key     情况特定数据对应的键
     * @return 成功清除返回true 否则 返回false
     */
    public boolean clear(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, 0);
        return settings.edit().clear().commit();
    }

    /**
     * 清空key对应的数据
     *
     * @param context  上下文
     * @param filename
     * @param key      情况特定数据对应的键
     * @return 成功清除返回true 否则 返回false
     */
    public boolean clear(Context context, String filename, String key) {
        SharedPreferences settings = context.getSharedPreferences(filename, 0);
        return settings.edit().remove(key).commit();
    }


}
