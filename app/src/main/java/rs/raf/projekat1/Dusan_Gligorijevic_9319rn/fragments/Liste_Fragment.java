package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.R;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewpager.TabAdapter;


public class Liste_Fragment extends Fragment {
    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";
    public Liste_Fragment(){
        super(R.layout.liste_fragment);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY);
            // Do something with value if needed
        }
        initView();
        initTabs();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SOME_VALUE_KEY, someStateValue);
        super.onSaveInstanceState(outState);
    }


    private void initView() {
        viewPager = getView().findViewById(R.id.viewPagerTab);
        tabLayout = getView().findViewById(R.id.tabLayout);
    }

    private void initTabs() {
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
