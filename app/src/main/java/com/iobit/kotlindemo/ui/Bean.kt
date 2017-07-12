package com.iobit.kotlindemo.ui

import com.google.gson.Gson

/**
 * Created by admin on 2017/6/15.
 */

class Bean private constructor(json: String) {
    init {
        Gson().fromJson(json, Bean::class.java)
    }
}
