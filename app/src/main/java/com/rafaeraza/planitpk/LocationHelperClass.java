package com.rafaeraza.planitpk;

import java.util.ArrayList;

public class LocationHelperClass {
    String name, category, Desc, district;
    double rating;
    LocationImagesHelperClass images;
    HotelHelperClass hotels;

    /*
    Constructors
     */
    public LocationHelperClass() {
    }

    public LocationHelperClass(String name, String category, String desc, String district, double rating, LocationImagesHelperClass images, HotelHelperClass hotels) {
        this.name = name;
        this.category = category;
        Desc = desc;
        this.district = district;
        this.rating = rating;
        this.images = images;
        this.hotels = hotels;
    }

    /*
    Getters and Setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocationImagesHelperClass getImages() {
        return images;
    }

    public void setImages(LocationImagesHelperClass images) {
        this.images = images;
    }

    public HotelHelperClass getHotels() {
        return hotels;
    }

    public void setHotels(HotelHelperClass hotels) {
        this.hotels = hotels;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
