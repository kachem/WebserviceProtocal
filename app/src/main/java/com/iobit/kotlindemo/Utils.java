package com.iobit.kotlindemo;

import android.content.Context;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by admin on 2017/6/15.
 */

public class Utils {

    public static String getDefaultConfig(Context context) {
        InputStream in = context.getResources().openRawResource(R.raw.ad_config);
        if (in == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString().replaceAll("\t", "").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void test(){
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
    }
}
