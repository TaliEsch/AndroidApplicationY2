package com.c21054601.project.DAOs;

import java.util.ArrayList;

public class WeaponDAO {
    String weaponName;
    ArrayList<String> weaponTraits;
    Long penetration;
    Double heDamage;
    Long suppress;
    Long groundRange;
    Long helicopterRange;
    Long planeRange;
    Long accuracy;
    Long movingAccuracy;
    Long rateOfFire;
    Double aimingTime;
    Double reloadTime;
    Long salvoLength;
    Long supplyCost;

    public WeaponDAO(String weaponName, ArrayList<String> weaponTraits, Long penetration,
                     Double heDamage, Long suppress, Long groundRange, Long helicopterRange,
                     Long planeRange, Long accuracy, Long movingAccuracy, Long rateOfFire,
                     Double aimingTime, Double reloadTime, Long salvoLength, Long supplyCost) {
        this.weaponName = weaponName;
        this.weaponTraits = weaponTraits;
        this.penetration = penetration;
        this.heDamage = heDamage;
        this.suppress = suppress;
        this.groundRange = groundRange;
        this.helicopterRange = helicopterRange;
        this.planeRange = planeRange;
        this.accuracy = accuracy;
        this.movingAccuracy = movingAccuracy;
        this.rateOfFire = rateOfFire;
        this.aimingTime = aimingTime;
        this.reloadTime = reloadTime;
        this.salvoLength = salvoLength;
        this.supplyCost = supplyCost;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public ArrayList<String> getWeaponTraits() {
        return weaponTraits;
    }

    public Long getPenetration() {
        return penetration;
    }

    public Double getHeDamage() {
        return heDamage;
    }

    public Long getSuppress() {
        return suppress;
    }

    public Long getGroundRange() {
        return groundRange;
    }

    public Long getHelicopterRange() {
        return helicopterRange;
    }

    public Long getPlaneRange() {
        return planeRange;
    }
    public Long getAccuracy() {
        return accuracy;
    }
    public Long getMovingAccuracy() {
        return movingAccuracy;
    }
    public Long getRateOfFire() {
        return rateOfFire;
    }
    public Double getAimingTime() {
        return aimingTime;
    }
    public Double getReloadTime() {
        return reloadTime;
    }
    public Long getSalvoLength() {
        return salvoLength;
    }
    public Long getSupplyCost() {
        return supplyCost;
    }
}
