package com.iobit.kotlindemo.webservice

import android.os.Handler
import android.os.Message

/**
 *
 * Created by kachem on 2017/7/11.
 */
interface BasePresenter<in T> {
    var handler: Handler?

    fun handleMessage(msg: Message?)

    fun attachView(v: T)

    fun detachView()
}