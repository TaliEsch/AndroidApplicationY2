package com.c21054601.project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.c21054601.project.DAOs.GameUnitDAO;
import com.c21054601.project.fragments.SearchUnits;
import com.c21054601.project.adapters.SearchUnitsAdapter;
import com.c21054601.project.activities.MainActivity;
import com.c21054601.project.databinding.FragmentDeckViewerBinding;
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

public class DeckViewer extends Fragment {

    ArrayList<Integer> units;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    public DeckViewer(ArrayList<Integer> units){
        this.units = units;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentDeckViewerBinding deckViewerBinding = FragmentDeckViewerBinding.inflate(inflater, container, false);
        MainActivity activityMainContext = MainActivity.activityMainContext;
        activityMainContext.showProgressBar();
        SearchUnits searchUnits = new SearchUnits();

        ArrayList<GameUnitDAO> unitsList = new ArrayList<>();
        ArrayList<GameUnitDAO> deckUnitsList = new ArrayList<>();
        ArrayList<Integer> unitsIdList = new ArrayList<>();


        SearchUnitsAdapter searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), deckUnitsList);
        deckViewerBinding.frameLayout.setAdapter(searchUnitsAdapter);
        deckViewerBinding.frameLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(deckViewerBinding.frameLayout.getContext(),
                DividerItemDecoration.VERTICAL);
        deckViewerBinding.frameLayout.addItemDecoration(dividerItemDecoration);

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
                                    unitsList.add(currentUnit);
                                    unitsIdList.add(Integer.parseInt(String.valueOf(hashMapUnit.get("id"))));
                                }
                            }
                            for (int i = 0; i < unitsList.size(); i++) {
                                if (units.contains(unitsIdList.get(i))){
                                    deckUnitsList.add(unitsList.get(i));
                                }
                            }
                            if (getActivity() != null) {
                                SearchUnitsAdapter searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), deckUnitsList);
                                deckViewerBinding.frameLayout.setAdapter(searchUnitsAdapter);
                                deckViewerBinding.frameLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(deckViewerBinding.frameLayout.getContext(),
                                        DividerItemDecoration.VERTICAL);
                                deckViewerBinding.frameLayout.addItemDecoration(dividerItemDecoration);
                            }
                            activityMainContext.hideProgressBar();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //TODO: Add no internet message
                        SearchUnitsAdapter searchUnitsAdapter = new SearchUnitsAdapter(getActivity(), null);
                        deckViewerBinding.frameLayout.setAdapter(searchUnitsAdapter);
                    }
                });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        return deckViewerBinding.getRoot();
    }

}
