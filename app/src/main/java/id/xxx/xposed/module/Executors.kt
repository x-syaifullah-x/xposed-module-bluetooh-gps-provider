package id.xxx.xposed.module

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Executors constructor(
    private val mainThread: Executor,
    private val newSingleThread: Executor,
    private val newCachedThread: Executor
) {

    constructor() : this(
        mainThread = MainThreadExecutor(),
        newSingleThread = Executors.newSingleThreadExecutor(),
        newCachedThread = Executors.newCachedThreadPool()
    )

    fun newSingleThread(): Executor = newSingleThread

    fun newFixedThread(THREAD_COUNT: Int): Executor = Executors.newFixedThreadPool(THREAD_COUNT)

    fun mainThread(): Executor = mainThread

    fun newCachedThread() = newCachedThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}