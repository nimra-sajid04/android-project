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
 * OrdersActivity
 * --------------
 * Displays a list of all orders using RecyclerView.
 */
public class OrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
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
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.ordersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);

        // Fetch orders from database
        List<Order> orderList = dbHelper.getAllOrders();

        // Set adapter
        OrderAdapter adapter = new OrderAdapter(this, orderList);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        if (item.getItemId() == R.id.menu_logout) {
            sessionManager.logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
