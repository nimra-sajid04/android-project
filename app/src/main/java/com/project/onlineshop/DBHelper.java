package com.project.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

/**
 * DBHelper
 * --------
 * This class handles all SQLite database operations.
 * It creates tables and provides CRUD methods.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "shop_app.db";
    private static final int DATABASE_VERSION = 2;

    /* -------- TABLE NAMES -------- */
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ITEMS = "items";
    private static final String TABLE_ORDERS = "orders";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* -------- CREATE TABLES -------- */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Users table
        String CREATE_USERS =
                "CREATE TABLE " + TABLE_USERS + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "email TEXT," +
                        "password TEXT" +
                        ")";
        db.execSQL(CREATE_USERS);

        // Items table
        String CREATE_ITEMS =
                "CREATE TABLE " + TABLE_ITEMS + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "price INTEGER," +
                        "description TEXT," +
                        "imageResId INTEGER" +
                        ")";
        db.execSQL(CREATE_ITEMS);

        // Orders table
//        String CREATE_ORDERS =
//                "CREATE TABLE " + TABLE_ORDERS + "(" +
//                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "userId INTEGER," +
//                        "itemName TEXT," +
//                        "quantity INTEGER," +
//                        "totalPrice INTEGER," +
//                        "orderDate TEXT" +
//                        ")";
//        db.execSQL(CREATE_ORDERS);

        String CREATE_ORDERS =
                "CREATE TABLE " + TABLE_ORDERS + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "userId INTEGER," +
                        "itemName TEXT," +
                        "quantity INTEGER," +
                        "totalPrice INTEGER," +
                        "orderDate TEXT," +
                        "customerName TEXT," +
                        "phone TEXT," +
                        "address TEXT)";

        db.execSQL(CREATE_ORDERS);

    }

    /* -------- DATABASE UPGRADE -------- */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    /* =====================================================
       USER TABLE METHODS
       ===================================================== */

    // Insert user
    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Check login
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS +
                        " WHERE email=? AND password=?",
                new String[]{email, password}
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }

    /* =====================================================
       ITEM TABLE METHODS
       ===================================================== */

    // Insert item
    public void insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("price", item.getPrice());
        values.put("description", item.getDescription());
        values.put("imageResId", item.getImageResId());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void deleteItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("items", "id=?", new String[]{String.valueOf(itemId)});
        db.close();
    }

    // Get all items
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                );
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemList;
    }

    /* =====================================================
       ORDER TABLE METHODS
       ===================================================== */

    // Insert order

    public void insertOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", order.getUserId());
        values.put("itemName", order.getItemName());
        values.put("quantity", order.getQuantity());
        values.put("totalPrice", order.getTotalPrice());
        values.put("orderDate", order.getOrderDate());
        values.put("customerName", order.getCustomerName());
        values.put("phone", order.getPhone());
        values.put("address", order.getAddress());

        db.insert("orders", null, values);
        db.close();
    }


    // Get all orders
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ORDERS, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)
                );
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;
    }
}
