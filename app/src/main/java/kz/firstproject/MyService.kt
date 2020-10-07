package kz.firstproject

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MyService : Service() {

    public val LOG_TAG = "MyService()"
    lateinit var es: ExecutorService

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate")
        es = Executors.newFixedThreadPool(2);
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand")
        someTask()
//        val time = intent!!.getIntExtra(ServiceActivity.PARAM_TIME, 1)
//        val task = intent!!.getIntExtra(ServiceActivity().PARAM_TASK, 0)

//        val mr = MyRun(startId, time, task)
//        es.execute(mr)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(LOG_TAG, "onBind")
        return null
    }

    internal class MyRun(var startId: Int, var time: Int, var task: Int) : Runnable {
        lateinit var intent: Intent
        override fun run() {
            Looper.prepare()
            intent = Intent(ServiceActivity().BROADCAST_ACTION)
            Log.d(ServiceActivity().LOG_TAG, "MyRun#$startId start, time = $time")
            try {
                // сообщаем о старте задачи
//                intent.putExtra(ServiceActivity().PARAM_TASK, task)
//                intent.putExtra(ServiceActivity().PARAM_STATUS, ServiceActivity().STATUS_START)
//                ServiceActivity().sendBroadcast(intent)

                // начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time.toLong())

                // сообщаем об окончании задачи
//                intent.putExtra(ServiceActivity().PARAM_STATUS, ServiceActivity().STATUS_FINISH)
//                intent.putExtra(ServiceActivity().PARAM_RESULT, time * 100)
//                ServiceActivity().sendBroadcast(intent)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            stopping()
        }

        fun stopping() {
            Log.d(ServiceActivity().LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + ServiceActivity().stopService(intent))
        }

        init {
            Log.d(ServiceActivity().LOG_TAG, "MyRun#$startId create")
        }
    }

    fun someTask() {

        Thread {
            for (i in 1..5) {
                Log.d(LOG_TAG, "i = $i")
                try {
                    TimeUnit.SECONDS.sleep(1)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            stopSelf()
        }.start()
    }
}
