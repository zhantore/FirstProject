package kz.firstproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    String[] autoNamesArray = new String[] {"Audi S8", "Subaru Forester", "Mercedes GT", "BMW 1", "Toyota Corolla", "Lexus ES",
            "Audi R8", "Subaru Outback", "Mercedes GL", "BMW 3", "Toyota Camry", "Lexus LS",
            "Audi RS6", "Subaru XV", "Mercedes ML", "BMW 5", "Toyota Hilux", "Lexus IS"};
    TextView resultTextView;
    AutoCompleteTextView auto;
    MultiAutoCompleteTextView multiAuto;
    Spinner spinner;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Bundle b = getIntent().getExtras();
//        if (b != null) {
//            name = b.getString("firstName");
//        }

        auto = findViewById(R.id.autoTextView);
        multiAuto = findViewById(R.id.multiAutoCompleteTextView);
        resultTextView = findViewById(R.id.resultTextView);
        spinner = findViewById(R.id.spinner);
        gridView = findViewById(R.id.gridItems);

        resultTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivityForResult(intent, 10);

            }
        });


        auto.setAdapter(new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                autoNamesArray));

        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long position) {
                resultTextView.setText( auto.getText().toString() );
            }
        });

        multiAuto.setAdapter(new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, autoNamesArray));
        multiAuto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTextView.setText(auto.getText().toString());
            }
        });

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, autoNamesArray));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resultTextView.setText(autoNamesArray[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                resultTextView.setText("");
            }
        });

        GridViewAdapter myAdapter = new GridViewAdapter(this, autoNamesArray);
        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                resultTextView.setText(autoNamesArray[i]);
                view.setBackgroundColor(Color.GREEN);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            if (data != null) {
                resultTextView.setText(data.getStringExtra("fromDate"));
            }
        }
    }

    class GridViewAdapter extends ArrayAdapter<String> {

        String[] objects;
        TextView tv;
        Context context;

        // Constructor
        public GridViewAdapter(@NonNull Context context, @NonNull String[] objects) {
            super(context, R.layout.support_simple_spinner_dropdown_item, objects);
            this.objects = objects;
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                tv = new TextView(this.context);
            }
            tv.setText(this.objects[position]);

            return tv;
        }

        @Nullable
        @Override
        public String getItem(int position) {
            return this.objects[position];
        }
    }
}