package com.project.onlineshop;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SessionManager
 * ----------------
 * This class handles:
 * 1. Login status (isLoggedIn)
 * 2. Theme selection (light / dark)
 * 3. Logout functionality
 *
 * Uses SharedPreferences to store data permanently.
 */
public class SessionManager {

    // SharedPreferences file name
    private static final String PREF_NAME = "shop_app_prefs";

    // Keys for stored values
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_THEME = "theme";

    // SharedPreferences objects
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /**
     * Constructor
     * @param context Application or Activity context
     */
    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /* ---------------- LOGIN MANAGEMENT ---------------- */

    /**
     * Save login state
     * @param value true = logged in, false = logged out
     */
    public void setLoggedIn(boolean value) {
        editor.putBoolean(KEY_IS_LOGGED_IN, value);
        editor.apply(); // save changes
    }

    /**
     * Check login state
     * @return true if logged in
     */
    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    /* ---------------- THEME MANAGEMENT ---------------- */

    /**
     * Save selected theme
     * @param theme "light" or "dark"
     */
    public void setTheme(String theme) {
        editor.putString(KEY_THEME, theme);
        editor.apply();
    }

    /**
     * Get saved theme
     * @return "light" by default
     */
    public String getTheme() {
        return preferences.getString(KEY_THEME, "light");
    }

    /* ---------------- LOGOUT ---------------- */

    /**
     * Clear all stored data
     * Used during logout
     */
    public void logout() {
        editor.clear();
        editor.apply();
    }
}
