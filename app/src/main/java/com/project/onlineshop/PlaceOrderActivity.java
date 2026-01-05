package com.project.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * PlaceOrderActivity
 * ------------------
 * This activity confirms the order and saves it into SQLite.
 */
public class PlaceOrderActivity extends AppCompatActivity {

    TextView itemNameText, quantityText, totalPriceText;

    EditText nameInput, phoneInput, addressInput;

    Button confirmOrderBtn;

    DBHelper dbHelper;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply theme BEFORE super
        sessionManager = new SessionManager(this);
        if (sessionManager.getTheme().equals("dark")) {
            setTheme(R.style.Theme_OnlineShop_Dark);
        } else {
            setTheme(R.style.Theme_OnlineShop_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        // Initialize DB
        dbHelper = new DBHelper(this);

        // Link UI
        itemNameText = findViewById(R.id.orderItemName);
        quantityText = findViewById(R.id.orderQuantity);
        totalPriceText = findViewById(R.id.orderTotalPrice);
        confirmOrderBtn = findViewById(R.id.confirmOrderBtn);

        nameInput = findViewById(R.id.inputName);
        phoneInput = findViewById(R.id.inputPhone);
        addressInput = findViewById(R.id.inputAddress);


        // Get data from Intent
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        int quantity = intent.getIntExtra("quantity", 1);
        int totalPrice = intent.getIntExtra("totalPrice", 0);

        // Display data
        itemNameText.setText("Item: " + itemName);
        quantityText.setText("Quantity: " + quantity);
        totalPriceText.setText("Total Price: " + totalPrice);

        // Confirm order
        confirmOrderBtn.setOnClickListener(v -> {

            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all delivery details", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get current date
            String currentDate = new SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
            ).format(new Date());


            // Create Order object
            Order order = new Order(
                    1,
                    itemName,
                    quantity,
                    totalPrice,
                    currentDate,
                    name,
                    phone,
                    address
            );

            // Save to database
            dbHelper.insertOrder(order);

            Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();

            // Go to Orders screen
            startActivity(new Intent(this, ItemsActivity.class));
            finish();
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
