//package com.altimetrik.altimetrikdemo.helpers;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
///**
// * Custom parser class is used to parse the json coming from webservices
// */
//
//public class CustomParser {
//    /**
//     * Private constructor to prevent instantiation
//     */
//    private CustomParser() {
//    }
//
//    /*Parse facebook user info from Json*/
//    public static FBUserInfo parseFBUserInfo(JSONObject object) {
//
//        FBUserInfo fbUserInfo = new FBUserInfo();
//        try {
//            fbUserInfo.setUsername(object.getString("name"));
//            fbUserInfo.setProfilePicture(object.getJSONObject("picture").getJSONObject("data").getString("url"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return fbUserInfo;
//    }
//
//    /*Parsing of top and popular deal data from json using Gson*/
//    public static DealModel parseDeals(JSONObject responseObject){
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.create();
//        return gson.fromJson(String.valueOf(responseObject), DealModel.class);
//
//    }
//}
