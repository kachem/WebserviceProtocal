package com.iobit.kotlindemo.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by admin on 2017/6/6.
 */

class TestUtil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        1 shl 3

        var strings = arrayOf("kachem", "much", "xinyue", "wen", "meng")
        var list = strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }
    }

    companion object {
        @JvmField
        var isOk: Boolean = false
    }

    fun Int.shl(x: Int): Int {
        return this + x
    }

    class User {

        companion object {
            @JvmStatic
            var num: Int = 3
        }
    }
}
