package com.startandroid.chapter12

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.location.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lon = 0.0
    var lat = 0.0
    lateinit var forecastView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastView = findViewById(R.id.description)
        forecastView.setText("Push here for start")

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        forecastView.setOnClickListener{
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    if (location != null) {
                        lon = location.longitude
                        lat = location.latitude
                    }
                    else forecastView.setText("Problem getting location")
                }
            forcastRequest()
        }
    }

    fun forcastRequest() {
        doAsync() {

            val forecast = Request(lat,lon).execute()
            uiThread { forecastView.setText("$forecast \n$lat\n$lon") }
        }
    }
}
