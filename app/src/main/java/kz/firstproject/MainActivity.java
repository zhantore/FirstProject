package kz.firstproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";
    String TAG = "MainActivity";
    EditText name;
    Button showBtn;
    TextView result;
//    ImageView avatar;
//    ProgressBar progressBar;
    CalendarView calendarView;
    DownloadImage async = null;
    String resultDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.personName);
        showBtn = findViewById(R.id.showButton);
        result = findViewById(R.id.textView);
        calendarView = findViewById(R.id.calendarView);

//        avatar = findViewById(R.id.imageView);
//        progressBar = findViewById(R.id.progressBar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                resultDate = String.valueOf(dayOfMonth) + "/"+ String.valueOf(month+1)+
                        "/" +String.valueOf(year) ;
            }
        });



        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                intent.putExtra("firstName", name.getText().toString());
//                startActivity(intent);
//                result.setText(name.getText().toString()+' '+resultDate);
//                async = new DownloadImage();
//                progressBar.setVisibility(View.VISIBLE);
//                avatar.setVisibility(View.INVISIBLE);
//                async.execute("https://photos-kl.kcdn.kz/kolesa-read/4c6e67f537c0dcb194f696b722616c85c41efa33-1193x671.jpg");

//                createNotificationChannel();
            }
        });
    }

    private void createNotificationChannel() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Мой будильник")
                .setContentText("Пора просываться!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.action_settings);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                notificationManager.notify(1, builder.build());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()");
    }

    class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... uris) {
            Bitmap bitmap = null;
            try {
                URL urlString = new URL(uris[0]);
                bitmap = BitmapFactory.decodeStream(urlString.openConnection().getInputStream());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
//            avatar.setImageBitmap(bitmap);
//            progressBar.setVisibility(View.INVISIBLE);
//            avatar.setVisibility(View.VISIBLE);
        }
    }
}
