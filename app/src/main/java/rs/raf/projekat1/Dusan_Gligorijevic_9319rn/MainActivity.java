package rs.raf.projekat1.Dusan_Gligorijevic_9319rn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Prihod_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Profil_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Rashod_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewpager.PagerAdapter;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewpager.TabAdapter;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        initFragments();
        initViewPager();
        initNavigation();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }
    private void initFragments(){

    }
    private void initNavigation() {
        ((BottomNavigationView) findViewById(R.id.bottomNavigation)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                // setCurrentItem metoda viewPager samo obavesti koji je Item trenutno aktivan i onda metoda getItem u adapteru setuje odredjeni fragment za tu poziciju
                case R.id.navigation_1:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_1, false);
                    break;
                case R.id.navigation_2:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_2, false);
                    break;
                case R.id.navigation_3:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_3, false);
                    break;
                case R.id.navigation_4:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_4, false);
                    break;
            }
            return true;
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Timber.e("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.e("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.e("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.e("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.e("onDestroy");
    }
}