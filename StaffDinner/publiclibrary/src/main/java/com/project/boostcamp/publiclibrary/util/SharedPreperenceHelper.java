package com.project.boostcamp.publiclibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.project.boostcamp.publiclibrary.data.Admin;
import com.project.boostcamp.publiclibrary.data.Application;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.data.Geo;
import com.project.boostcamp.publiclibrary.domain.GeoDTO;
import com.project.boostcamp.publiclibrary.domain.LoginDTO;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class SharedPreperenceHelper {
    private static final String PREFERENCE_NAME = "StaffDinner";
    private static final String KEY_APPLY = "apply";
    public static final String EXTRA_LOGIN_ID = "login_id";
    public static final String EXTRA_LOGIN_TYPE = "login_type";
    public static final String EXTRA_GEO = "geo";
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

    public void saveApply(Application application) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Application.class.getName(), gson.toJson(application));
        editor.apply();
    }

    public Application getApply() {
        String str = preferences.getString(Application.class.getName(), "");
        return gson.fromJson(str, Application.class);
    }

    public void saveLogin(LoginDTO loginDTO) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EXTRA_LOGIN_ID, loginDTO.getId());
        editor.putInt(EXTRA_LOGIN_TYPE, loginDTO.getType());
        editor.apply();
    }

    public String getLoginId() {
        return preferences.getString(EXTRA_LOGIN_ID, "");
    }

    public int getLoginType() {
        return preferences.getInt(EXTRA_LOGIN_TYPE, -1);
    }

    public void saveGeo(GeoDTO geo) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EXTRA_GEO, gson.toJson(geo));
        editor.apply();
    }

    public GeoDTO getGeo() {
        String str = preferences.getString(EXTRA_GEO, "");
        return gson.fromJson(str, GeoDTO.class);
    }
}
