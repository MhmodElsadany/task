package com.example.mahmoud.task1;

public class SouqItems {

    String name;
    String image;
    String id;

    public SouqItems(String name, String image ,String id) {
        this.name = name;
        this.image = image;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
    public String getId() {
        return id;
    }

}
