package rs.raf.projekat1.Dusan_Gligorijevic_9319rn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private String logged, empty="empty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences editor = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        logged = editor.getString(LoginActivity.sifra, empty);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (logged.contentEquals(empty)) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);

    }
}