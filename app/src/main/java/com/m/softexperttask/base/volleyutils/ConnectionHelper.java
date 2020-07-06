package com.m.softexperttask.base.volleyutils;

import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.m.softexperttask.R;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConnectionHelper {

    private ConnectionListener connectionListener;
    private RequestQueue queue;
    private static final int TIME_OUT = 10000;
    private Gson gson;

    public ConnectionHelper(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
        queue = MyApplication.getInstance().getRequestQueue();
        gson = new Gson();
    }


    public void requestJsonObject(int method, String url, Object requestData, final Class<?> responseType) {
        final Gson gson = new Gson();
        String link = url;

        link = link.replaceAll(" ", "%20");
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(gson.toJson(requestData));
            Log.e("jsonObject", jsonObject.toString());
        } catch (Exception e) {
            Log.e("message", e.getMessage());
            e.getStackTrace();
        }

        Log.e("Url :", url);
        if (jsonObject != null) {
            Log.e("Request :", jsonObject.toString());
        } else {
            Log.e("Request :", "Make sure that you added request correctly");
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, link, jsonObject,

                response -> {
                    Log.e("Response Success:", response.toString());
                    parseData(response, responseType);
                }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showErrorDetails(volleyError);
                String error = "";
                NetworkResponse networkResponse = volleyError.networkResponse;
                if (networkResponse != null) {
                }

                if (volleyError instanceof TimeoutError) {
                    error = volleyError.getMessage();
                } else if (volleyError instanceof NoConnectionError) {
                    error = MyApplication.getInstance().getResources().getString(R.string.connection_invaild_msg);
                } else if (volleyError instanceof AuthFailureError) {
                    error = volleyError.getMessage();
                } else if (volleyError instanceof ServerError) {
                    error = volleyError.getMessage();
                } else if (volleyError instanceof NetworkError) {
                    error = MyApplication.getInstance().getResources().getString(R.string.connection_invaild_msg);
                } else if (volleyError instanceof ParseError) {
                    error = volleyError.getMessage();
                }
                connectionListener.onRequestError(error);
            }


        }) {
            @Override
            public Map getHeaders() {

                return getCustomHeaders();
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjReq);

    }



    public Map getCustomHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("lang", "en");
        return headers;
    }


    private void showErrorDetails(VolleyError volleyError) {
        String body;

        try {
            final String statusCode = String.valueOf(volleyError.networkResponse.statusCode);
            body = new String(volleyError.networkResponse.data, "UTF-8");
            Log.e("TAG", "Error Body " + body + " StatusCode " + statusCode);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void parseData(JSONObject response, final Class<?> responseType) {
        try {
            if (response.toString().equals("")) {
                connectionListener.onRequestError(null);
            } else {
                connectionListener.onRequestSuccess(gson.fromJson(response.toString(), responseType));
            }

        } catch (Exception e) {
            Log.e("parseData", "parseData: " + e.getMessage());
            connectionListener.onRequestError(null);
        }

    }

    private Map<String, String> getParameters(final Object requestData) {
        Map<String, String> params = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(gson.toJson(requestData));
            for (int i = 0; i < jsonObject.names().length(); i++) {
                params.put(jsonObject.names().getString(i), jsonObject.get(jsonObject.names().getString(i)) + "");
                Log.e("PARAMS", jsonObject.get(jsonObject.names().getString(i)) + "");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return params;
    }

}