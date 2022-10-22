package com.if5a.kamus.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.if5a.kamus.R;
import com.if5a.kamus.activities.SplashActivity;
import com.if5a.kamus.databases.KamusHelper;

public class AppPreference {
    private SharedPreferences prefs;
    private Context context;

    public AppPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFisrtRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getString(R.string.app_first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun(){
        String key = context.getString(R.string.app_first_run);
        return prefs.getBoolean(key,true);
    }


}
