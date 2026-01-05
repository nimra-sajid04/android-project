package com.project.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * LoginActivity
 * -------------
 * This activity allows the user to login.
 * It checks login state using SharedPreferences
 * and redirects accordingly.
 */
public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;
    TextView signupText;

    SessionManager sessionManager;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply saved theme BEFORE super.onCreate
        sessionManager = new SessionManager(this);
        if (sessionManager.getTheme().equals("dark")) {
            setTheme(R.style.Theme_OnlineShop_Dark);
        } else {
            setTheme(R.style.Theme_OnlineShop_Light);
        }

        super.onCreate(savedInstanceState);

        // If user already logged in, go to Items screen
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, ItemsActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        // Initialize database
        dbHelper = new DBHelper(this);

        // Link UI elements
        emailInput = findViewById(R.id.loginEmail);
        passwordInput = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);

        // Login button click
        loginButton.setOnClickListener(v -> {

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Simple validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check user in database
            boolean validUser = dbHelper.checkUser(email, password);

            if (validUser) {
                sessionManager.setLoggedIn(true);
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, ItemsActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        // Go to Signup screen
        signupText.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
        });
    }
}
