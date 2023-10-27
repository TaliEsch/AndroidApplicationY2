package com.c21054601.project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.c21054601.project.R;
import com.c21054601.project.activities.MainActivity;
import com.c21054601.project.databinding.FragmentDeckDecoderBinding;
import com.google.common.io.BaseEncoding;

import java.util.ArrayList;

public class DeckDecoder extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDeckDecoderBinding deckDecoderBinding = FragmentDeckDecoderBinding.inflate(inflater, container, false);
        MainActivity activityMainContext = MainActivity.activityMainContext;
        deckDecoderBinding.deckDecoderButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtedDeckB64 = deckDecoderBinding.textInputField.getEditableText().toString();
                try{
                    decodeDeck(inputtedDeckB64);
                } catch (Exception e){
                    Toast toast = Toast.makeText(getActivity(), "ERROR READING DECK", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        deckDecoderBinding.deckDecoderButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String testDeck ="FBF6aIS6UkRKK8RKJMRIq0VkeIAGLMVEfgAGKsVKL4AKKYAFtYAFtYAFYgAEwYAEwYAEfUVIq0VuKQ+Vj0VIq0VkeAAGMgAGMYAIfYAIfYAKJoAKJoAKJgAKJgAKJwAKJwAKJYAKUQAGMQAAgA==";
                try{
                    decodeDeck(testDeck);
                } catch (Exception e){
                    Toast toast = Toast.makeText(getActivity(), "ERROR READING DECK", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        activityMainContext.hideProgressBar();
        return deckDecoderBinding.getRoot();
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

    private void decodeDeck(String deck){
        ArrayList<Integer> units = new ArrayList<>();
        byte[] decodedDeck = BaseEncoding.base64().decode(deck);
        String outputDeck = String.format("%8s", Integer.toBinaryString(decodedDeck[0] & 0xFF)).replace(' ', '0');
        for (int i = 1; i < decodedDeck.length; i++) {
            outputDeck = outputDeck + String.format("%8s", Integer.toBinaryString(decodedDeck[i] & 0xFF)).replace(' ', '0');
        }
        outputDeck = outputDeck.substring(13); // Remove Eugen headers
        String DivisionId = outputDeck.substring(0, 14);
        outputDeck = outputDeck.substring(14); // Remove division id
        String numOfCards = outputDeck.substring(0, 7);
        int numOfCardsInt = Integer.parseInt(numOfCards, 2);
        outputDeck = outputDeck.substring(7); // Remove number of cards
        String unitXpLength = outputDeck.substring(0, 5);
        int unitXpLengthInt = Integer.parseInt(unitXpLength, 2);
        outputDeck = outputDeck.substring(5); // Remove unit xp length
        String unitIdLength = outputDeck.substring(0, 5);
        int unitIdLengthInt = Integer.parseInt(unitIdLength, 2);
        outputDeck = outputDeck.substring(5); // Remove unit id length
        int unitLength = unitXpLengthInt + unitIdLengthInt*2;



        for (int i = 0; i < numOfCardsInt; i++){
            String unit = outputDeck.substring(0,unitLength);
            outputDeck = outputDeck.substring(unitLength);
            String vet = unit.substring(0, 2);
            int unitLengthPlus2 = unitIdLengthInt+2;
            String unitId = unit.substring(2, unitLengthPlus2);
            Integer unitIdInt = Integer.parseInt(unitId, 2);
            units.add(unitIdInt);
            String transportId = unit.substring(unitLengthPlus2);
        }

        changeInternalFragment(new DeckViewer(units));
    }

}