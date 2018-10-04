package com.example.qaiserpasha.project2017.Objects;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Qaiser on 1/23/2018.
 */

public class SharedPrefHandler {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String keyUserName = "userName";
    String keyPassword = "password";
    String keyType = "type";
    String keyPrefName = "userData";

    public SharedPrefHandler(Context context) {
        sharedPreferences = context.getSharedPreferences(keyPrefName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public void clearSavedValues()
    {
        editor.remove(keyUserName);
        editor.remove(keyType);
        editor.remove(keyPassword);
        editor.commit();
        editor.apply();
    }

    public void saveUserName(String UserName)

    {
        editor.putString(keyUserName,UserName);
        editor.apply();
        editor.commit();
    }
    public void savePassword(String pwd)
    {
        editor.putString(keyPassword,pwd);
        editor.apply();
        editor.commit();

    }
    public void saveType(String type)
    {
        editor.putString(keyType,type);
        editor.apply();
        editor.commit();

    }
    public String getUserName()
    {
       return sharedPreferences.getString(keyUserName,"NULL");
    }
    public String getPassword()
    {
        return sharedPreferences.getString(keyPassword,"NULL");
    }
    public String getKeyType()
    {
        return  sharedPreferences.getString(keyType,"NULL");

    }
}
