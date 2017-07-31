package com.project.boostcamp.publiclibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.project.boostcamp.publiclibrary.data.Apply;

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
        editor.putString(KEY_APPLY, gson.toJson(apply));
        editor.apply();
    }

    public Apply getApply() {
        String str = preferences.getString(KEY_APPLY, "");
        return gson.fromJson(str, Apply.class);
    }
}
