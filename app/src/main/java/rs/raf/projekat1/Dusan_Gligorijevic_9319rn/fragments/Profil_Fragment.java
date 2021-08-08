package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.ChangeInfoActivity;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.LoginActivity;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.R;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.SplashActivity;

import static android.content.Context.MODE_PRIVATE;

public class Profil_Fragment extends Fragment {
    private TextView ime, prezime, banka;
    private Button odjava, izmeni;
    public Profil_Fragment(){
        super(R.layout.profil_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListeners();
    }
    public void initView(){
        ime = getView().findViewById(R.id.ime_value);
        prezime = getView().findViewById(R.id.prezime_value);
        banka = getView().findViewById(R.id.banka_value);
        odjava = getView().findViewById(R.id.profil_odjava);
        izmeni = getView().findViewById(R.id.profil_izmeni);
        SharedPreferences editor = getActivity().getSharedPreferences(getActivity().getPackageName(), MODE_PRIVATE);
        String name = editor.getString(LoginActivity.ime,"empty");
        ime.setText(name);
        String ln = editor.getString(LoginActivity.prezime,"empty");
        prezime.setText(ln);
        String bank = editor.getString(LoginActivity.banka,"empty");
        banka.setText(bank);
    }
    public void initListeners(){
        odjava.setOnClickListener(v->{
            SharedPreferences preferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), SplashActivity.class);
            startActivity(intent);
        });
        izmeni.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
            startActivity(intent);
        });
    }

}
