package com.rafaeraza.planitpk;

public class HotelHelperClass {
    String about, accommodationType, avgPrice, facility, name, rating;

    /*
    Constructors
     */
    public HotelHelperClass() {
    }

    public HotelHelperClass(String about, String accommodationType, String avgPrice, String facility, String name, String rating) {
        this.about = about;
        this.accommodationType = accommodationType;
        this.avgPrice = avgPrice;
        this.facility = facility;
        this.name = name;
        this.rating = rating;
    }

    /*
    Getters and Setters
     */
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
