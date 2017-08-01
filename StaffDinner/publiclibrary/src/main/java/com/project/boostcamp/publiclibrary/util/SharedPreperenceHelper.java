package com.project.boostcamp.publiclibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.project.boostcamp.publiclibrary.data.Admin;
import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.publiclibrary.data.Client;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class SharedPreperenceHelper {
    private static final String PREFERENCE_NAME = "StaffDinner";
    private static final String KEY_APPLY = "apply";
    private static SharedPreperenceHelper instance;
    private SharedPreferences preferences;
    private Gson gson;
    public static SharedPreperenceHelper getInstance(Context context) {
        if(instance == null) {
            instance = new SharedPreperenceHelper(context);
        }
        return instance;
    }

    public SharedPreperenceHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveApply(Apply apply) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Apply.class.getName(), gson.toJson(apply));
        editor.apply();
    }

    public Apply getApply() {
        String str = preferences.getString(Apply.class.getName(), "");
        return gson.fromJson(str, Apply.class);
    }

    public void saveClient(Client client) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Client.class.getName(), gson.toJson(client));
        editor.apply();
    }

    public Client getClient() {
        String str = preferences.getString(Client.class.getName(), "");
        return gson.fromJson(str, Client.class);
    }

    public void saveAdmin(Admin admin) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Admin.class.getName(), gson.toJson(admin));
        editor.apply();
    }

    public Admin getAdmin() {
        String str = preferences.getString(Admin.class.getName(), "");
        return gson.fromJson(str, Admin.class);
    }
}
