package com.rafaeraza.planitpk;

public class RatingHelperClass {
    private String locationName;
    private int userRating;

    public RatingHelperClass() {
    }

    public RatingHelperClass(String locationName, int userRating) {
        this.locationName = locationName;
        this.userRating = userRating;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }
}
