package com.project.onlineshop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * OrderDetailsActivity
 * --------------------
 * Displays detailed information of a selected order.
 * Data is received via Intent from OrdersActivity/OrderAdapter.
 */
public class OrderDetailsActivity extends AppCompatActivity {

    TextView itemNameText, quantityText, totalPriceText, orderDateText;
    TextView nameText, phoneText, addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply theme BEFORE super
        SessionManager sessionManager = new SessionManager(this);
        if (sessionManager.getTheme().equals("dark")) {
            setTheme(R.style.Theme_OnlineShop_Dark);
        } else {
            setTheme(R.style.Theme_OnlineShop_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Link UI
        itemNameText = findViewById(R.id.detailOrderItemName);
        quantityText = findViewById(R.id.detailOrderQuantity);
        totalPriceText = findViewById(R.id.detailOrderTotalPrice);
        orderDateText = findViewById(R.id.detailOrderDate);

        nameText = findViewById(R.id.detailOrderName);
        phoneText = findViewById(R.id.detailOrderPhone);
        addressText = findViewById(R.id.detailOrderAddress);


        // Get data from Intent
        String itemName = getIntent().getStringExtra("itemName");
        int quantity = getIntent().getIntExtra("quantity", 0);
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);
        String orderDate = getIntent().getStringExtra("orderDate");
        String customerName = getIntent().getStringExtra("customerName");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");

        // Set data to views
        itemNameText.setText("Item: " + itemName);
        quantityText.setText("Quantity: " + quantity);
        totalPriceText.setText("Total Price: " + totalPrice);
        orderDateText.setText("Order Date: " + orderDate);

        nameText.setText("Name: " + customerName);
        phoneText.setText("Phone: " + phone);
        addressText.setText("Address: " + address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
}
