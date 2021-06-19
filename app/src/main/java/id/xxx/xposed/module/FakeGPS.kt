package id.xxx.xposed.module

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.location.LocationProvider
import android.os.SystemClock
import androidx.core.content.ContextCompat

class FakeGPS(private val context: Context) {
    private val locationManager =
        ContextCompat.getSystemService(context, LocationManager::class.java)

    fun applyCoordinates(longitude: Double?, latitude: Double?) {
        if (locationManager?.isProviderEnabled(GPS_MOCK_PROVIDER) == true) {
            val altitude = 0.0
            val accuracy = 3.0f
            val timestamp = System.currentTimeMillis()
            try {
                val location = Location(LocationManager.GPS_PROVIDER)
                location.latitude = latitude!!
                location.longitude = longitude!!
                location.altitude = altitude
                location.accuracy = accuracy
                location.time = timestamp
                location.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                locationManager.setTestProviderLocation(GPS_MOCK_PROVIDER, location)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val GPS_MOCK_PROVIDER = "gps"
    }

    init {
        try {
            if (locationManager?.isProviderEnabled(GPS_MOCK_PROVIDER) == true) {
                locationManager.addTestProvider(
                    GPS_MOCK_PROVIDER, false, false,
                    false, false, true, false, false, 0, 5
                )
                locationManager.setTestProviderEnabled(GPS_MOCK_PROVIDER, true)
                locationManager.setTestProviderStatus(
                    GPS_MOCK_PROVIDER,
                    LocationProvider.AVAILABLE,
                    null,
                    System.currentTimeMillis()
                )
            }
        } catch (e:Exception){
            locationManager?.removeTestProvider("gps")
        }
    }
}