package com.os.paytzwakal.reg.application;

import android.content.Context;
import android.content.SharedPreferences;


public final class Config {
    private static final String PREFERENCES = "preferences_paytzwakala";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static String contactNumber;

    public static void init(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public static boolean isCameraOn() {
        return preferences.getBoolean("camera_on", false);
    }

    public static void setCameraOn(boolean camera_on) {
        editor.putBoolean("camera_on", camera_on).apply();
    }



    public static String getUserId() {
        return preferences.getString("user_id", "");
    }

    public static void setUserId(String user_id) {
        editor.putString("user_id", user_id).apply();
    }


    public static String getIsfirstlogin() {
        return preferences.getString("is_first_login", "");
    }

    public static void setIsfirstlogin(String is_first_login) {
        editor.putString("is_first_login", is_first_login).apply();
    }


    public static String getRememberToken() {
        return preferences.getString("remember_token", null);
    }

    public static void setRememberToken(String remember_token) {
        editor.putString("remember_token", remember_token).apply();
    }


    public static boolean getLogoutStatus() {
        return preferences.getBoolean("status", false);
    }

    public static void setLogoutStatus(boolean img) {
        editor.putBoolean("status", img).apply();
    }





    public static void savePreferences() {
        editor.commit();
    }

    public static void removeAll() {
        editor.clear();
        editor.apply();
    }
}
