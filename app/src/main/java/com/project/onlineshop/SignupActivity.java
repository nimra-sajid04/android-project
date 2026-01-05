package com.project.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * SignupActivity
 * --------------
 * This activity allows new users to register.
 * User data is stored in SQLite database.
 */
public class SignupActivity extends AppCompatActivity {

    EditText emailInput, passwordInput, confirmPasswordInput;
    Button signupButton;

    DBHelper dbHelper;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply theme BEFORE super.onCreate
        sessionManager = new SessionManager(this);
        if (sessionManager.getTheme().equals("dark")) {
            setTheme(R.style.Theme_OnlineShop_Dark);
        } else {
            setTheme(R.style.Theme_OnlineShop_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize DB
        dbHelper = new DBHelper(this);

        // Link UI
        emailInput = findViewById(R.id.signupEmail);
        passwordInput = findViewById(R.id.signupPassword);
        confirmPasswordInput = findViewById(R.id.signupConfirmPassword);
        signupButton = findViewById(R.id.signupButton);

        // Signup button click
        signupButton.setOnClickListener(v -> {

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Basic validation
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create user object
            User user = new User(email, password);

            // Save user in SQLite
            dbHelper.insertUser(user);

            Toast.makeText(this, "Signup Successful. Please login.", Toast.LENGTH_SHORT).show();

            // Go back to Login screen
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
