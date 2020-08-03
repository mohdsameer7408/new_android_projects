package com.example.recyclerviewwithcontextmenu;

public class Item {

    private int image_id;
    private String name;

    public Item(int image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public String getName() {
        return name;
    }
}
