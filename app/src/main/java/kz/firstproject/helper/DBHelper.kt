package kz.firstproject.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class DBHelper(context: Context?, name: String?, version: Int, openParams: SQLiteDatabase.OpenParams)
    : SQLiteOpenHelper(context, name, version, openParams) {

    val TABLE_CONTACTS = "contacts"

    val KEY_ID = "_id"
    val KEY_NAME = "name"
    val KEY_MAIL = "mail"



    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_MAIL + " text" + ")");
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);
    }
}