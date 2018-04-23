package com.bbbbbbbbb.a0416httpproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ld
 * Created on 2018/4/19
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("rshansb", Context.MODE_PRIVATE);
        if (sharedPreferences.getInt("son", 0) == 0) {
            sharedPreferences.edit().putInt("son", 100).apply();
        }
    }
}
