package com.rafaeraza.planitpk;

public class CategoryHelperClass {
    String name;
    String image;

    public CategoryHelperClass() {
    }

    public CategoryHelperClass(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
