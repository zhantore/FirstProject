package kz.firstproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

//    var a: String = "Almaty"
//    var b: Int = 10
//    var list = listOf(0, 1, 2, 3, 4, 5)
    var strings = mutableListOf("1", "2", "3", "4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        send.setOnClickListener {
            resultText.text = enterText.text.toString()
        }


/*
        print(a)
        val adapter = MyAdapter(this, R.layout.support_simple_spinner_dropdown_item, strings)
        strings.add("5")
        for (i in 0..10) {
            println(i)
        }
        loop@ for (i in 1..10) {
            for (j in 1..3) {
                if (i == j)
                    println(j.toString() + "=" + i.toString())
                    break@loop
            }
        }
        list.forEach {
            println(it)
        }
*/
    }

    class MyAdapter(context: Context, resource: Int, private val objects: MutableList<String>)
        : ArrayAdapter<String>(context, resource, objects) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            return super.getView(position, convertView, parent)
        }

        override fun getItem(position: Int): String? {
            return objects[position]
        }
    }
}