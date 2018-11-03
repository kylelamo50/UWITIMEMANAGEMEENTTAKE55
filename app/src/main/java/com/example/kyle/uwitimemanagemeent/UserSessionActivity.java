package com.example.kyle.uwitimemanagemeent;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserSessionActivity{

    private SharedPreferences pref;

    private Context context;



    public UserSessionActivity(Context context){
        this.context=context;
        pref=context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);

    }

    public  void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_preference_status),status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        boolean status=false;
        status=pref.getBoolean(context.getResources().getString(R.string.login_preference_status),status);
        return status;
    }







}
