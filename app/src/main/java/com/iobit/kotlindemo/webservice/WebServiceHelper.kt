package com.iobit.kotlindemo.webservice

import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.io.IOException

/**
 *
 * Created by kachem on 2017/7/11.
 */
class WebServiceHelper private constructor() {

    companion object {
        var INSTANCE: WebServiceHelper = WebServiceHelper()
            private set

        val serviceNameSpace = "http://WebXml.com.cn/"
        val methodgetProvince = "getSupportProvince"
        val WSDL = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx"
    }

    fun getSupportCity(): String? {
        val request: SoapObject = SoapObject(serviceNameSpace, methodgetProvince)
        val envelope: SoapSerializationEnvelope = SoapSerializationEnvelope(SoapSerializationEnvelope.VER11)
        envelope.bodyOut = request
        envelope.dotNet = true
        val httpSE: HttpTransportSE = HttpTransportSE(WSDL)
        try {
            httpSE.call(serviceNameSpace + methodgetProvince, envelope)
            val resultObj: SoapObject = envelope.response as SoapObject
            val sb: StringBuilder = StringBuilder()
            for (i in 0..resultObj.propertyCount - 1) {
                sb.append(resultObj.getPropertyAsString(i)).append("\n")
            }

            return sb.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}