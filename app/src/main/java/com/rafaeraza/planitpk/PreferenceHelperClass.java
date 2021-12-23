package com.rafaeraza.planitpk;

import java.util.ArrayList;

public class PreferenceHelperClass {
    ArrayList<String> categories, districts, activities;
    String favorites;

    /*
    Constructors
     */
    public PreferenceHelperClass() {
    }

    public PreferenceHelperClass(ArrayList<String> categories, ArrayList<String> districts, ArrayList<String> activities, String favorites) {
        this.categories = categories;
        this.districts = districts;
        this.activities = activities;
        this.favorites = favorites;
    }

    /*
    Getters and Setters
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<String> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<String> districts) {
        this.districts = districts;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }
}
