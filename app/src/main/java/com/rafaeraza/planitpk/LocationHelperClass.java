package com.rafaeraza.planitpk;

import java.util.ArrayList;

public class LocationHelperClass {
    String name, category, Desc, rating;
    LocationImagesHelperClass images;
    HotelHelperClass hotels;

    public LocationHelperClass() {
    }

    public LocationHelperClass(String name, String category, String desc, String rating, LocationImagesHelperClass images, HotelHelperClass hotels) {
        this.name = name;
        this.category = category;
        Desc = desc;
        this.rating = rating;
        this.images = images;
        this.hotels = hotels;
    }

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
}
