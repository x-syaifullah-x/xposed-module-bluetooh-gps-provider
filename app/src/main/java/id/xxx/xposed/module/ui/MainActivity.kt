package id.xxx.xposed.module.ui

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import id.xxx.xposed.module.R
import id.xxx.xposed.module.provider.DatabaseProvider
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        val locationListener = object : LocationListener {
            override fun onProviderDisabled(provider: String) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onLocationChanged(location: Location) {}
        }

        val onNmeaMessageListener = OnNmeaMessageListener { message, timestamp ->
//            Log.i("TAG", "onCreate OnNmeaMessageListener: $message")
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 1L, 1F, locationListener
        )

        locationManager.addNmeaListener(onNmeaMessageListener)

        locationManager.addGpsStatusListener {
//            Log.i("TAG", "addGpsStatusListener onCreate: $it")
        }

        locationManager.registerGnssStatusCallback(object : GnssStatus.Callback() {
            override fun onSatelliteStatusChanged(status: GnssStatus) {
//                Log.i("TAG", "onSatelliteStatusChanged satelliteCount: ${status.satelliteCount}")
            }

            override fun onStarted() {
//                Log.i("TAG", "onStarted: ")
            }

            override fun onStopped() {
//                Log.i("TAG", "onStopped: ")
            }
        })
    }

//        BluetoothAdapter.getDefaultAdapter().enable()
//        val bluetoothDevice =
//            BluetoothAdapter.getDefaultAdapter().getRemoteDevice("08:7F:98:BB:F7:48")
//
//        bluetoothDevice.connectGatt(this, true, object : BluetoothGattCallback() {
//            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
//                super.onConnectionStateChange(gatt, status, newState)
//            }
//        })
//
//        Thread.sleep(5000)
//        try {
//            val a = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(
//                UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
//            )
//            a.connect()
//            val inputStreamReader = InputStreamReader(a.inputStream)
//            val bufferedReader = BufferedReader(inputStreamReader)
//            var read: String? = null
//            val stringBuffer = StringBuffer()
//            while (bufferedReader.run { read = this.readLine(); read } != null) {
//                stringBuffer.append(read);
//
//                Log.i("TAG", "onCreate: ${stringBuffer.toString()}")
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
}