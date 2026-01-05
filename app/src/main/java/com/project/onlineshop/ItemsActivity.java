package com.project.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

/**
 * ItemsActivity
 * -------------
 * This activity displays all available items using RecyclerView.
 * It acts as the main/home screen of the app.
 */
public class ItemsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply saved theme BEFORE super
        sessionManager = new SessionManager(this);
        if (sessionManager.getTheme().equals("dark")) {
            setTheme(R.style.Theme_OnlineShop_Dark);
        } else {
            setTheme(R.style.Theme_OnlineShop_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = findViewById(R.id.itemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);

        // Insert dummy items ONLY ONCE
        insertDummyItemsIfNeeded();

        // Get items from database
        List<Item> itemList = dbHelper.getAllItems();

        // Set adapter
        ItemAdapter adapter = new ItemAdapter(this, itemList);

        adapter.setOnItemLongClickListener((item, position) -> {

            // Delete from database
            dbHelper.deleteItem(item.getId());

            // Remove from list
            itemList.remove(position);

            // Notify adapter
            adapter.notifyItemRemoved(position);
        });

        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Insert sample items if database is empty
     */
    private void insertDummyItemsIfNeeded() {

        if (dbHelper.getAllItems().size() == 0) {

            dbHelper.insertItem(new Item(
                    "Laptop",
                    50000,
                    "High performance laptop",
                    R.drawable.laptop
            ));

            dbHelper.insertItem(new Item(
                    "Mobile Phone",
                    30000,
                    "Smartphone",
                    R.drawable.phone
            ));

            dbHelper.insertItem(new Item(
                    "Headphones",
                    5000,
                    "Noise cancelling headphones",
                    R.drawable.headphones
            ));
        }
    }

    /* ---------------- OPTIONS MENU ---------------- */

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
}
