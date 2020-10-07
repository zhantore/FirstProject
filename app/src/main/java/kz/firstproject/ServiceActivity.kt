package kz.firstproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ServiceActivity : AppCompatActivity() {

    val TASK1_CODE = 1
    val TASK2_CODE = 2
    val TASK3_CODE = 3

    val STATUS_START = 100
    val STATUS_FINISH = 200


    protected final val PARAM_TIME = "time"
    protected final val PARAM_TASK = "task"
    val PARAM_RESULT = "result"
    val PARAM_STATUS = "status"

    val BROADCAST_ACTION = "ru.startandroid.develop.p0961servicebackbroadcast"

    val LOG_TAG = "ServiceActivity()"
    var tvTask1: TextView? = null
    var tvTask2: TextView? = null
    var tvTask3: TextView? = null
    var br: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        tvTask1 = findViewById<View>(R.id.tvTask1) as TextView
        tvTask1!!.text = "Task1"
        tvTask2 = findViewById<View>(R.id.tvTask2) as TextView
        tvTask2!!.text = "Task2"
        tvTask3 = findViewById<View>(R.id.tvTask3) as TextView
        tvTask3!!.text = "Task3"

        // создаем BroadcastReceiver

        // создаем BroadcastReceiver
        br = object : BroadcastReceiver() {
            // действия при получении сообщений
            override fun onReceive(context: Context?, intent: Intent) {
                val task = intent.getIntExtra(PARAM_TASK, 0)
                val status = intent.getIntExtra(PARAM_STATUS, 0)
                Log.d(LOG_TAG, "onReceive: task = $task, status = $status")

                // Ловим сообщения о старте задач
                if (status == STATUS_START) {
                    when (task) {
                        TASK1_CODE -> tvTask1!!.text = "Task1 start"
                        TASK2_CODE -> tvTask2!!.text = "Task2 start"
                        TASK3_CODE -> tvTask3!!.text = "Task3 start"
                    }
                }

                // Ловим сообщения об окончании задач
                if (status == STATUS_FINISH) {
                    val result = intent.getIntExtra(PARAM_RESULT, 0)
                    when (task) {
                        TASK1_CODE -> tvTask1!!.text = "Task1 finish, result = $result"
                        TASK2_CODE -> tvTask2!!.text = "Task2 finish, result = $result"
                        TASK3_CODE -> tvTask3!!.text = "Task3 finish, result = $result"
                    }
                }
            }
        }
        // создаем фильтр для BroadcastReceiver
        // создаем фильтр для BroadcastReceiver
        val intFilt = IntentFilter(BROADCAST_ACTION)
        // регистрируем (включаем) BroadcastReceiver
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(br, intFilt)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

    fun onClickStart(v: View?) {
        var intent: Intent
        // Создаем Intent для вызова сервиса,
        // кладем туда параметр времени и код задачи
        intent = Intent(this, MyService::class.java).putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_TASK, TASK1_CODE)
        // стартуем сервис
        startService(intent)
        intent = Intent(this, MyService::class.java).putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_TASK, TASK2_CODE)
        startService(intent)
        intent = Intent(this, MyService::class.java).putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_TASK, TASK3_CODE)
        startService(intent)
    }
}