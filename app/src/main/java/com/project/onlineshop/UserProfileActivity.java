package com.project.onlineshop;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UserProfileActivity
 * -------------------
 * Displays basic user profile information.
 * Data is fetched from SQLite database.
 */
public class UserProfileActivity extends AppCompatActivity {

    TextView emailText;
    SessionManager sessionManager;
    DBHelper dbHelper;

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
        setContentView(R.layout.activity_user_profile);

        emailText = findViewById(R.id.profileEmail);

        dbHelper = new DBHelper(this);

        // Load user data (simple approach: first user)
        loadUserProfile();
    }

    /**
     * Fetch and display user profile info
     */
    private void loadUserProfile() {

        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT email FROM users LIMIT 1", null);

        if (cursor.moveToFirst()) {
            String email = cursor.getString(0);
            emailText.setText("Email: " + email);
        } else {
            emailText.setText("Email: Not available");
        }

        cursor.close();
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
