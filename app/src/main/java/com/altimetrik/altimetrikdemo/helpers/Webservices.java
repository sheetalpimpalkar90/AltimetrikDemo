package com.altimetrik.altimetrikdemo.helpers;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/*Handling Volley Get,post,delete requests*/
public class Webservices
{
    private WebServiceDelegate  webserviceObject; // Instance of interface
    private Activity    passedActivity;

    public  Webservices(Activity getActivity, WebServiceDelegate getWeservice)
    {
        webserviceObject = getWeservice;
        passedActivity   = getActivity;
    }

    /*Volley POST request*/
    public  void startPostRequest(String serviceName, JSONObject params)
    {
        webserviceObject.onPreFetch();
        String finalURL = Constants.BASE_SERVER+serviceName;
        System.out.print(finalURL);

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.POST, finalURL, params, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // the response is already constructed as a JSONObject!
                        Log.i("Response >> ", ""+response);
                        webserviceObject.jSONResponseAfterRequest(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.print(error);

                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("status", "fail");
                            obj.put("errors", "Request cannot initiate");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        webserviceObject.jSONResponseAfterRequest(obj);
                    }
                });

        Volley.newRequestQueue(passedActivity).add(jsonRequest);

    }

    /*Volley PATCH request*/
    public  void startPatchRequest(String serviceName, JSONObject params)
    {
        webserviceObject.onPreFetch();
        String finalURL = Constants.BASE_SERVER+serviceName;
        System.out.print(finalURL);

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.PATCH, finalURL, params, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // the response is already constructed as a JSONObject!
                        Log.i("Response >> ", ""+response);
                        webserviceObject.jSONResponseAfterRequest(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.print(error);

                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("status", "fail");
                            obj.put("errors", "Request cannot initiate");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        webserviceObject.jSONResponseAfterRequest(obj);
                    }
                });

        Volley.newRequestQueue(passedActivity).add(jsonRequest);

    }

    /*Volley GET request*/
    public void startGetRequest(String serviceName)
    {
        webserviceObject.onPreFetch();
        Log.i("URL >>", Constants.BASE_SERVER+serviceName);
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, Constants.BASE_SERVER+serviceName, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // the response is already constructed as a JSONObject!
                        Log.i("RESPONSE ====>>>> ", ""+response);
                        webserviceObject.jSONResponseAfterRequest(response);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("status", "fail");
                            obj.put("errors", "Request cannot initiate");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        webserviceObject.jSONResponseAfterRequest(obj);
                    }
                });

        Volley.newRequestQueue(passedActivity).add(jsonRequest);

    }

}
