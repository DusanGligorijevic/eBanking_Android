package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.R;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.adapter.FaRashod;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.adapter.FinansijaAdapter;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.differ.Differ;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.enums.Tip;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.RcmRashod;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewmodels.RecyclerViewModel;

import static android.widget.Toast.LENGTH_SHORT;

public class Rashod_Fragment extends Fragment {
    private RcmRashod recyclerViewModel;
    private RecyclerView recyclerView;
    private static int id = 101;

    private FaRashod finansijaAdapter;

    public Rashod_Fragment(){
        super(R.layout.activity_recycler_rashod);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RcmRashod.class);
        init();
    }

    private void init() {
        initView();
       // initListeners();
        initObservers();
        initRecycler();
    }

    private void initView() {
        recyclerView =  getActivity().findViewById(R.id.listRvrashod);

    }

    private void initListeners() {

    }

    private void initObservers() {
        recyclerViewModel.getFinansije().observe(getViewLifecycleOwner(), finansije -> {
            finansijaAdapter.submitList(finansije);
        });
    }

    private void initRecycler() {
        finansijaAdapter = new FaRashod(new Differ(), finansija -> {
            Toast.makeText(getActivity(),id+"", LENGTH_SHORT).show();
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(finansijaAdapter);
    }
}
