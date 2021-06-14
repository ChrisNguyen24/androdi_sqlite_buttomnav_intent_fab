package com.example.sqlitedemo.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentButtomAdapter extends FragmentStatePagerAdapter {

    private int numPager=4;

    public FragmentButtomAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentMail();
            case 2:
                return new FragmentSearch();
            case 3:
                return new FragmentNoty();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return numPager;
    }
}
