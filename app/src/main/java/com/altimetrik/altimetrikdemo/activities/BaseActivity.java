package com.altimetrik.altimetrikdemo.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.altimetrik.altimetrikdemo.R;

/**
 * BaseActivity is a class to initialize components to be used in other activities,
 * other activities will extend the BaseActivity.
 */

public class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public TextView toolBarTitle;
    public Switch configSwitch;
    /*Variables for storage permissions (gallery and camera)*/
    private static String[] READ_WRITE_STORAGE_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private static final int REQUEST_STORAGE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
//        initToolBar();
    }

    /*Initialize toolbar*/
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarTitle = (TextView) findViewById(R.id.toolBarTitleID);
        configSwitch = (Switch) findViewById(R.id.changeConfig_switchID);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /*Check Internet Connection availability*/
    public boolean isInternetConnectionAvailable() {
        if (!isNetworkAvailable()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }

    /*Method to give runtime Storage permission if android version is marshmallow or higher than marshmallow*/
    public boolean showStoragePermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Storage permissions have not been granted, So request for it.
            requestStoragePermissions();
            return false;
        } else {
            return true;
        }
    }

    private void requestStoragePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example, if the request has been denied previously.
            ActivityCompat
                    .requestPermissions(this, READ_WRITE_STORAGE_PERMISSIONS, REQUEST_STORAGE);
        } else {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(this, READ_WRITE_STORAGE_PERMISSIONS, REQUEST_STORAGE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
