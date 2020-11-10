package com.professionalandroid.apps.baemin_clone_android.src

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat


class GpsTracker(context: Context) : Service(), LocationListener {
    private val mContext: Context
    var now_location: Location? = null
    var latitude = 0.0
    var longitude = 0.0
    protected var locationManager: LocationManager? = null

    fun getLocation(): Location? {
        try {
            locationManager =
                mContext.getSystemService(LOCATION_SERVICE) as LocationManager?
            val isGPSEnabled =
                locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled =
                locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                val hasFineLocationPermission = ContextCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                    hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
                ) {
                } else return null
                if (isNetworkEnabled) {
                    locationManager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(),
                        this
                    )
                    if (locationManager != null) {
                        now_location =
                            locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (now_location != null) {
                            latitude = now_location!!.latitude
                            longitude = now_location!!.longitude
                        }
                    }
                }
                if (isGPSEnabled) {
                    if (now_location == null) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(),
                            this
                        )
                        if (locationManager != null) {
                            now_location =
                                locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (now_location != null) {
                                latitude = now_location!!.latitude
                                longitude = now_location!!.longitude
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("@@@", "" + e.toString())
        }
        return now_location
    }


    override fun onProviderDisabled(provider: String) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onLocationChanged(p0: Location) {}

    override fun onStatusChanged(
        provider: String,
        status: Int,
        extras: Bundle
    ) {
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@GpsTracker)
        }
    }

    companion object {
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10
        private const val MIN_TIME_BW_UPDATES = 1000 * 60 * 1.toLong()
    }

    init {
        mContext = context
        getLocation()
    }
}