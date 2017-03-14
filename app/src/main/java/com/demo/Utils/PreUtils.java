package com.demo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wb-qjl256634 on 2017/3/13.
 */

public class PreUtils {

    public static void setBoolean(Context context,String Key,boolean config){

        SharedPreferences sp = context.getSharedPreferences("Config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(Key,config).commit();
    }
    public static boolean getBoolean(Context context,String Key){

        SharedPreferences sp = context.getSharedPreferences("Config", Context.MODE_PRIVATE);
        return sp.getBoolean(Key, true);
    }
    public static void setString(Context context,String Key,String config){

        SharedPreferences sp = context.getSharedPreferences("Config", Context.MODE_PRIVATE);
        sp.edit().putString(Key,config).commit();
    }
    public static String getString(Context context,String Key){

        SharedPreferences sp = context.getSharedPreferences("Config", Context.MODE_PRIVATE);
        return sp.getString(Key, "");
    }

}
