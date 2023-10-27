package com.c21054601.project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.c21054601.project.R;
import com.c21054601.project.activities.MainActivity;
import com.c21054601.project.databinding.FragmentMapListBinding;
import com.google.android.material.navigation.NavigationView;


public class MapList extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    FragmentMapListBinding mapListBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMapListBinding mapListBinding = FragmentMapListBinding.inflate(inflater, container, false);
        MainActivity activityMainContext = MainActivity.activityMainContext;
        mapListBinding.navMap.setNavigationItemSelectedListener(this);
        activityMainContext.hideProgressBar();
        return mapListBinding.getRoot();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        changeInternalFragment(new MapViewer(id), R.id.fragmentContainer);
        return true;
    }

    public void changeInternalFragment(Fragment fragment, int fragmentContainer){
        FragmentManager supportFragmentManager = getParentFragmentManager();
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(fragmentContainer, fragment)
                .commit();
    }

}