package com.project.onlineshop;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


/**
 * BaseActivity
 * ------------
 * This activity contains common menu logic.
 * All activities (except Login & Signup) should extend this.
 */
public class MainActivity extends AppCompatActivity {

    protected SessionManager sessionManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        sessionManager = new SessionManager(this);

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
