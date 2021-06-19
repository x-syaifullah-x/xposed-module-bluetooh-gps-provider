package id.xxx.xposed.module

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.location.GpsStatus
import android.location.Location
import android.location.LocationManager
import android.location.OnNmeaMessageListener
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import de.robv.android.xposed.*
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import id.xxx.xposed.module.provider.DatabaseProvider
import java.util.*
import java.util.concurrent.Executors

class MainLoadPackage : IXposedHookLoadPackage {

    @RequiresApi(Build.VERSION_CODES.N)
    fun replaceAddNmeaListener(clazz: Class<*>, context: Context) {

        XposedHelpers.findAndHookMethod(
            clazz,
            "addNmeaListener",
            GpsStatus.NmeaListener::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    param.args.forEach { onNmeaMessageListener ->
                        if (onNmeaMessageListener is GpsStatus.NmeaListener) {
                            context.contentResolver.registerContentObserver(
                                DatabaseProvider.CONTENT_URI,
                                true,
                                object : ContentObserver(Handler(Looper.getMainLooper())) {
                                    override fun onChange(selfChange: Boolean) {
                                        super.onChange(selfChange)
                                        onNmeaMessageListener.onNmeaReceived(
                                            Date().time,
                                            DatabaseProvider.getDataNmea(context)
                                        )
                                    }
                                })
                            param.result = true
                        }
                    }
                    return true
                }
            }
        )

        XposedHelpers.findAndHookMethod(
            clazz,
            "addNmeaListener",
            OnNmeaMessageListener::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    param.args.forEach { onNmeaMessageListener ->
                        if (onNmeaMessageListener is OnNmeaMessageListener) {
                            context.contentResolver.registerContentObserver(
                                DatabaseProvider.CONTENT_URI,
                                true,
                                object : ContentObserver(Handler(Looper.getMainLooper())) {
                                    override fun onChange(selfChange: Boolean) {
                                        super.onChange(selfChange)
                                        onNmeaMessageListener.onNmeaMessage(
                                            DatabaseProvider.getDataNmea(context),
                                            Date().time
                                        )
                                    }
                                })
                            param.result = true
                        }
                    }
                    return true
                }
            }
        )

        XposedHelpers.findAndHookMethod(
            clazz,
            "addNmeaListener",
            OnNmeaMessageListener::class.java,
            Handler::class.java,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    param.args.forEach { onNmeaMessageListener ->
                        if (onNmeaMessageListener is OnNmeaMessageListener) {
                            context.contentResolver.registerContentObserver(
                                DatabaseProvider.CONTENT_URI,
                                true,
                                object : ContentObserver(Handler(Looper.getMainLooper())) {
                                    override fun onChange(selfChange: Boolean) {
                                        super.onChange(selfChange)
                                        onNmeaMessageListener.onNmeaMessage(
                                            DatabaseProvider.getDataNmea(context),
                                            Date().time
                                        )
                                    }
                                })
                            param.result = true
                        }
                    }
                    return true
                }
            }
        )

//        XposedHelpers.findAndHookMethod(
//            clazz,
//            "addNmeaListener",
//            Executor::class.java,
//            OnNmeaMessageListener::class.java,
//            object : XC_MethodHook() {
//                override fun beforeHookedMethod(param: MethodHookParam?) {
//                    super.beforeHookedMethod(param)
//                }
//
//                override fun afterHookedMethod(param: MethodHookParam?) {
//                    super.afterHookedMethod(param)
//                }
//            }
//        )
    }

    private val receiverLocationUpdate = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when {
                intent?.action == "BTGPS_LOCUPDATE" && intent.hasExtra("EXTRA_LOCATION") -> {
                    val location = intent.getParcelableExtra<Location>("EXTRA_LOCATION")
                }
            }
        }
    }

    private val receiverServiceUpdate = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                "de.mobilej.btgps.Service" -> {
                    val running = intent.getBooleanExtra("RUNNING", false)
                    val initializing = intent.getBooleanExtra("INITIALIZING", false)
                    val connected = intent.getBooleanExtra("CONNECTED", false)
                    val validFix = intent.getBooleanExtra("VALIDFIX", false)
                    val workRunning = intent.getBooleanExtra("WORKER_RUNNING", false)
                    DatabaseProvider.setIsRunning(context, workRunning && validFix)
                }
            }
        }
    }

    private val receiverStatusUpdate = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                "BTGPS_STATDATE" -> {
                    when {
                        intent.hasExtra("BTGPS_NMEA") -> {
                            val dataNMEA = intent.getStringExtra("BTGPS_NMEA")
                            DatabaseProvider.setDataNmea(context, dataNMEA)
                        }

                        intent.hasExtra("BTGPS_PRNS") &&
                                intent.hasExtra("BTGPS_SNRS") &&
                                intent.hasExtra("BTGPS_ELEVATIONS") &&
                                intent.hasExtra("BTGPS_AZIMUTHS") &&
                                intent.hasExtra("BTGPS_EPHEMERIS") &&
                                intent.hasExtra("BTGPS_ALMANAC") &&
                                intent.hasExtra("BTGPS_USED_IN_FIX") &&
                                intent.hasExtra("EXTRA_USED_IN_FIX_PRNS") -> {

                            val dataPRNS = intent.getIntArrayExtra("BTGPS_PRNS")
                            val dataSNRS = intent.getFloatArrayExtra("BTGPS_SNRS")
                            val dataELAVATION = intent.getFloatArrayExtra("BTGPS_ELEVATIONS")
                            val dataAZIMUTHS = intent.getFloatArrayExtra("BTGPS_AZIMUTHS")
                            val dataEPHEMERIS = intent.getIntExtra("BTGPS_EPHEMERIS", 0)
                            val dataALMANAC = intent.getIntExtra("BTGPS_ALMANAC", 0)
                            val dataUsedInFix = intent.getIntExtra("BTGPS_USED_IN_FIX", 0)
                            val dataUsedInFixPRNS =
                                intent.getIntArrayExtra("EXTRA_USED_IN_FIX_PRNS")

                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedHelpers.findAndHookMethod(
            Application::class.java, "attach", Context::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)

                    val context = (param.args[0] as Context)
                    if (lpparam.packageName != "de.mobilej.btgps" && DatabaseProvider.getIsRunning(
                            context
                        )
                    ) {
                        replaceAddNmeaListener(
                            Class.forName(LocationManager::class.java.name),
                            context
                        )
                    }
                }
            }
        )


        if (lpparam.packageName == "de.mobilej.btgps") {
            XposedHelpers.findAndHookMethod(
                Application::class.java, "attach", Context::class.java, object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)

                        val context = (param.args[0] as Context)

                        context.registerReceiver(
                            receiverLocationUpdate, IntentFilter("BTGPS_LOCUPDATE")
                        )

                        context.registerReceiver(
                            receiverStatusUpdate, IntentFilter("BTGPS_STATDATE")
                        )

                        context.registerReceiver(
                            receiverServiceUpdate, IntentFilter("de.mobilej.btgps.Service")
                        )
                    }
                }
            )
        }
    }
}
