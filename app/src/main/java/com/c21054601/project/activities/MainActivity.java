package com.c21054601.project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.c21054601.project.DAOs.GameUnitDAO;
import com.c21054601.project.fragments.DeckDecoder;
import com.c21054601.project.fragments.MapList;
import com.c21054601.project.R;
import com.c21054601.project.fragments.SearchUnits;
import com.c21054601.project.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener{

    public ActivityMainBinding mainBinding;
    private AdView mAdView;
    private InterstitialAd interstitialAd;
    public static MainActivity activityMainContext;
    private static MediaPlayer mediaPlayer;
    public ArrayList<GameUnitDAO> unitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mediaPlayer = MediaPlayer.create(this, R.raw.audioclip);
        playAudio();


        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        // Set up the progress spinner
        mainBinding.progressBar.setVisibility(View.VISIBLE);
        mainBinding.adView.setVisibility(View.INVISIBLE);
        mainBinding.fragmentContainer.setVisibility(View.INVISIBLE);



        //
        activityMainContext = this;


        mainBinding.bottomNavigationView.setOnItemSelectedListener(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                System.out.println("Ad init complete");
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void playAudio(){
        mediaPlayer.start();
    }

    private void changeInternalFragment(Fragment fragment, int fragmentContainer){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(fragmentContainer, fragment)
                .commit();
    }

    public void hideProgressBar(){
/*        Transition transition = new Fade();
        transition.setDuration(1000);
        transition.addTarget(drawerBinding.mainActivity.progressBar);
        TransitionManager.beginDelayedTransition(drawerBinding.mainActivity.fragmentContainer, transition);*/
        mainBinding.progressBar.setVisibility(View.INVISIBLE);
        mainBinding.adView.setVisibility(View.VISIBLE);
        mainBinding.fragmentContainer.setVisibility(View.VISIBLE);
    }

    public void showProgressBar(){
        mainBinding.progressBar.setVisibility(View.VISIBLE);
        mainBinding.adView.setVisibility(View.INVISIBLE);
        mainBinding.fragmentContainer.setVisibility(View.INVISIBLE);
    }

    public void showInterstitial() {
        // Show the ad if it's ready
        if (interstitialAd != null) {
            interstitialAd.show(this);
        }
    }

    // Handle switching to nightmode (prevent crash)
    @Override
    protected void onNightModeChanged(int mode) {
        super.onNightModeChanged(mode);
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/3419835294",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        MainActivity.this.interstitialAd = interstitialAd;
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        MainActivity.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        MainActivity.this.interstitialAd = null;
                                    }
                                });
                    }
                });
    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_fragment1: // UnitViewer
                changeInternalFragment(new SearchUnits(), R.id.fragmentContainer);
                break;
            case R.id.nav_fragment3: // DeckDecoder
                changeInternalFragment(new DeckDecoder(), R.id.fragmentContainer);
                break;
            case R.id.nav_fragment4: // MapPage
                changeInternalFragment(new MapList(), R.id.fragmentContainer);
                break;
        }
        return true;
    }
}