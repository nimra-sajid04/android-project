package com.project.onlineshop;

/**
 * Order Model Class
 * -----------------
 * This class represents a single order placed by a user.
 * It is used to transfer order data between SQLite database
 * and activities.
 *
 * This class contains ONLY data.
 */
public class Order {

    // Order attributes
    private int id;
    private int userId;
    private String itemName;
    private int quantity;
    private int totalPrice;
    private String orderDate;

    private String customerName;
    private String phone;
    private String address;


    /**
     * Empty constructor
     */
    public Order() {
    }

    /**
     * Constructor without id
     * Used when creating a new order before saving to database
     */
    public Order(int userId, String itemName, int quantity, int totalPrice, String orderDate) {
        this.userId = userId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }


    /**
     * Constructor with all fields
     * Used when reading order from database
     */

    public Order(int id, int userId,
                 String itemName,
                 int quantity,
                 int totalPrice,
                 String orderDate,
                 String customerName,
                 String phone,
                 String address) {

        this.id = id;
        this.userId = userId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
    }

    public Order(int userId,
                 String itemName,
                 int quantity,
                 int totalPrice,
                 String orderDate,
                 String customerName,
                 String phone,
                 String address) {

        this.userId = userId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
    }

    /* -------- GETTERS AND SETTERS -------- */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() { return customerName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}

