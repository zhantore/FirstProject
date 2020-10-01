package kz.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Objects;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    String selectedDate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView2);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                selectedDate = String.valueOf(i2) + "/" + String.valueOf(i1+1) + "/" + String.valueOf(i);
                setResult(10, getIntent().putExtra("fromDate", selectedDate));
                finish();
            }
        });

    }
}