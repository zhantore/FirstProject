package kz.firstproject

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase.OpenParams
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kz.firstproject.helper.DBHelper


class DBActivity : AppCompatActivity(), View.OnClickListener {

    var btnAdd: Button? = null
    var btnRead:Button? = null
    var btnClear:Button? = null
    var etName: EditText? = null
    var etEmail:EditText? = null
    lateinit var dbHelper: DBHelper
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "contactDb"

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_b)
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd!!.setOnClickListener(this)

        btnRead =  findViewById(R.id.btnRead)
        btnRead!!.setOnClickListener(this)

        btnClear =  findViewById(R.id.btnClear)
        btnClear!!.setOnClickListener(this)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)

        val options = OpenParams.Builder()
        dbHelper = DBHelper(this, DATABASE_NAME, DATABASE_VERSION, options.build())
    }

    override fun onClick(v: View?) {
        val name = etName!!.text.toString()
        val email = etEmail!!.text.toString()

        val database = dbHelper.writableDatabase

        val contentValues = ContentValues()
        var result: String? = null


        when (v!!.getId()) {
            R.id.btnAdd -> {
                contentValues.put(dbHelper.KEY_NAME, name)
                contentValues.put(dbHelper.KEY_MAIL, email)
                database.insert(dbHelper.TABLE_CONTACTS, null, contentValues)
                Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
            }
            R.id.btnRead -> {
                val cursor: Cursor = database.query(dbHelper.TABLE_CONTACTS, null, null, null, null, null, null)
                if (cursor.moveToFirst()) {
                    val idIndex: Int = cursor.getColumnIndex(dbHelper.KEY_ID)
                    val nameIndex: Int = cursor.getColumnIndex(dbHelper.KEY_NAME)
                    val emailIndex: Int = cursor.getColumnIndex(dbHelper.KEY_MAIL)
                    do {
                        result = "ID = " + cursor.getInt(idIndex).toString() +
                                ", name = " + cursor.getString(nameIndex).toString() +
                                ", email = " + cursor.getString(emailIndex)
                        Log.d("mLog", result)
                        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                    } while (cursor.moveToNext())
                } else Log.d("mLog", "0 rows")
                cursor.close()
            }
            R.id.btnClear -> database.delete(dbHelper.TABLE_CONTACTS, null, null)
        }
        dbHelper.close()
    }
}