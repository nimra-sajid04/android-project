package com.project.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


/**
 * ItemDetailsActivity
 * -------------------
 * Shows detailed information of a selected item.
 * Data is received from ItemsActivity via Intent.
 */
public class ItemDetailsActivity extends AppCompatActivity {

    ImageView itemImage;
    TextView itemName, itemPrice, itemDescription, quantityText;
    Button increaseBtn, decreaseBtn, placeOrderBtn;

    int quantity = 1;
    int pricePerItem = 0;

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
        setContentView(R.layout.activity_item_details);

        // Link UI
        itemImage = findViewById(R.id.detailItemImage);
        itemName = findViewById(R.id.detailItemName);
        itemPrice = findViewById(R.id.detailItemPrice);
        itemDescription = findViewById(R.id.detailItemDescription);
        quantityText = findViewById(R.id.quantityText);
        increaseBtn = findViewById(R.id.increaseQty);
        decreaseBtn = findViewById(R.id.decreaseQty);
        placeOrderBtn = findViewById(R.id.placeOrderBtn);

        // Get data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("itemName");
        int price = intent.getIntExtra("itemPrice", 0);
        String desc = intent.getStringExtra("itemDesc");
        int image = intent.getIntExtra("itemImage", 0);

        pricePerItem = price;

        // Set data
        itemName.setText(name);
        itemPrice.setText("Price: " + price);
        itemDescription.setText(desc);
        itemImage.setImageResource(image);
        quantityText.setText(String.valueOf(quantity));

        // Increase quantity
        increaseBtn.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
            updatePrice();
        });

        // Decrease quantity
        decreaseBtn.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
                updatePrice();
            }
        });

        // Place order
        placeOrderBtn.setOnClickListener(v -> {
            Intent orderIntent = new Intent(this, PlaceOrderActivity.class);
            orderIntent.putExtra("itemName", name);
            orderIntent.putExtra("quantity", quantity);
            orderIntent.putExtra("totalPrice", quantity * pricePerItem);
            startActivity(orderIntent);
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Update total price text
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_light_theme) {
            sessionManager.setTheme("light");
            recreate();
            return true;
        }

        if (item.getItemId() == R.id.menu_dark_theme) {
            sessionManager.setTheme("dark");
            recreate();
            return true;
        }

        if (item.getItemId() == R.id.menu_orders) {
            startActivity(new Intent(this, OrdersActivity.class));
            finish();
            return true;
        }

        if (item.getItemId() == R.id.menu_logout) {
            sessionManager.logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updatePrice() {
        int total = quantity * pricePerItem;
        itemPrice.setText("Price: " + total);
    }
}
