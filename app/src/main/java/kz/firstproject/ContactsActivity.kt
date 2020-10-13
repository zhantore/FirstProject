package kz.firstproject

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class ContactsActivity : AppCompatActivity(){
    val REQUEST_READ_CONTACTS = 79
    var list: ListView? = null
    var mobileArray: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            mobileArray = getAllContacts()
        } else {
            requestPermission()
        }
        list = findViewById(R.id.contactsRV)
        val adapter: ArrayAdapter<*> = ArrayAdapter(this,
                R.layout.contact_list_item, R.id.contact_text, mobileArray!!)
        list!!.setAdapter(adapter)
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_READ_CONTACTS)
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_READ_CONTACTS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_READ_CONTACTS -> {
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    mobileArray = getAllContacts()
                } else {
                    // permission denied,Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }

    fun getAllContacts(): ArrayList<String> {
        val nameList = ArrayList<String>()
        val cr = getContentResolver()
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null)
        if (cur != null) {
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    val id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE))
                    nameList.add(name)
                    if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                arrayOf(id), null)
                        while (pCur!!.moveToNext()) {
                            val phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER))
//                            nameList.add(phoneNo)
                        }
                        pCur.close()
                    }
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return nameList
    }
}