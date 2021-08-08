package rs.raf.projekat1.Dusan_Gligorijevic_9319rn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.SharedViewModel;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private EditText fName, lName, bank, pass;
    private Button button;
    private static final String password = "admin";
    public static final String ime = "ime", prezime = "prezime", banka = "banka", sifra = "sifra";
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initListeners();
    }
    public void init(){
        imageView = findViewById(R.id.loginImage);
        textView = findViewById(R.id.textView);
        fName = findViewById(R.id.loginFName);
        lName = findViewById(R.id.loginLName);
        bank = findViewById(R.id.loginBank);
        pass = findViewById(R.id.loginPassword);
        button = findViewById(R.id.loginPrijava);
    }
    public void initListeners(){
        button.setOnClickListener(v -> {
            if(fName.getText().toString().isEmpty() || lName.getText().toString().isEmpty() || bank.getText().toString().isEmpty()){
                Toast.makeText(this, "Polja ne smeju biti prazna.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(pass.getText().toString().isEmpty()){
                Toast.makeText(this, "Morate uneti sifru.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(pass.getText().length()<5) {
                Toast.makeText(this, "Sifra mora imati minimum 5 karaktera.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pass.getText().toString().equals(password)) {
                Intent intent = new Intent(this, MainActivity.class);
                SharedPreferences editor = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                //------------------------------------------------------------------
                editor.edit().putString(ime,fName.getText().toString()).apply();
                editor.edit().putString(prezime,lName.getText().toString()).apply();
                editor.edit().putString(banka,bank.getText().toString()).apply();
                editor.edit().putString(sifra,pass.getText().toString()).apply();
                //------------------------------------------------


                startActivity(intent);
            }else {
                Timber.e("Pogresna sifra!");
                Toast.makeText(this, "Pogresna sifra!", Toast.LENGTH_SHORT).show();
            }
            });
    }
}
