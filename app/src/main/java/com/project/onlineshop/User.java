package com.project.onlineshop;

/**
 * User Model Class
 * ----------------
 * This class represents a single user in the application.
 * It is used to store and retrieve user data from SQLite database.
 *
 * This class contains ONLY data.
 * No UI or database code should be written here.
 */
public class User {

    // User attributes
    private int id;
    private String email;
    private String password;

    /**
     * Empty constructor
     * Required sometimes for flexibility
     */
    public User() {
    }

    /**
     * Constructor without id
     * Used when creating a new user before saving to database
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor with all fields
     * Used when reading user from database
     */
    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /* -------- GETTERS AND SETTERS -------- */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
