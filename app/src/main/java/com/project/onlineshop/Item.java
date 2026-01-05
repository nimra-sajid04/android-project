package com.project.onlineshop;
/**
 * Item Model Class
 * ----------------
 * This class represents a product/item in the shopping application.
 * It is used to store item data retrieved from SQLite database
 * and display it on different screens.
 */
public class Item {

    // Item attributes
    private int id;
    private String name;
    private int price;
    private String description;
    private int imageResId; // drawable resource ID

    /**
     * Empty constructor
     */
    public Item() {
    }

    /**
     * Constructor without id
     * Used when creating a new item before saving to database
     */
    public Item(String name, int price, String description, int imageResId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    /**
     * Constructor with all fields
     * Used when reading item from database
     */
    public Item(int id, String name, int price, String description, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    /* -------- GETTERS AND SETTERS -------- */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
