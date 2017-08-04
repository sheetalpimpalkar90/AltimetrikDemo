package com.altimetrik.altimetrikdemo.helpers;


/**
 * Singleton class where we can save/get the data to/from shared preferences and
 * class intents are called.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.altimetrik.altimetrikdemo.activities.FeedsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class SPDSingleton {
    public static final String MY_PREFS_NAME = "config_preferences";
    ProgressDialog progressDialog;
    private static SPDSingleton spdSingleton;

    public static SPDSingleton getInstance() {
        if (spdSingleton == null) {
            spdSingleton = new SPDSingleton();
        }
        return spdSingleton;
    }

    /*Setting string value to shared preferences*/
    public void setStringToSp(String value, String key, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /*Setting int value to shared preferences*/
    public void setIntegerToSp(int value, String key, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /*Getting String value from preferences*/
    public String getStringFromSp(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "N/A");
    }

    /*Getting int value from preferences*/
    public int getIntegerFromSp(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    /*Clear preferences*/
    public void clearDataFromSp(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().commit();
    }

    /* Short toast message */
    public void showShortToast(String txtString, Context contextPassed) {
        Toast.makeText(contextPassed, txtString, Toast.LENGTH_SHORT).show();
    }

    /* Long toast message*/
    public void showLongToast(String txtString, Context contextPassed) {
        Toast.makeText(contextPassed, txtString, Toast.LENGTH_LONG).show();
    }

    /*Show progress dialog*/
    public void showProgressDialog(Context passedContext, String displayString, Boolean blockUI) {
        progressDialog = new ProgressDialog(passedContext);
        progressDialog.setMessage(displayString);
        progressDialog.setCancelable(blockUI);
        progressDialog.show();
    }

    /*Hide progress dialog*/
    public void hideProgressDialog() {
        progressDialog.hide();
    }


    /*Presenting Feeds screen using intent*/
    public void presentFeedsPage(Context passedContext) {
        Intent activityChangeIntent = new Intent(passedContext, getFeedsClass());
        passedContext.startActivity(activityChangeIntent);
    }

    private Class<FeedsActivity> getFeedsClass() {
        return FeedsActivity.class;
    }

    public String getDate(String dateTime){
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm.SSS");
        java.util.Date date = null;
        try
        {
            date = form.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
        String newDateStr = postFormater.format(date);
        return newDateStr;
    }
}