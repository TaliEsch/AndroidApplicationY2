package com.c21054601.project.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.c21054601.project.activities.MainActivity;
import com.c21054601.project.DAOs.GameUnitDAO;
import com.c21054601.project.R;
import com.c21054601.project.adapters.SearchUnitsAdapter;
import com.c21054601.project.databinding.FragmentSearchUnitsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;



public class SearchUnits extends Fragment {

    private FragmentSearchUnitsBinding fragmentSearchUnitsBinding;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    public static ArrayList<GameUnitDAO> unitList;
    public static SearchUnits searchUnitsContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSearchUnitsBinding fragmentSearchUnitsBinding = FragmentSearchUnitsBinding.inflate(inflater, container, false);
        searchUnitsContext = this;
        MainActivity activityMainContext = MainActivity.activityMainContext;
        unitList = activityMainContext.unitList;

        activityMainContext.showProgressBar();

        Runnable runnable = new Runnable(){
            public void run() {
                Query query = reference.child("units").orderByChild("name");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                HashMap hashMapUnit = (HashMap) issue.getValue();
                                assert hashMapUnit != null;
                                String name = (String) hashMapUnit.get("name");
                                String descriptor = (String) hashMapUnit.get("descriptorName");
                                if (!Objects.equals(name, "")) {
                                    StorageReference storageReference = storageRef.child(descriptor + ".png");
                                    GameUnitDAO currentUnit = new GameUnitDAO(hashMapUnit.get("infoPanelType").toString(), hashMapUnit.get("descriptorName")
                                            .toString(), hashMapUnit.get("name").toString(), Integer.parseInt(String.valueOf(hashMapUnit.get("id"))),storageReference, hashMapUnit);
                                    unitList.add(currentUnit);
                                    activityMainContext.hideProgressBar();
                                }
                            }
                            if (getActivity() != null) {
                                SearchUnitsAdapter searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), unitList);
                                fragmentSearchUnitsBinding.frameLayout.setAdapter(searchUnitsAdapter);
                                fragmentSearchUnitsBinding.frameLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(fragmentSearchUnitsBinding.frameLayout.getContext(),
                                        DividerItemDecoration.VERTICAL);
                                fragmentSearchUnitsBinding.frameLayout.addItemDecoration(dividerItemDecoration);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //TODO: Add no internet message
                        SearchUnitsAdapter searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), null);
                        fragmentSearchUnitsBinding.frameLayout.setAdapter(searchUnitsAdapter);
                    }
                });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        // Code for search bar

        fragmentSearchUnitsBinding.unitSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchUnitsAdapter searchUnitsAdapter;
                if (query.isEmpty()){
                    searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), unitList);
                }else {
                    ArrayList<GameUnitDAO> results = unitList.stream()
                            .filter(unit -> unit.getGameUnitName().contains(query.toUpperCase()))
                            .collect(Collectors.toCollection(ArrayList::new));

                    searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), results);
                }
                fragmentSearchUnitsBinding.frameLayout.setAdapter(searchUnitsAdapter);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentSearchUnitsBinding.unitSearch.getWindowToken(), 0);
                fragmentSearchUnitsBinding.frameLayout.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return fragmentSearchUnitsBinding.getRoot();
    }

    public void changeInternalFragment(Fragment fragment){
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
