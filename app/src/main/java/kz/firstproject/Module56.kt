package kz.firstproject

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_module56.*

class Module56 : AppCompatActivity() {
    var autoNamesArray= arrayOf(
            "Audi S8", "Subaru Forester", "Mercedes GT", "BMW 1", "Toyota Corolla", "Lexus ES",
            "Audi R8", "Subaru Outback", "Mercedes GL", "BMW 3", "Toyota Camry", "Lexus LS",
            "Audi RS6", "Subaru XV", "Mercedes ML", "BMW 5", "Toyota Hilux", "Lexus IS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module56)

        autoCompleteTextView.setAdapter(ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, autoNamesArray))

        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            resultTextView2.text = autoCompleteTextView.text
        }

        multiAutoCompleteTextView2.setAdapter(ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, autoNamesArray))

        multiAutoCompleteTextView2.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multiAutoCompleteTextView2.setOnItemClickListener { adapterView, view, i, l ->
            resultTextView2.text = multiAutoCompleteTextView2.text
        }

        spinner2.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, autoNamesArray)
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                resultTextView2.text = autoNamesArray[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                resultTextView2.text = ""
            }
        }

        gridViewText.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, autoNamesArray)

    }
}