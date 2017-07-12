package com.iobit.kotlindemo.webservice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.iobit.kotlindemo.R

class MainActivity : AppCompatActivity(), IView {
    private var tv: TextView? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv) as TextView?
        presenter = MainPresenter(this)
        presenter?.attachView(this)
        val bt: Button = findViewById(R.id.bt_get) as Button
        bt.setOnClickListener { presenter?.getInfo()}
    }


    override fun setText(str: String) {
        tv?.text = str
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }
}
