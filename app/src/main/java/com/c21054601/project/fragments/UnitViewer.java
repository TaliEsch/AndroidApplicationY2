package com.c21054601.project.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.c21054601.project.DAOs.GameUnitDAO;
import com.c21054601.project.DAOs.WeaponDAO;
import com.c21054601.project.R;
import com.c21054601.project.adapters.UnitViewerAdapterRecycler;
import com.c21054601.project.databinding.FragmentUnitViewerBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitViewer extends Fragment {

    GameUnitDAO gameUnit;
    private ArrayList<WeaponDAO> weaponList = new ArrayList<>();

    public UnitViewer(GameUnitDAO gameUnit) {
        this.gameUnit = gameUnit;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentUnitViewerBinding unitViewerBinding = FragmentUnitViewerBinding.inflate(inflater, container, false);

        // Get the unit from Firebase
/*        if (gameUnit.get() == null) {*/

/*        }*/


        String infoPanelType = gameUnit.getInfoPanelType();
        switch (infoPanelType){
            case "default":
                unitViewerBinding = createUnitDefault(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                // A thing
                break;
            case "infantry":
                unitViewerBinding = createUnitDefault(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                // TODO: Add infantry specific stuff
                break;
            case "transport-vehicle":
                unitViewerBinding = createUnitTransportVehicle(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                // A thing
                break;
            case "supply-vehicle":
                unitViewerBinding = createUnitSupplyVehicle(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                break;
            case "helicopter":
                unitViewerBinding = createUnitHelicopter(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                break;
            case "transport-helicopter":
                unitViewerBinding = createUnitTransportHelicopter(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                break;
            case "supply-helicopter":
                unitViewerBinding = createUnitSupplyHelicopter(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                break;
            case "plane":
                unitViewerBinding = createUnitPlane(unitViewerBinding, gameUnit.getHashMap(), gameUnit);
                break;
        }

        ImageView imageView = unitViewerBinding.unitViewerIcon;
        Glide.with(this)
                .load(gameUnit.getGameUnitImageReference())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


        if (gameUnit.getWeapons() != null){
            ArrayList<HashMap> weapons = gameUnit.getWeapons();
            weaponList =  createWeapon(weapons, gameUnit);
        }else{
            unitViewerBinding.textView4.setVisibility(View.GONE);
            unitViewerBinding.textBuffer17.setVisibility(View.GONE);
        }

        if (getActivity()!=null) {
            UnitViewerAdapterRecycler unitViewerAdapter = new UnitViewerAdapterRecycler(getActivity(), weaponList);
            unitViewerBinding.listViewWeaponry.setAdapter(unitViewerAdapter);
            unitViewerBinding.listViewWeaponry.setLayoutManager(new LinearLayoutManager(getActivity()));
        }


        // Inflate the layout for this fragment
        return unitViewerBinding.getRoot();
    }

    private FragmentUnitViewerBinding createUnitDefault(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
/*        if(gameUnit.getMaxDamage() == null){*/
            createBase(gameUnit.getHashMap(), gameUnit);
            createBaseTEMPORARY(gameUnit.getHashMap(), gameUnit);
            addOptics(gameUnit.getHashMap(), gameUnit);
            addAdvancedDeployment(gameUnit.getHashMap(), gameUnit);
/*        }*/
        loadBase(unitViewerBinding, gameUnit);
        loadOptics(unitViewerBinding, gameUnit);
        loadAdvancedDeployment(unitViewerBinding, gameUnit);
        // HP, Optics, Stealth, Adv., Speed
        return unitViewerBinding;
    }
    private FragmentUnitViewerBinding createUnitTransportVehicle(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
        if(gameUnit.getMaxDamage() == null){
            createBase(gameUnit.getHashMap(), gameUnit);
            addOptics(gameUnit.getHashMap(), gameUnit);
            addAdvancedDeployment(gameUnit.getHashMap(), gameUnit);
            addVehicleBase(gameUnit.getHashMap(), gameUnit);
        }
        loadBase(unitViewerBinding, gameUnit);
        loadOptics(unitViewerBinding, gameUnit);
        loadAdvancedDeployment(unitViewerBinding, gameUnit);
        loadTransportVehicle(unitViewerBinding, gameUnit);
        // HP, Optics, Stealth, Amphib, Speed, RoadSpeed, Autonomy, Fuel, Adv. Deploy, Smoke
        return unitViewerBinding;
    }
    private FragmentUnitViewerBinding createUnitSupplyVehicle(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
        if(gameUnit.getMaxDamage() == null){
            createBase(gameUnit.getHashMap(), gameUnit);
            addOptics(gameUnit.getHashMap(), gameUnit);
            addAdvancedDeployment(gameUnit.getHashMap(), gameUnit);
            addSupplyVehicleBase(gameUnit.getHashMap(), gameUnit);
        }
        // HP, Optics, Stealth, Amphib, Speed, RoadSpeed, Adv. Deploy, Supply
        loadBase(unitViewerBinding, gameUnit);
        loadOptics(unitViewerBinding, gameUnit);
        loadAdvancedDeployment(unitViewerBinding, gameUnit);
        loadSupplyVehicle(unitViewerBinding, gameUnit);
        return unitViewerBinding;
    }
    private FragmentUnitViewerBinding createUnitHelicopter(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
        if(gameUnit.getMaxDamage() == null){
            createBase(gameUnit.getHashMap(), gameUnit);
            addOptics(gameUnit.getHashMap(), gameUnit);
            addAdvancedDeployment(gameUnit.getHashMap(), gameUnit);
            addHelicopterBase(gameUnit.getHashMap(), gameUnit);
        }
        // HP, Optics, Stealth, ECM, Speed, Autonomy, Fuel, Adv. Deploy
        loadBase(unitViewerBinding, gameUnit);
        loadOptics(unitViewerBinding, gameUnit);
        loadAdvancedDeployment(unitViewerBinding, gameUnit);
        loadHelicopter(unitViewerBinding, gameUnit);
        return unitViewerBinding;
    }
    private FragmentUnitViewerBinding createUnitTransportHelicopter(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
        // HP, Optics, Stealth, ECM, Speed, Autonomy, Fuel, Adv. Deploy
        if(gameUnit.getMaxDamage() == null){
            createBase(gameUnit.getHashMap(), gameUnit);
            addOptics(gameUnit.getHashMap(), gameUnit);
            addAdvancedDeployment(gameUnit.getHashMap(), gameUnit);
            addHelicopterBase(gameUnit.getHashMap(), gameUnit);
        }
        loadBase(unitViewerBinding, gameUnit);
        loadOptics(unitViewerBinding, gameUnit);
        loadAdvancedDeployment(unitViewerBinding, gameUnit);
        loadHelicopter(unitViewerBinding, gameUnit);
        return unitViewerBinding;
    }
    private FragmentUnitViewerBinding createUnitSupplyHelicopter(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
        // HP, Optics, Stealth, ECM, Speed, Adv. Deploy, Supply
        if(gameUnit.getMaxDamage() == null){
            createBase(gameUnit.getHashMap(), gameUnit);
            addOptics(gameUnit.getHashMap(), gameUnit);
            addAdvancedDeployment(gameUnit.getHashMap(), gameUnit);
            addSupplyHelicopterBase(gameUnit.getHashMap(), gameUnit);
        }
        loadBase(unitViewerBinding, gameUnit);
        loadOptics(unitViewerBinding, gameUnit);
        loadAdvancedDeployment(unitViewerBinding, gameUnit);
        loadSupplyHelicopter(unitViewerBinding, gameUnit);
        return unitViewerBinding;
    }
    private FragmentUnitViewerBinding createUnitPlane(FragmentUnitViewerBinding unitViewerBinding, HashMap unit, GameUnitDAO unitObject){
        // HP, Air Optics, ECM, Agility, Traveltime, Speed, Autonomy, Speed
        if(gameUnit.getMaxDamage() == null){
            createBase(gameUnit.getHashMap(), gameUnit);
            addPlaneBase(gameUnit.getHashMap(), gameUnit);
        }
        loadBase(unitViewerBinding, gameUnit);
        loadPlane(unitViewerBinding, gameUnit);
        return unitViewerBinding;
    }

    private void addOptics(HashMap unit, GameUnitDAO unitObject){
        // Optics
        double optics;
        Object opticsObj = unit.get("optics");
        if(opticsObj instanceof Long) {
            Long opticsLong = (Long) opticsObj;
            optics = (double) opticsLong;
        }else {
            optics = (double) opticsObj;
        }
        unitObject.setOptics(optics);
    }

    private void addAdvancedDeployment(HashMap unit, GameUnitDAO unitObject){
        double advancedDeployment;
        Object advancedDeployObj = unit.get("advancedDeployment");
        if(advancedDeployObj instanceof Long) {
            Long advancedDeployLong = (Long) advancedDeployObj;
            advancedDeployment = (double) advancedDeployLong;
        }else {
            advancedDeployment = (double) advancedDeployObj;
        }
        unitObject.setAdvancedDeployment(advancedDeployment);
    }

    private void createBase(HashMap unit, GameUnitDAO unitObject) {

        ArrayList<HashMap> weapons = (ArrayList<HashMap>) unit.get("weapons");

        Long commandPoints = (Long) unit.get("commandPoints");

        // HP, Optics, Stealth, Adv., Speed
        Object frontArmourObj = unit.get("frontArmor");
        double frontArmour;
        if (frontArmourObj instanceof Long) {
            Long frontArmourLong = (Long) frontArmourObj;
            frontArmour = (double) frontArmourLong;
        }else{
            frontArmour = (double) frontArmourObj;
        }

        double sideArmour;
        Object sideArmourObj = unit.get("sideArmor");
        if (sideArmourObj instanceof Long) {
            Long sideArmourLong = (Long) sideArmourObj;
            sideArmour = (double) sideArmourLong;
        }else{
            sideArmour = (double) sideArmourObj;
        }

        double rearArmour;
        Object rearArmourObj = unit.get("rearArmor");
        if(rearArmourObj instanceof Long) {
            Long rearArmourLong = (Long) rearArmourObj;
            rearArmour = (double) rearArmourLong;
        }else{
            rearArmour = (double) rearArmourObj;
        }

        double topArmour;
        Object topArmourObj = unit.get("topArmor");
        if(topArmourObj instanceof Long) {
            Long topArmourLong = (Long) topArmourObj;
            topArmour = (double) topArmourLong;
        }else {
            topArmour = (double) topArmourObj;
        }

        Object maxDamage = unit.get("maxDamage");
        Long maxDamageLong = (Long) maxDamage;

        double stealth;
        Object stealthObj = unit.get("stealth");
        if(stealthObj instanceof Long) {
            Long stealthLong = (Long) stealthObj;
            stealth = (double) stealthLong;
        }else {
            stealth = (double) stealthObj;
        }

        Long speed;
        speed = (Long) unit.get("speed");


        unitObject.setUnitBase(weapons, commandPoints, frontArmour, sideArmour, rearArmour, topArmour, maxDamageLong, stealth, speed);
    }

    private ArrayList<WeaponDAO> createWeapon(ArrayList<HashMap> weapons, GameUnitDAO gameunit){
        for(HashMap weapon : weapons){
            String weaponName = (String) weapon.get("weaponName");

            ArrayList<String> weaponTraits = (ArrayList<String>) weapon.get("traits");

            Long penetration = (Long) weapon.get("penetration");

            double heInput;
            Object heObj = weapon.get("he");
            if(heObj instanceof Long) {
                Long stealthLong = (Long) heObj;
                heInput = (double) stealthLong;
            }else {
                heInput = (double) heObj;
            }
            Double heDamage = heInput / 10;
            Long suppress = (Long) weapon.get("suppress");

            Long groundRange = (Long) weapon.get("groundRange");
            Long helicopterRange = (Long) weapon.get("helicopterRange");
            Long planeRange = (Long) weapon.get("planeRange");

            Long accuracy = (Long) weapon.get("staticAccuracy");
            Long movingAccuracy = (Long) weapon.get("movingAccuracy");

            Long rateOfFire = (Long) weapon.get("rateOfFire");
            double aimingTime;
            Object aimingTimeObj = weapon.get("aimingTime");
            if(aimingTimeObj instanceof Long) {
                Long stealthLong = (Long) aimingTimeObj;
                aimingTime = (double) stealthLong;
            }else {
                aimingTime = (double) aimingTimeObj;
            }
            double reloadTime;
            Object reloadTimeObj = weapon.get("reloadTime");
            if(reloadTimeObj instanceof Long) {
                Long stealthLong = (Long) reloadTimeObj;
                reloadTime = (double) stealthLong;
            }else {
                reloadTime = (double) reloadTimeObj;
            }
            Long salvoLength = (Long) weapon.get("salvoLength");

            Long supplyCost = (Long) weapon.get("supplyCost");

            WeaponDAO weaponObject = new WeaponDAO(weaponName, weaponTraits, penetration, heDamage,
                    suppress, groundRange, helicopterRange, planeRange, accuracy, movingAccuracy,
                    rateOfFire, aimingTime, reloadTime, salvoLength, supplyCost);
            weaponList.add(weaponObject);

        }
        return weaponList;
    }

    private void createBaseTEMPORARY(HashMap unit, GameUnitDAO unitObject){
        Object fuelObj = unit.get("fuel");
        Long fuel = (Long) fuelObj;
        if(fuel == null){
            fuel = 0L;
        }

        Long roadSpeed;
        roadSpeed = (Long) unit.get("roadSpeed");

        Boolean smoke;
        smoke = (Boolean) unit.get("hasDefensiveSmoke");
        unitObject.setUnitBaseTEMPORARY(fuel, roadSpeed, smoke);
    }

    private void addVehicleBase(HashMap unit, GameUnitDAO unitObject){

        boolean amphibious = false;
        ArrayList<String> specialtiesList;
        specialtiesList = (ArrayList<String>) unit.get("specialties");
        if(specialtiesList != null) {
            if (specialtiesList.contains("_amphibile")) {
                amphibious = true;
            }
        }

        Object fuelMoveObj = unit.get("fuelMove");
        Long fuelMove = (Long) fuelMoveObj;

        Object fuelObj = unit.get("fuel");
        Long fuel = (Long) fuelObj;

        Long roadSpeed;
        roadSpeed = (Long) unit.get("roadSpeed");

        Boolean smoke;
        smoke = (Boolean) unit.get("hasDefensiveSmoke");

        unitObject.setVehicleBase(amphibious, fuelMove, fuel,roadSpeed, smoke);
    }

    private void addSupplyVehicleBase(HashMap unit, GameUnitDAO unitObject) {
        boolean amphibious = false;
        ArrayList<String> specialtiesList;
        specialtiesList = (ArrayList<String>) unit.get("specialties");
        if(specialtiesList != null) {
            if (specialtiesList.contains("_amphibile")) {
                amphibious = true;
            }
        }

        Long roadSpeed;
        roadSpeed = (Long) unit.get("roadSpeed");

        Long supply;
        supply = (Long) unit.get("supply");

        unitObject.setSupplyVehicleBase(amphibious, roadSpeed, supply);
    }

    private void addHelicopterBase(HashMap unit, GameUnitDAO unitObject){
        double ecm;
        Object ecmObj = unit.get("ecm");
        if(ecmObj instanceof Long) {
            Long stealthLong = (Long) ecmObj;
            ecm = (double) stealthLong;
        }else {
            ecm = (double) ecmObj;
        }

        ecm = ecm * -1;
        ecm = ecm * 100;
        Long ecmLong = Math.round(ecm);


        Object fuelMoveObj = unit.get("fuelMove");
        Long fuelMove = (Long) fuelMoveObj;

        Object fuelObj = unit.get("fuel");
        Long fuel = (Long) fuelObj;

        unitObject.setHelicopterBase(ecmLong, fuelMove, fuel);
    }

    private void addSupplyHelicopterBase(HashMap unit, GameUnitDAO unitObject){
        double ecm;
        Object ecmObj = unit.get("ecm");
        if(ecmObj instanceof Long) {
            Long stealthLong = (Long) ecmObj;
            ecm = (double) stealthLong;
        }else {
            ecm = (double) ecmObj;
        }

        ecm = ecm * -1;
        ecm = ecm * 100;
        Long ecmLong = Math.round(ecm);


        Long supply;
        supply = (Long) unit.get("supply");

        unitObject.setSupplyHelicopterBase(ecmLong, supply);
    }

    // HP, Air Optics, ECM, Agility, Traveltime, Speed, Autonomy, Speed
    private void addPlaneBase(HashMap unit, GameUnitDAO unitObject){
        Long airOptics;
        airOptics = (Long) unit.get("airOptics");

        double ecm;
        Object ecmObj = unit.get("ecm");
        if(ecmObj instanceof Long) {
            Long stealthLong = (Long) ecmObj;
            ecm = (double) stealthLong;
        }else {
            ecm = (double) ecmObj;
        }

        ecm = ecm * -1;
        ecm = ecm * 100;
        Long ecmLong = Math.round(ecm);

        Long agility;
        agility = (Long) unit.get("agility");

        Long travelTime;
        travelTime = (Long) unit.get("travelTime");

        Object fuelObj = unit.get("fuel");
        Long fuel = (Long) fuelObj;

        unitObject.setPlaneBase(airOptics, ecmLong, agility, travelTime, fuel);
    }

    private FragmentUnitViewerBinding loadBase(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.unitViewerText1.setText(unitObject.getGameUnitName());
        unitViewerBinding.unitViewerCost.setText(String.valueOf(unitObject.getCommandPoints()));

        // Unit armour
        unitViewerBinding.textViewArmourFront.setText(String.valueOf(unitObject.getFrontArmour()));
        unitViewerBinding.textViewArmourSide.setText(String.valueOf(unitObject.getSideArmour()));
        unitViewerBinding.textViewArmourRear.setText(String.valueOf(unitObject.getRearArmour()));
        unitViewerBinding.textViewArmourTop.setText(String.valueOf(unitObject.getTopArmour()));

        // Second row binding
        unitViewerBinding.textViewMaxDamage.setText(String.valueOf(unitObject.getMaxDamage()));
        unitViewerBinding.textViewStealth.setText(String.valueOf(unitObject.getStealth()));

        // Third row binding
        unitViewerBinding.textViewSpeed.setText(String.valueOf(unitObject.getSpeed()));

        unitViewerBinding.textView4Title.setText(R.string.amphibious);
        unitViewerBinding.textViewAmphibious.setText(String.valueOf(unitObject.getAmphibious()));
        unitViewerBinding.textView5Title.setText(R.string.road_speed);
        unitViewerBinding.textViewRoadSpeed.setText(String.valueOf(unitObject.getRoadSpeed()));
        unitViewerBinding.textView8Title.setText(R.string.fuel);
        unitViewerBinding.textViewFuel.setText(String.valueOf(unitObject.getFuel()));
        unitViewerBinding.textView9Title.setText(R.string.smoke);
        unitViewerBinding.textViewSmoke.setText(String.valueOf(unitObject.getSmoke()));
        return unitViewerBinding;
    }

    private FragmentUnitViewerBinding loadOptics(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView3Title.setText(R.string.optics);
        unitViewerBinding.textViewOptics.setText(String.valueOf(unitObject.getOptics()));
        return unitViewerBinding;
    }

    private FragmentUnitViewerBinding loadAdvancedDeployment(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView6Title.setText(R.string.adv_deploy);
        unitViewerBinding.textViewAdvancedDeploy.setText(String.valueOf(unitObject.getAdvancedDeployment()));
        return unitViewerBinding;
    }

    private FragmentUnitViewerBinding loadTransportVehicle(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView4Title.setText(R.string.amphibious);
        unitViewerBinding.textViewAmphibious.setText(String.valueOf(unitObject.getAmphibious()));
        unitViewerBinding.textView5Title.setText(R.string.road_speed);
        unitViewerBinding.textViewRoadSpeed.setText(String.valueOf(unitObject.getRoadSpeed()));
        unitViewerBinding.textView8Title.setText(R.string.fuel);
        unitViewerBinding.textViewFuel.setText(String.valueOf(unitObject.getFuel()));
        unitViewerBinding.textView9Title.setText(R.string.smoke);
        unitViewerBinding.textViewSmoke.setText(String.valueOf(unitObject.getSmoke()));
        // TODO: Add fuel move duration
        return unitViewerBinding;
    }

    private FragmentUnitViewerBinding loadSupplyVehicle(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView4Title.setText(R.string.amphibious);
        unitViewerBinding.textViewAmphibious.setText(String.valueOf(unitObject.getAmphibious()));
        unitViewerBinding.textView5Title.setText(R.string.road_speed);
        unitViewerBinding.textViewRoadSpeed.setText(String.valueOf(unitObject.getRoadSpeed()));
        unitViewerBinding.textView8Title.setText(R.string.supply);
        unitViewerBinding.textViewFuel.setText(String.valueOf(unitObject.getSupply()));
        unitViewerBinding.textBuffer13.setVisibility(View.GONE);
        unitViewerBinding.textView9Title.setVisibility(View.GONE);
        unitViewerBinding.textViewSmoke.setVisibility(View.GONE);
        return unitViewerBinding;
    }

    public FragmentUnitViewerBinding loadHelicopter(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView4Title.setText(R.string.ecm);
        double ecm = unitObject.getEcm();
        String ecmText = ecm + "%";
        unitViewerBinding.textViewAmphibious.setText(ecmText);
        unitViewerBinding.textView5Title.setText(R.string.fuel);
        unitViewerBinding.textViewRoadSpeed.setText(String.valueOf(unitObject.getFuel()));
        unitViewerBinding.textView8Title.setVisibility(View.GONE);
        unitViewerBinding.textViewFuel.setVisibility(View.GONE);
        unitViewerBinding.textBuffer13.setVisibility(View.GONE);
        unitViewerBinding.textView9Title.setVisibility(View.GONE);
        unitViewerBinding.textViewSmoke.setVisibility(View.GONE);
        return unitViewerBinding;
    }

    public FragmentUnitViewerBinding loadSupplyHelicopter(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView4Title.setText(R.string.ecm);
        String ecm = String.valueOf(unitObject.getEcm());
        String ecmText = ecm + "%";
        unitViewerBinding.textViewAmphibious.setText(ecmText);
        unitViewerBinding.textView5Title.setText(R.string.supply);
        unitViewerBinding.textViewRoadSpeed.setText(String.valueOf(unitObject.getSupply()));
        unitViewerBinding.textView8Title.setVisibility(View.GONE);
        unitViewerBinding.textViewFuel.setVisibility(View.GONE);
        unitViewerBinding.textBuffer13.setVisibility(View.GONE);
        unitViewerBinding.textView9Title.setVisibility(View.GONE);
        unitViewerBinding.textViewSmoke.setVisibility(View.GONE);
        return unitViewerBinding;
    }

    public FragmentUnitViewerBinding loadPlane(FragmentUnitViewerBinding unitViewerBinding, GameUnitDAO unitObject){
        unitViewerBinding.textView3Title.setText(R.string.air_optics);
        unitViewerBinding.textViewOptics.setText(String.valueOf(unitObject.getAirOptics()));
        unitViewerBinding.textView4Title.setText(R.string.ecm);
        String ecm = String.valueOf(unitObject.getEcm());
        String ecmText = ecm + "%";
        unitViewerBinding.textViewAmphibious.setText(ecmText);
        unitViewerBinding.textView5Title.setText(R.string.travel_time);
        unitViewerBinding.textViewRoadSpeed.setText(String.valueOf(unitObject.getTravelTime()));
        unitViewerBinding.textView6Title.setText(R.string.agility);
        unitViewerBinding.textViewAdvancedDeploy.setText(String.valueOf(unitObject.getAgility()));
        unitViewerBinding.textView8Title.setText(R.string.fuel);
        unitViewerBinding.textViewFuel.setText(String.valueOf(unitObject.getFuel()));
        unitViewerBinding.textView9Title.setVisibility(View.GONE);
        unitViewerBinding.textViewSmoke.setVisibility(View.GONE);
        return unitViewerBinding;
    }

    private void createUnitObject(HashMap unit, GameUnitDAO unitObject) {

        Object infoPanelTypeObj = unit.get("infoPanelType");
        String infoPanelType = (String) infoPanelTypeObj;


        Object frontArmourObj = unit.get("frontArmor");
        double frontArmour;
        if (frontArmourObj instanceof Long) {
            Long frontArmourLong = (Long) frontArmourObj;
            frontArmour = (double) frontArmourLong;
        }else{
            frontArmour = (double) frontArmourObj;
        }

        double sideArmour;
        Object sideArmourObj = unit.get("sideArmor");
        if (sideArmourObj instanceof Long) {
            Long sideArmourLong = (Long) sideArmourObj;
            sideArmour = (double) sideArmourLong;
        }else{
            sideArmour = (double) sideArmourObj;
        }

        double rearArmour;
        Object rearArmourObj = unit.get("rearArmor");
        if(rearArmourObj instanceof Long) {
            Long rearArmourLong = (Long) rearArmourObj;
            rearArmour = (double) rearArmourLong;
        }else{
            rearArmour = (double) rearArmourObj;
        }

        double topArmour;
        Object topArmourObj = unit.get("topArmor");
        if(topArmourObj instanceof Long) {
            Long topArmourLong = (Long) topArmourObj;
            topArmour = (double) topArmourLong;
        }else {
            topArmour = (double) topArmourObj;
        }

        Object maxDamage = unit.get("maxDamage");
        Long maxDamageLong = (Long) maxDamage;

        double stealth;
        Object stealthObj = unit.get("stealth");
        if(stealthObj instanceof Long) {
            Long stealthLong = (Long) stealthObj;
            stealth = (double) stealthLong;
        }else {
            stealth = (double) stealthObj;
        }

        double optics;
        Object opticsObj = unit.get("optics");
        if(opticsObj instanceof Long) {
            Long opticsLong = (Long) opticsObj;
            optics = (double) opticsLong;
        }else {
            optics = (double) opticsObj;
        }

        boolean amphibious = false;
        ArrayList<String> specialtiesList = new ArrayList<>();
        specialtiesList = (ArrayList<String>) unit.get("specialties");
        if(specialtiesList != null) {
            if (specialtiesList.contains("_amphibile")) {
                amphibious = true;
            }
        }



        Long roadSpeed;
        roadSpeed = (Long) unit.get("roadSpeed");

        double advancedDeployment;
        Object advancedDeployObj = unit.get("advancedDeployment");
        if(advancedDeployObj instanceof Long) {
            Long advancedDeployLong = (Long) advancedDeployObj;
            advancedDeployment = (double) advancedDeployLong;
        }else {
            advancedDeployment = (double) advancedDeployObj;
        }

        double fuel = 0;
        Object fuelObj = unit.get("fuel");
        if (fuelObj != null) {
            if (fuelObj instanceof Long) {
                Long fuelLong = (Long) fuelObj;
                fuel = (double) fuelLong;
            } else {
                fuel = (double) fuelObj;
            }
        }

        Boolean smoke = false;
        smoke = (Boolean) unit.get("hasDefensiveSmoke");



/*        unitObject.setUnitLite(infoPanelType, frontArmour, sideArmour, rearArmour, topArmour, maxDamageLong, stealth, optics, amphibious, roadSpeed, advancedDeployment, fuel, smoke);*/

/*        Object advancedDeployment = unit.get("advancedDeployment");
        Object airOptics = unit.get("airOptics");
        String category = (String) unit.get("category");
        Object commandPoints = unit.get("commandPoints");
        String descriptorName = (String) unit.get("descriptorName");
        Object ecm = unit.get("ecm");
        String factoryDescriptor = (String) unit.get("factoryDescriptor");
        Object frontArmour = unit.get("frontArmour");
        Object sideArmour = unit.get("sideArmour");
        Object rearArmour = unit.get("rearArmour");
        Object topArmour = unit.get("topArmour");
        Object fuel = unit.get("fuel");
        Object fuelMove = unit.get("fuelMove");
        Boolean hasDefensiveSmoke = (Boolean) unit.get("hasDefensiveSmoke");
        Object id = unit.get("id");
        Boolean isSellable = (Boolean) unit.get("isSellable");
        Object maxDamage = unit.get("maxDamage");
        String name = (String) unit.get("name");
        Object optics = unit.get("optics");
        Object speed = unit.get("speed");
        Object stealth = unit.get("stealth");
        StorageReference imageReference = (StorageReference) unit.get("imageReference");*/

/*        unitObject.SetUnit(advancedDeployment, airOptics, category, commandPoints, descriptorName, ecm, factoryDescriptor,
                frontArmour, sideArmour, rearArmour, topArmour, fuel, fuelMove, hasDefensiveSmoke,
                id, isSellable, maxDamage, name, optics, speed, stealth, imageReference);*/
    }


}