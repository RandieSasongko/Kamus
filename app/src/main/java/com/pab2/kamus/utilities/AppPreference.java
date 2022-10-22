package com.pab2.kamus.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.pab2.kamus.R;

public class AppPreference {
    private SharedPreferences preferences;
    private Context context;

    public AppPreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(context.getString(R.string.app_first_run), input);
        editor.commit();
    }

    public boolean getFirstRun(){
        return preferences.getBoolean(context.getString(R.string.app_first_run), true);
    }
}
