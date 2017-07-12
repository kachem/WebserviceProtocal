package com.iobit.kotlindemo.webservice

/**
 * Created by admin on 2017/7/11.
 */
interface IMainPresenter<in T> : BasePresenter<T> {

    fun getInfo()

    fun setInfo(result: String?)
}