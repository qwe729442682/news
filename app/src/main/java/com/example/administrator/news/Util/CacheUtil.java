package com.example.administrator.news.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/13.
 */

public class CacheUtil {
    public static final String CONFIG = "config";
    public static String IS_FIRST = "is_first";

    public static void putBooleanIntoSp(Context context, String Key, boolean b){

        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(Key,b).commit();
    }
    public static boolean getBooleanFromSp(Context context,String Key,boolean b){
        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);

        return sp.getBoolean(Key,b);
    }


}
