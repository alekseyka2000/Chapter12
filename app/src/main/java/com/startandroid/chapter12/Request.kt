package com.startandroid.chapter12

import com.google.gson.Gson
import java.net.URL

class Request (val lat:Double = 0.01,
        val lon:Double = 0.01){

        private val url2 = "https://weather.cit.api.here.com/weather/1.0/report.json?product=observation&latitude=$lat&longitude=$lon&oneobservation=true&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg"

    fun execute(): String {
        val JsonResult = URL(url2).readText()
        //return Gson().fromJson(JsonResult, Forecast::class.java)
        return JsonResult
    }
}