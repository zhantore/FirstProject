package kz.firstproject

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class SavingDataActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"

    val FILENAME = "file"

    val DIR_SD = "MyFiles"
    val FILENAME_SD = "fileSD"

    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saving_data)
    }

    fun onclick(v: View) {
        when (v.getId()) {
            R.id.btnWrite -> writeFile()
            R.id.btnRead -> readFile()
            R.id.btnWriteSD -> writeFileSD()
            R.id.btnReadSD -> readFileSD()
        }
    }


    fun writeFile() {
        try {
            // отрываем поток для записи
            val bw = BufferedWriter(OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)))
            // пишем данные
            bw.write("Содержимое файла")
            // закрываем поток
            bw.close()
            Log.d(LOG_TAG, "Файл записан")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFile() {
        try {
            // открываем поток для чтения
            val br = BufferedReader(InputStreamReader(
                    openFileInput(FILENAME)))
            var str: String? = ""
            // читаем содержимое
            while (br.readLine().also { str = it } != null) {
                Log.d(LOG_TAG, str)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun writeFileSD() {
        // проверяем на наличие SD карты в телефоне
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState())
            return
        }
        // получаем путь к SD
        var sdPath: File = Environment.getExternalStorageDirectory()
        // добавляем свой каталог к пути
        sdPath = File(sdPath.getAbsolutePath().toString() + "/" + DIR_SD)
        // создаем каталог
        sdPath.mkdirs()
        // формируем объект File, который содержит путь к файлу
        val sdFile = File(sdPath, FILENAME_SD)
        try {
            // открываем поток для записи
            val bw = BufferedWriter(FileWriter(sdFile))
            // пишем данные
            bw.write("Содержимое файла на SD")
            // закрываем поток
            bw.close()
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFileSD() {
        // проверяем на наличие SD карты в телефоне
        if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState())
            return
        }
        // получаем путь к SD карте
        var sdPath: File = Environment.getExternalStorageDirectory()
        // добавляем свой каталог к пути
        sdPath = File(sdPath.getAbsolutePath().toString() + "/" + DIR_SD)
        // формируем объект File, который содержит путь к файлу
        val sdFile = File(sdPath, FILENAME_SD)
        try {
            // открываем поток для чтения
            val br = BufferedReader(FileReader(sdFile))
            var str: String? = ""
            // читаем содержимое
            while (br.readLine().also { str = it } != null) {
                Log.d(LOG_TAG, str)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}