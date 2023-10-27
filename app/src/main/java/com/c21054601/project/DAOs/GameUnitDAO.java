package com.c21054601.project.DAOs;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class GameUnitDAO {
    String infoPanelType;
    double advancedDeployment;
    Long airOptics;
    String category;
    Long commandPoints;
    String descriptorName;
    Long ecm;
    String factoryDescriptor;
    double frontArmour;
    double sideArmour;
    double rearArmour;
    double topArmour;
    boolean amphibious;
    double fuel;
    Long fuelMove;
    Boolean smoke;
    Integer id;
    Boolean isSellable;
    Long maxDamage;
    String name;
    double optics;
    Long roadSpeed;
    Long speed;
    Long supply;
    Long agility;
    Long travelTime;
    double stealth;
    StorageReference imageReference;
    HashMap hashMap;
    ArrayList<HashMap> weapons;
    ArrayList<WeaponDAO> weaponsList;

    public GameUnitDAO(String infoPanelType, String descriptorName, String name, Integer id, StorageReference imageReference, HashMap hashMap){
        this.infoPanelType = infoPanelType;
        this.descriptorName = descriptorName;
        this.name = name;
        this.id = id;
        this.imageReference = imageReference;
        this.hashMap = hashMap;
    }


    public void SetUnit(String advancedDeployment, String airOptics, String category,
                        String commandPoints, String descriptorName, String ecm,
                        String factoryDescriptor, String frontArmour, String sideArmour,
                        String rearArmour, String topArmour, String fuel, String fuelMove,
                        Boolean hasDefensiveSmoke, String id, Boolean isSellable, String maxDamage,
                        String name, String optics, String speed, String stealth,
                        StorageReference imageReference) {
    }

    public void setOptics(double optics){
        this.optics = optics;
    }

    public void setAdvancedDeployment(double advancedDeployment){
        this.advancedDeployment = advancedDeployment;
    }

    public void setWeaponary(ArrayList<HashMap> weapons){
        this.weapons = weapons;
    }
    public void setUnitBase(ArrayList<HashMap> weapons, Long commandPoints, double frontArmour, double sideArmour, double rearArmour,
                            double topArmour, Long maxDamage, double stealth, Long speed){
        this.weapons = weapons;
        this.commandPoints = commandPoints;
        this.frontArmour = frontArmour;
        this.sideArmour = sideArmour;
        this.rearArmour = rearArmour;
        this.topArmour = topArmour;
        this.maxDamage = maxDamage;
        this.stealth = stealth;
        this.speed = speed;
    }

    public void setUnitBaseTEMPORARY(Long fuel, Long roadSpeed, Boolean smoke){
        this.fuel = fuel;
        this.roadSpeed = roadSpeed;
        this.smoke = smoke;
    }

    public void setVehicleBase(boolean amphibious, Long fuelMove, Long fuel,Long roadSpeed, Boolean smoke){
        this.amphibious = amphibious;
        this.fuelMove = fuelMove;
        this.fuel = fuel;
        this.roadSpeed = roadSpeed;
        this.smoke = smoke;
    }

    public void setSupplyVehicleBase(boolean amphibious, Long roadSpeed, Long supply){
        this.amphibious = amphibious;
        this.roadSpeed = roadSpeed;
        this.supply = supply;
    }

    public void setHelicopterBase(Long ecm, Long fuelMove, Long fuel){
        this.ecm = ecm;
        this.fuelMove = fuelMove;
        this.fuel = fuel;
    }

    public void setSupplyHelicopterBase(Long ecm, Long supply){
        this.ecm = ecm;
        this.supply = supply;
    }

    public void setPlaneBase(Long airOptics, Long ecm, Long agility, Long travelTime, Long fuel){
        this.airOptics = airOptics;
        this.ecm = ecm;
        this.agility = agility;
        this.travelTime = travelTime;
        this.fuel = fuel;
    }

    public void setUnitLite(String infoPanelType, double frontArmour, double sideArmour, double rearArmour,
                            double topArmour, Long maxDamage, double optics, double stealth, boolean amphibious,
                            Long roadSpeed, Long speed, double advancedDeployment, double fuel, Boolean smoke){
        this.infoPanelType = infoPanelType;
        this.frontArmour = frontArmour;
        this.sideArmour = sideArmour;
        this.rearArmour = rearArmour;
        this.topArmour = topArmour;
        this.maxDamage = maxDamage;
        this.optics = optics;
        this.stealth = stealth;
        this.amphibious = amphibious;
        this.roadSpeed = roadSpeed;
        this.speed = speed;
        this.advancedDeployment = advancedDeployment;
        this.fuel = fuel;
        this.smoke = smoke;
    }

    public Integer getUnitId(){
        return getUnitId();
    }

    public String getInfoPanelType() {
        return infoPanelType;
    }

    public String getDescriptorName() {
        return descriptorName;
    }

    public Long getCommandPoints() {
        return commandPoints;
    }

    public ArrayList<HashMap> getWeapons(){
        return weapons;
    }

    public ArrayList<WeaponDAO> getWeaponsList(){
        return weaponsList;
    }

    public String getGameUnitName() {
        return name;
    }

    public StorageReference getGameUnitImageReference() {
        return imageReference;
    }

    public double getFrontArmour() {
        return frontArmour;
    }

    public double getSideArmour() {
        return sideArmour;
    }

    public double getRearArmour() {
        return rearArmour;
    }

    public double getTopArmour() {
        return topArmour;
    }

    public Long getMaxDamage() {
        return maxDamage;
    }

    public double getOptics() {
        return optics;
    }

    public double getStealth() {
        return stealth;
    }

    public boolean getAmphibious() {
        return amphibious;
    }

    public Long getRoadSpeed() {
        return roadSpeed;
    }

    public Long getSpeed() {
        return speed;
    }

    public double getAdvancedDeployment() {
        return advancedDeployment;
    }

    public Double getFuel() {
        return fuel;
    }

    public Boolean getSmoke() {
        return smoke;
    }

    public Long getSupply() {
        return supply;
    }

    public Long getEcm() {
        return ecm;
    }

    public long getAirOptics() {
        return airOptics;
    }

    public Long getTravelTime() {
        return travelTime;
    }

    public Long getAgility() {
        return agility;
    }

    public HashMap getHashMap() {
        return hashMap;
    }
}
