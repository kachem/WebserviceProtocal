package com.iobit.kotlindemo.webservice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 *
 * Created by kachem on 2017/7/11.
 */
class MainPresenter(view: IView) : IMainPresenter<IView> {
    override var handler: Handler? = null

    var view: IView? = null
    lateinit var model: MainModel

    init {
        attachView(view)
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                this@MainPresenter.handleMessage(msg)
            }
        }
    }

    override fun handleMessage(msg: Message?) {
        if (msg?.what == 0) {
            val bundle: Bundle? = msg.data
            val result: String = bundle?.getString("result") ?: "没有内容"
            view?.setText(result)
        }
    }

    override fun attachView(v: IView) {
        this.view = v
        model = MainModel(this)
    }

    override fun getInfo() {
        model.getSupportDataSet()
    }

    override fun setInfo(result: String?) {
        if (result != null) {
            val msg: Message = Message()
            val bundle: Bundle = Bundle()
            bundle.putString("result", result)
            msg.data = bundle
            msg.what = 0
            handler?.sendMessage(msg)
        }
    }

    override fun detachView() {
        view = null
    }

}