package kz.firstproject

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity3 : AppCompatActivity() {

    val mCats = arrayOf("Audi", "Subaru", "Mercedes", "BMW",
            "Toyota", "Lexus")

    val mContacts = arrayOf(
            "Audi S8", "Subaru Forester", "Mercedes GT", "BMW 1", "Toyota Corolla", "Lexus ES",
            "Audi R8", "Subaru Outback", "Mercedes GL", "BMW 3", "Toyota Camry", "Lexus LS",
            "Audi RS6", "Subaru XV", "Mercedes ML", "BMW 5", "Toyota Hilux", "Lexus IS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        personName.setAdapter(ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mCats))
        personName.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            resultTextView.text = personName.text.toString()
        }

        modelName.setAdapter(ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mContacts))
        modelName.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        modelName.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            resultTextView.text = personName.text.toString()
        }
        val adapter = MyAdapter(this, mContacts)

        gridView.adapter = adapter

        showButton.setOnClickListener {
//            val intent = Intent(applicationContext, MainActivity2::class.java)
//            intent.putExtra("firstName", personName.getText().toString())
//            startActivity(intent)
        }
    }

    class MyAdapter(context: Context, objects: Array<out String>)
        : ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, objects) {

        var mContacts = objects
        lateinit var tv : TextView

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            if (convertView == null) {
                tv = TextView(context)
            }
            tv.text = mContacts[position]
            return tv
        }

        override fun getItem(position: Int): String? {
            return mContacts[position]
        }
    }
}