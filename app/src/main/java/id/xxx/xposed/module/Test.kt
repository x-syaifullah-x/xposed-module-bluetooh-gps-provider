package id.xxx.xposed.module

class IXposedHookLoadPackage {
//    //        for (method in LocationManager::class.java.declaredMethods) {
////            val hook: XC_MethodHook = object : XC_MethodReplacement() {
////
////                override fun replaceHookedMethod(param: MethodHookParam?): Any {
////                    XposedBridge.log(param?.method?.name)
////                    if (param?.method?.name == "addNmeaListener") {
////                        param.args.forEachIndexed { index, any ->
////                            when (any) {
////                                is OnNmeaMessageListener -> {
////                                    Executors.newFixedThreadPool(1).execute {
////                                        while (true) {
////                                            stringNMEA.forEach {
////                                                any.onNmeaMessage(it, Date().time)
////                                            }
////                                        }
////                                    }
////                                }
////
////                                is GpsStatus.NmeaListener -> {
////                                    Executors.newFixedThreadPool(1).execute {
////                                        while (true) {
////                                            stringNMEA.forEach {
////                                                any.onNmeaReceived(Date().time, it)
////                                            }
////                                        }
////                                    }
////                                }
////                            }
////                        }
//////                        param.result = true
////                    }
////                    return false
////                }
////            }
////            if (Modifier.isPublic(method.modifiers)) {
////                XposedBridge.hookMethod(method, hook)
////            }
////        }
//}
//}
////                override fun beforeHookedMethod(param: MethodHookParam?) {
////                    super.beforeHookedMethod(param)
////                    param?.args?.forEach { methode ->
////                        /* GpsStatus.NmeaListener , OnNmeaMessageListener*/
////                        if (param.method.name == "addNmeaListener") {
////                            param.args.forEach {
////                                if (it is OnNmeaMessageListener) {
////                                    Executors.newFixedThreadPool(1).execute {
////                                        while (true) {
////                                            stringNMEA.forEach { stringNMEA ->
////                                                it.onNmeaMessage(stringNMEA, Date().time)
////                                            }
////                                        }
////                                    }
////                                }
////                            }
////                        }
////                    }
////                }
//
////                override fun afterHookedMethod(param: MethodHookParam?) {
////                    param?.args?.forEach { methode ->
////                        /* GpsStatus.NmeaListener , OnNmeaMessageListener*/
////                        if (param.method.name == "addNmeaListener") {
////                            param.args.forEach {
////                                if (it is OnNmeaMessageListener) {
////                                    Executors.newFixedThreadPool(1).execute {
////                                        while (true) {
////                                            stringNMEA.forEach { stringNMEA ->
////                                                it.onNmeaMessage(stringNMEA, Date().time)
////                                            }
////                                        }
////                                    }
////                                }
////                            }
////                        }
////                    }
////                }
//
////        for (method in LocationManager::class.java.declaredMethods) {
////            val hook: XC_MethodHook = object : XC_MethodHook() {
////                override fun beforeHookedMethod(param: MethodHookParam?) {
////                    super.beforeHookedMethod(param)
////                    param?.args?.forEach {
////                        if (param.method.name == "addNmeaListener") {
////                            /* GpsStatus.NmeaListener , OnNmeaMessageListener*/
////                        }
////                    }
////                }
////
////                override fun afterHookedMethod(param: MethodHookParam?) {
////                    XposedBridge.log("asasassssssssssssssssssss")
////                }
////            }
////            if (Modifier.isPublic(method.modifiers)) {
////                XposedBridge.hookMethod(method, hook)
////            }
////        }
////    }
////        XposedHelpers.findAndHookMethod(
////            cls,
////            "addNmeaListener",
////            OnNmeaMessageListener::class.java,
////            object : XC_MethodHook() {
////                override fun afterHookedMethod(param: MethodHookParam) {
////                    super.afterHookedMethod(param)
////
////                    val onNmeaMessageListener = param.args[0] as OnNmeaMessageListener
////
////
////                    Executors.newFixedThreadPool(1).execute {
////                        while (true) {
////                            stringNMEA.forEach {
////                                onNmeaMessageListener.onNmeaMessage(it, Date().time)
////                            }
////                            Thread.sleep(1000)
////                        }
////                    }
////
////                    param.result = true
////                }
////            )
////    } catch (e: ClassNotFoundException) {
////        e.printStackTrace()
////    }
//
////        for (method in LocationManager::class.java.declaredMethods) {
////            val hook: XC_MethodHook = object : XC_MethodHook() {
////                override fun beforeHookedMethod(param: MethodHookParam?) {
////                    super.beforeHookedMethod(param)
////                    param?.args?.forEach {
////                        if (it is LocationListener) {
////                            listener.add(it)
////                        }
////                    }
////                }
////            }
////            if (Modifier.isPublic(method.modifiers)) {
////                XposedBridge.hookMethod(method, hook)
////            }
////        }
////
////        XposedHelpers.findAndHookMethod(
////            LocationManager::class.java,
////            "registerGnssStatusCallback",
////            GnssStatus.Callback::class.java,
////            object : XC_MethodHook() {
////                @RequiresApi(Build.VERSION_CODES.R)
////                override fun afterHookedMethod(param: MethodHookParam) {
////                    super.afterHookedMethod(param)
////
////                    val svCount = 1
////                    val svidWithFlags = arrayOf(1)
////                    val cn0DbHzs = arrayOf(0.0f)
////                    val elevations = arrayOf(0.0f)
////                    val azimuths = arrayOf(0.0f)
////                    val carrierFrequencies = arrayOf(0.0f)
////                    val basebandCn0DbHzs = arrayOf(0.0f)
//
//
////                        val gnss = XposedHelpers.newInstance(
////                            GnssStatus::class.java,
////                            svCount,
////                            svidWithFlags,
////                            cn0DbHzs,
////                            elevations,
////                            azimuths,
////                            carrierFrequencies,
////                            basebandCn0DbHzs
////                        ) as GnssStatus
////                        GnssStatus::class.java.constructors.forEach {
////                            if (Modifier.isPrivate(it.modifiers)) {
////                                it.isAccessible = true
////                            }
////
////                            it.parameterTypes.forEach {
////                                Thread.sleep(100)
////                                XposedBridge.log(it.name)
////                            }
////                        }
//
//
////                        val clss = Class.forName("android.location.GnssStatus")
////                        val gnss = clss.getDeclaredMethod(
////                            "wrap",
////                            Int::class.java,
////                            IntArray::class.java,
////                            FloatArray::class.java,
////                            FloatArray::class.java,
////                            FloatArray::class.java,
////                            FloatArray::class.java,
////                            FloatArray::class.java,
////                        ).invoke(
////                            null,
////                            svCount,
////                            svidWithFlags,
////                            cn0DbHzs,
////                            elevations,
////                            azimuths,
////                            carrierFrequencies,
////                            basebandCn0DbHzs
////                        ) as GnssStatus
////                        val gnss = clss
////                            .getConstructor(
////                                Int::class.java,
////                                IntArray::class.java,
////                                FloatArray::class.java,
////                                FloatArray::class.java,
////                                FloatArray::class.java,
////                                FloatArray::class.java,
////                                FloatArray::class.java,
////                            ).newInstance(
////                                svCount,
////                                svidWithFlags,
////                                cn0DbHzs,
////                                elevations,
////                                azimuths,
////                                carrierFrequencies,
////                                basebandCn0DbHzs
////                            ) as GnssStatus
//
////                    GnssStatus::class.java.constructors.forEach {
////                        it.isAccessible = true
////                        val SVID_SHIFT = XposedHelpers.getStaticIntField(
////                            GnssStatus::class.java,
////                            "SVID_SHIFT_WIDTH"
////                        )
////                        val CONSTELLATION_SHIFT = XposedHelpers.getStaticIntField(
////                            GnssStatus::class.java,
////                            "CONSTELLATION_TYPE_SHIFT_WIDTH"
////                        )
////
////                        Thread(Runnable {
////                            while (true) {
////                                val gnss = it.newInstance(
////                                    6,
////                                    intArrayOf(
////                                        (1 shl SVID_SHIFT) + (GnssStatus.CONSTELLATION_GPS shl CONSTELLATION_SHIFT) + 7,
////                                        (2 shl SVID_SHIFT) + (GnssStatus.CONSTELLATION_GPS shl CONSTELLATION_SHIFT) + 7,
////                                        (3 shl SVID_SHIFT) + (GnssStatus.CONSTELLATION_GPS shl CONSTELLATION_SHIFT) + 7,
////                                        (4 shl SVID_SHIFT) + (GnssStatus.CONSTELLATION_GPS shl CONSTELLATION_SHIFT) + 7,
////                                        (5 shl SVID_SHIFT) + (GnssStatus.CONSTELLATION_GPS shl CONSTELLATION_SHIFT) + 7,
////                                        (6 shl SVID_SHIFT) + (GnssStatus.CONSTELLATION_GPS shl CONSTELLATION_SHIFT) + 7,
////                                    ),
////                                    floatArrayOf(30.0f, 30.0f, 30.0f, 30.0f, 30.0f, 30.0f),
////                                    floatArrayOf(
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat()
////                                    ),
////                                    floatArrayOf(
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat()
////                                    ),
////                                    floatArrayOf(
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat(),
////                                        Random.nextInt(10, 100).toFloat()
////                                    ),
////                                ) as GnssStatus
////
////                                val mainHandler = Handler(Looper.getMainLooper());
////
////                                val myRunnable = Runnable {
////                                    (param.args[0] as GnssStatus.Callback).onSatelliteStatusChanged(
////                                        gnss
////                                    )
////                                }
////                                mainHandler.post(myRunnable);
////                                val location = Location("gps")
////                                location.latitude = -6.0778
////                                location.longitude = 106.6034
////                                listener.forEach { locationListener ->
////                                    locationListener.onLocationChanged(location)
////                                }
////                                Thread.sleep(10000)
////                            }
////                        }).start()
////                    }
////                }
////            })
////    }
////}
//
////            XposedHelpers.findAndHookMethod(
////                Application::class.java,
////                "attach",
////                Context::class.java,
////                object : XC_MethodHook() {
////                    override fun afterHookedMethod(param: MethodHookParam) {
////                        super.afterHookedMethod(param)
////
////                        val application = param.args[0] as Context
//
////                        val context = FakeGPS(application)
////                        Executors.newFixedThreadPool(1).execute {
////                            Thread.sleep(10000)
////                            while (true){
////                                context.applyCoordinates(52.53063685, 13.36023430)
////                                Thread.sleep(1000)
////                            }
////                        }
////                    }
////                }
////            )
////        }
//
////        XposedHelpers.findAndHookMethod(
////            Application::class.java,
////            "attach",
////            Context::class.java,
////            object : XC_MethodHook() {
////                override fun afterHookedMethod(param: MethodHookParam) {
////                    context = param.args[0] as Context
////
////                    val locationManager = getSystemService(context, LocationManager::class.java)
////
////                    try {
////                        val cls = Class.forName("android.location.LocationManager")
////
////                        XposedHelpers.findAndHookMethod(
////                            cls,
////                            "registerGnssStatusCallback",
////                            GnssStatus.Callback::class.java,
////                            object : XC_MethodHook() {
////                                @RequiresApi(Build.VERSION_CODES.R)
////                                override fun afterHookedMethod(param: MethodHookParam) {
////                                    super.afterHookedMethod(param)
////
////                                    val onNmeaMessageListener = param.args[0] as GnssStatus.Callback
////
////                                    XposedHelpers.findAndHookMethod(
////                                        onNmeaMessageListener::class.java,
////                                        "onSatelliteStatusChanged",
////                                        GnssStatus::class.java,
////                                        object : XC_MethodHook() {
////                                            override fun afterHookedMethod(param: MethodHookParam) {
////                                                super.afterHookedMethod(param)
////
////                                                val a = param.args[0] as GnssStatus
////
////                                                Log.i("TAG", "afterHookedMethod: $a")
////                                            }
////                                        }
////                                    )
////
////                                    val gnssStatus = GnssStatus.Builder()
////                                        .addSatellite(
////                                            GnssStatus.CONSTELLATION_BEIDOU,
////                                            1,
////                                            1f,
////                                            1f,
////                                            1f,
////                                            true,
////                                            true,
////                                            true,
////                                            true,
////                                            1f,
////                                            true,
////                                            1f
////                                        ).build()
//////
//////                                    Executors.newCachedThreadPool().execute {
//////                                        while (true) {
//////                                            onNmeaMessageListener.onSatelliteStatusChanged(gnssStatus)
//////                                            Thread.sleep(1000)
//////                                        }
//////                                    }
////
////                                    param.result = true
////                                }
////                            })
////
////                        XposedHelpers.findAndHookMethod(
////                            cls,
////                            "addGpsStatusListener",
////                            GpsStatus.Listener::class.java,
////                            object : XC_MethodHook() {
////                                override fun afterHookedMethod(param: MethodHookParam) {
////                                    super.afterHookedMethod(param)
////
////                                    val onNmeaMessageListener = param.args[0] as GpsStatus.Listener
////
////
////                                    Executors.newFixedThreadPool(1).execute {
////                                        while (true) {
////                                            onNmeaMessageListener.onGpsStatusChanged(4)
////                                            Thread.sleep(1000)
////                                        }
////                                    }
////
////                                    param.result = true
////                                }
////                            })
////
////                        XposedHelpers.findAndHookMethod(
////                            cls,
////                            "addNmeaListener",
////                            OnNmeaMessageListener::class.java,
////                            object : XC_MethodHook() {
////                                override fun afterHookedMethod(param: MethodHookParam) {
////                                    super.afterHookedMethod(param)
////
////                                    val onNmeaMessageListener =
////                                        param.args[0] as OnNmeaMessageListener
////
////
////                                    Executors.newFixedThreadPool(1).execute {
////                                        while (true) {
////                                            stringNMEA.forEach {
////                                                onNmeaMessageListener.onNmeaMessage(it, Date().time)
////                                            }
////                                            Thread.sleep(1000)
////                                        }
////                                    }
////
////                                    param.result = true
////                                }
////                            })
////                    } catch (e: ClassNotFoundException) {
////                        e.printStackTrace()
////                    }
////                }
////            })
//
////        if (lpparam.packageName == "id.xxx.example" || lpparam.packageName == "com.peterhohsy.nmeatools" || lpparam.packageName == "com.avmobiledev.nmeareader") {
//
////        }
////        }
////    }
////}
}