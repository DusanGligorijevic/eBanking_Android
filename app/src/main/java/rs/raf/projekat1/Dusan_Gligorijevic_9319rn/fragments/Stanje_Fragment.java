package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.MainActivity;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.R;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.SharedViewModel;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.ShowViewModel;


public class Stanje_Fragment extends Fragment {
    private TextView textView, prihod, prihod_value,rashod,razlika, rashod_value, razlika_value;
    private SharedViewModel sharedViewModel;
    private ShowViewModel showViewModel;
    public  static int sum = 0;
    public  static int sub = 0;
    public  static int res = 0;
    public static int number;
    public Stanje_Fragment(){
        super(R.layout.stanje_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showViewModel = new ViewModelProvider(getActivity()).get(ShowViewModel.class);
        // sa requireActivity() u ViewModelProvider viewModel vezujemo za activity
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        initView();
    }

    public void initView(){
        textView = getView().findViewById(R.id.stanje_textView);
        prihod = getView().findViewById(R.id.prihod);
        prihod_value = getView().findViewById(R.id.prihod_value);
        rashod = getView().findViewById(R.id.rashod);
        rashod_value = getView().findViewById(R.id.rashod_value);
        razlika = getView().findViewById(R.id.razlika);
        razlika_value = getView().findViewById(R.id.razlika_value);

        showViewModel.getTitle().observe(getActivity(),s -> {

        });
        // Kada korisnik unese tekst i potvrdi unos, zelimo da prikazemo taj unos
        sharedViewModel.getUserInput().observe(getActivity(), (str) -> {
                    prihod_value.setText(String.valueOf(sum));
                    rashod_value.setText(String.valueOf(sub));
                    razlika_value.setText(String.valueOf(res));
            if(res<0) razlika_value.setTextColor(Color.parseColor("#ff0000"));
            else   razlika_value.setTextColor(Color.parseColor("#00ff00"));

        });


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        String p = prihod_value.getText().toString();
        String r = rashod_value.getText().toString();
        String re = razlika_value.getText().toString();
        outState.putString("p", p);
        outState.putString("r", r);
        outState.putString("res", re);
        super.onSaveInstanceState(outState);
    }

}
