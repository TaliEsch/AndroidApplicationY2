package com.c21054601.project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c21054601.project.R;
import com.c21054601.project.activities.MainActivity;
import com.c21054601.project.databinding.FragmentDeckDecoderBinding;
import com.c21054601.project.databinding.FragmentSplashScreenBinding;

public class SplashScreen extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSplashScreenBinding splashScreenBinding = FragmentSplashScreenBinding.inflate(inflater, container, false);
        MainActivity activityMainContext = MainActivity.activityMainContext;
        activityMainContext.hideProgressBar();

        splashScreenBinding.splashScreenDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInternalFragment(new DeckDecoder());
            }
        });

        splashScreenBinding.splashScreenUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInternalFragment(new SearchUnits());
            }
        });

        splashScreenBinding.splashScreenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInternalFragment(new MapList());
            }
        });

        return splashScreenBinding.getRoot();
    }

    private void changeInternalFragment(Fragment fragment){
        MainActivity activityMainContext = MainActivity.activityMainContext;
        activityMainContext.loadAd();
        activityMainContext.showInterstitial();
        int fragmentContainer = R.id.fragmentContainer;
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(fragmentContainer, fragment)
                .commit();
    }
}