package com.friska.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static final String SP_APP = "spApp";

    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Preferences(Context context){
        sp = context.getSharedPreferences(SP_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
