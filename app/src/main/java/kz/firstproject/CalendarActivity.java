package kz.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    TextView textResult;
    String result = "";
    SharedPreferences sharedPreferences;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        sharedPreferences = getSharedPreferences("Travelling", MODE_PRIVATE);
        textResult = findViewById(R.id.textResult);
        result = sharedPreferences.getString("holiday", "");
        sh = PreferenceManager.getDefaultSharedPreferences(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new PrefFragment())
                .commit();
        textResult.setText(result);
    }

    @Override
    protected void onResume() {
        boolean notif = sh.getBoolean("notif", false);
        String address = sh.getString("address", "");
        String text = "Notifications are "
                + ((notif) ? "enabled, address = " + address : "disabled");
        textResult.setText(text);
        super.onResume();
    }
}