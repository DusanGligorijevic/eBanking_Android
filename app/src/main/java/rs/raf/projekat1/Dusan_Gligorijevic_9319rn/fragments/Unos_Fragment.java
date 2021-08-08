package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.MainActivity;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.R;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.enums.Tip;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.RcmRashod;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.RecyclerViewModel;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.SharedViewModel;

import static android.content.ContentValues.TAG;

public class Unos_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
     private RecyclerViewModel recyclerViewModel;
     private EditText naslov, kolicina, opis;
     private Button dodajUListu;
     public static String prihod = "prihod";
    private Spinner spinner;
    private CheckBox checkBox;
    private SharedViewModel sharedViewModel;
    private RcmRashod rcm;
    private  MediaRecorder  mediaRecorder;
    private final int PERMISSION_ALL = 1;
    private final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static File file;


    public Unos_Fragment(){
        super(R.layout.unos_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        rcm = new ViewModelProvider(requireActivity()).get(RcmRashod.class);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        // Pitamo da li je aplikacija vec prihvatila ove dozvole
        if(hasPermissions(getActivity(), PERMISSIONS)) {
            init();
        }else {
            // Ukoliko nije, trazimo ih
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        }

        initView();
        initListeners();
    }
    private boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String @NotNull [] permissionsList, int @NotNull [] grantResults) {
        // Ovde dobijamo odgovor na requestPermissions
        if (requestCode == PERMISSION_ALL) {
            if (grantResults.length > 0) {
                StringBuilder permissionsDenied = new StringBuilder();
                for(int i=0;i<grantResults.length;i++){
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        permissionsDenied.append("\n").append(permissionsList[i]);
                    }
                }
                if (permissionsDenied.toString().length() == 0) {
                    // Ukoliko nema odbijenih dozvola, nastavljamo dalje
                    init();
                }else {
                    Toast.makeText(getActivity(), "Missing permissions! " + permissionsDenied.toString(), Toast.LENGTH_LONG).show();
                    // Ukoliko ima odbijenih dozvola ispisujemo poruku i zatvaramo activity
                    getActivity().finish();
                }
            }
        }
    }
    private void init() {

        File folder = new File(getActivity().getFilesDir(), "sounds");
        if(!folder.exists()) folder.mkdir();
        file = new File(folder, "record.3gp");
        initListenersrecord();


    }
    public void initView(){

        naslov = getView().findViewById(R.id.naslov);
        kolicina = getView().findViewById(R.id.kolicina);
        opis = getView().findViewById(R.id.opis);
        dodajUListu = getView().findViewById(R.id.unos_button);
        spinner = getView().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.list, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }
    public void initListeners(){
        dodajUListu.setOnClickListener(v->{
            if( naslov.getText().toString().isEmpty() || kolicina.getText().toString().isEmpty() || opis.getText().toString().isEmpty()){
                Toast.makeText(this.getActivity(), "Morate popuniti sva polja.", Toast.LENGTH_SHORT).show();
                return;
            }
            Tip tip = null;
            String s =  spinner.getSelectedItem().toString();
            if(s.contentEquals("Prihod")) tip= Tip.PRIHOD;
            else tip = Tip.RASHOD;
            if(s.contentEquals("Rashod"))
                rcm.addFinansija( tip, naslov.getText().toString(),
                        kolicina.getText().toString(), opis.getText().toString());
            else recyclerViewModel.addFinansija( tip, naslov.getText().toString(),
                    kolicina.getText().toString(), opis.getText().toString());
            //s = s.concat(",");
            //s = s.concat(kolicina.getText().toString());
            sharedViewModel.storeUserInput(s);
            int number = Integer.parseInt(kolicina.getText().toString());
            if(s.contentEquals("Prihod")){

                Stanje_Fragment.sum += number;
               // prihod_value.setText(String.valueOf(sum));
                Stanje_Fragment.res += Stanje_Fragment.sum;
            }else{
                Stanje_Fragment.sub += number;
                // prihod_value.setText(String.valueOf(sum));
                Stanje_Fragment.res -= Stanje_Fragment.sub;
            }
            naslov.setText(null);
            kolicina.setText(null);
            opis.setText(null);
        });
    }


    private void initMediaRecorder(File file) {
        mediaRecorder = new MediaRecorder();
        // Postavljanje parametara za mediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mediaRecorder.setOutputFile(file);
        }


    }
    public void initListenersrecord(){
        checkBox = getView().findViewById(R.id.checkBox);
        ImageView btnMic = getView().findViewById(R.id.btnMic);
        ImageView btnRecording = getView().findViewById(R.id.btnRecording);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isChecked()) {
                spinner.setVisibility(View.GONE);
                opis.setVisibility(View.GONE);
                ((Chronometer)getView().findViewById(R.id.chronometer)).setVisibility(View.VISIBLE);
                btnMic.setVisibility(View.VISIBLE);
            }
            else
            {
                ((Chronometer)getView().findViewById(R.id.chronometer)).setVisibility(View.GONE);
                btnMic.setVisibility(View.GONE);
                opis.setVisibility(View.VISIBLE);
            }
        });

        btnMic.setOnClickListener(v -> {
            try {
                btnMic.setVisibility(View.GONE);
                btnRecording.setVisibility(View.VISIBLE);
                initMediaRecorder(file);
                // Pokrecemo snimanje
                mediaRecorder.prepare();
                mediaRecorder.start();
                ((Chronometer)getView().findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
                ((Chronometer)getView().findViewById(R.id.chronometer)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnRecording.setOnClickListener(v -> {
            btnRecording.setVisibility(View.GONE);
            btnMic.setVisibility(View.VISIBLE);
            // Zaustavljamo snimanje i oslobadjamo resurse
            // Metodom stop() se snimljeni resurs cuva u fajlu koji smo prosledili pri inicijalizaciji mediaRecorder-a
           try {
               mediaRecorder.stop();
               mediaRecorder.release();
               mediaRecorder = null;
               ((Chronometer)getView().findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
               ((Chronometer)getView().findViewById(R.id.chronometer)).stop();
           }catch(Exception e){
               e.printStackTrace();
           }


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
