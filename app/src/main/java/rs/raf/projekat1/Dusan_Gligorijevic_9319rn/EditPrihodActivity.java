package rs.raf.projekat1.Dusan_Gligorijevic_9319rn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditPrihodActivity extends AppCompatActivity {
    private EditText naslov, kolicina, opis;
    private Button odustani, izmeni;

    public EditPrihodActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prihod);
        init();
        initListeners();
    }
    public void init(){
        naslov = findViewById(R.id.edit_naslov_value);
        kolicina = findViewById(R.id.edit_kolicina_value);
        opis = findViewById(R.id.edit_opis_value);
        odustani = findViewById(R.id.edit_odustani);
        izmeni = findViewById(R.id.edit_izmeni);
    }
    public void initListeners(){
        odustani.setOnClickListener(v->{
            finish();
        });
        izmeni.setOnClickListener(v->{
            Intent intent = new Intent(this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("editnaslov",naslov.getText().toString());
            bundle.putString("editkolicina",kolicina.getText().toString());
            intent.putExtras(bundle);
            //ovde ide deo gde treba da izmenim viewholder


            finish();
        });
    }
}