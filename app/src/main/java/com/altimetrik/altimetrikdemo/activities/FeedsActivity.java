package com.altimetrik.altimetrikdemo.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;

import com.altimetrik.altimetrikdemo.R;
import com.altimetrik.altimetrikdemo.adapters.FeedsAdapter;
import com.altimetrik.altimetrikdemo.helpers.Constants;
import com.altimetrik.altimetrikdemo.helpers.SPDSingleton;
import com.altimetrik.altimetrikdemo.helpers.WebServiceDelegate;
import com.altimetrik.altimetrikdemo.helpers.Webservices;
import com.altimetrik.altimetrikdemo.models.RssFeedModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Feeds screen to show the RSS feed data from iTunes
 */

public class FeedsActivity extends BaseActivity implements WebServiceDelegate {

    RecyclerView feedsRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    //Feed Results to get the array of tracks
    ArrayList<RssFeedModel.FeedsModel.FeedResultsModel> feedResultsModels;

    //boolean to store the switch ON/OFF status
    boolean shouldShowImages = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        /*Initiating toolbar from BaseActivity*/
        initToolBar();
        toolBarTitle.setText(getResources().getString(R.string.header_rss_feeds));
        getSwitchStatus();

        initViews();
        setListeners();
        //If internet available call the webservice to get the tracks
        if(isInternetConnectionAvailable()){
            getRssFeeds();
        }else{
            SPDSingleton.getInstance().showShortToast(getResources().getString(R.string.no_internet_connection),
                    getApplicationContext());
        }

    }

    /*Method to get the configuration switch ON/OFF status to hide and show images*/
    private void getSwitchStatus() {
        /*Check whether switch ON or OFF*/
        if(SPDSingleton.getInstance().getStringFromSp(Constants.spSwitchStatus, getApplicationContext()).equalsIgnoreCase("OFF")){
            configSwitch.setChecked(false);
            shouldShowImages = false;
        }else{
            configSwitch.setChecked(true);
            shouldShowImages = true;
        }
    }

    /*Initialize the views in the class*/
    private void initViews() {
        feedsRecyclerView = (RecyclerView) findViewById(R.id.feeds_recyclerViewID);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        feedsRecyclerView.setLayoutManager(layoutManager);
        feedsRecyclerView.setHasFixedSize(true);
    }

    /*Set listener to switch. On switch ON hide the images by reloading the recycler view and by
    * switch OFF get the original recycler view*/
    private void setListeners() {
        configSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Save the checked status of switch in shared preferences
                    SPDSingleton.getInstance().setStringToSp("ON", Constants.spSwitchStatus, FeedsActivity.this);
                    FeedsAdapter feedsAdapter = new FeedsAdapter(FeedsActivity.this, feedResultsModels, true);
                    feedsRecyclerView.setAdapter(feedsAdapter);
                }else{
                    //Save the checked status of switch in shared preferences
                    SPDSingleton.getInstance().setStringToSp("OFF", Constants.spSwitchStatus, FeedsActivity.this);
                    FeedsAdapter feedsAdapter = new FeedsAdapter(FeedsActivity.this, feedResultsModels, false);
                    feedsRecyclerView.setAdapter(feedsAdapter);
                }
            }
        });
    }

    /*Get the iTunes RSS feed by calling the webservice*/
    private void getRssFeeds() {
        Webservices webservicesObj = new Webservices(FeedsActivity.this, FeedsActivity.this);
        webservicesObj.startGetRequest(Constants.GET_HOT_TRACKS);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_feeds, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onPreFetch() {
        SPDSingleton.getInstance().showProgressDialog(FeedsActivity.this, getResources().getString(R.string.progress_getting_feeds), true);
    }

    @Override
    public void jSONResponseAfterRequest(JSONObject responseObject) {
        SPDSingleton.getInstance().hideProgressDialog();

        //Gson builder to parse the json data coming from server
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        //Convert the json coming from server to gson that is to DTO class and store the feed results array

        RssFeedModel rssFeedModel = gson.fromJson(String.valueOf(responseObject), RssFeedModel.class);
        feedResultsModels = rssFeedModel.getFeedsModel().getFeedResultsModelArrayList();

        //Pass the results array to adapter to populate in recycler view
        FeedsAdapter feedsAdapter = new FeedsAdapter(FeedsActivity.this, feedResultsModels, shouldShowImages);
        feedsRecyclerView.setAdapter(feedsAdapter);
    }

}
