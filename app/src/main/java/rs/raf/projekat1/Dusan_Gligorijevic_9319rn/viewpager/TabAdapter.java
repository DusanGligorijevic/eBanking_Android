package rs.raf.projekat1.Dusan_Gligorijevic_9319rn.viewpager;

import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.MainActivity;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Liste_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Prihod_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Profil_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Rashod_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Stanje_Fragment;
import rs.raf.projekat1.Dusan_Gligorijevic_9319rn.fragments.Unos_Fragment;

public class TabAdapter extends FragmentPagerAdapter {
    private final int ITEM_COUNT = 2;

    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;

    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_1:fragment =new Prihod_Fragment();break;
            default: fragment = new Rashod_Fragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "PRIHODI";
            default: return "RASHODI";
        }
    }
}

