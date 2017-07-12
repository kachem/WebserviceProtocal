package com.iobit.kotlindemo.webservice

/**
 *
 * Created by kachem on 2017/7/11.
 */
class MainModel(val presenter: MainPresenter) {


    fun getSupportDataSet() {
        Thread({
            val str: String? = WebServiceHelper.INSTANCE.getSupportCity()
            presenter.setInfo(str)
        }).start()
        //val str:String? =

    }
}