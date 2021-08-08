package rs.raf.projekat1.Dusan_Gligorijevic_9319rn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeInfoActivity extends AppCompatActivity {
    private EditText ime, prezime, banka;
    private Button sacuvaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        init();
        initListeners();
    }
    public void init(){
        ime = findViewById(R.id.izmeni_ime_value);
        prezime = findViewById(R.id.izmeni_prezime_value);
        banka = findViewById(R.id.izmeni_banka_value);
        sacuvaj = findViewById(R.id.sacuvaj);
    }
    public void initListeners(){
        sacuvaj.setOnClickListener(v->{
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            sharedPreferences.edit().putString(LoginActivity.ime, ime.getText().toString()).apply();
            sharedPreferences.edit().putString(LoginActivity.prezime, prezime.getText().toString()).apply();
            sharedPreferences.edit().putString(LoginActivity.banka, banka.getText().toString()).apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}