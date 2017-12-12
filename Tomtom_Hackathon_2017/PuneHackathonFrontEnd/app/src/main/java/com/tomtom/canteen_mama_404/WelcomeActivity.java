package com.tomtom.canteen_mama_404;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {

    private static Map<String, Object> userInfo = new HashMap<String, Object>();
    private String SERVICE_URL = "http://insrvwd-cpp01:9091/cantin-mama/employee/login/";
    private String MERCHANT_SERVICE_URL = "http://insrvwd-cpp01:9091/cantin-mama/merchant/login/";
    private RequestQueue requestQueue;
    JSONObject userDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUserInfo(LoaderActivity.getAppContext(), userInfo);
        if(isUserLoggedIn()) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_welcome);
        }
    }

    public void login(View view) {
        EditText userId = (EditText) findViewById(R.id.editText);
        EditText password = (EditText) findViewById(R.id.editText2);
        // Fetch user info and then set User cache
        uploadCache(userId.getText().toString());
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void uploadCache(final String userId) {
        requestQueue = Volley.newRequestQueue(this);
        String url = SERVICE_URL + userId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                userDetail = response;
                try {
                    String userId = response.getString("userId");
                    Map<String, Object> userInfo = new HashMap<String, Object>();
                    userInfo.put("IsLoggedIn", true);
                    userInfo.put("IsMerchant", false);
                    userInfo.put("UserId", userId);
                    WelcomeActivity.loadUserInfo(LoaderActivity.getAppContext(), userInfo);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkForMerchant(userId);
                requestQueue.stop();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void checkForMerchant(String userId) {
        requestQueue = Volley.newRequestQueue(this);
        String url = MERCHANT_SERVICE_URL + userId;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                userDetail = response;
                try {
                    String userId = response.getString("userId");
                    Map<String, Object> userInfo = new HashMap<String, Object>();
                    userInfo.put("IsLoggedIn", true);
                    userInfo.put("IsMerchant", true);
                    userInfo.put("UserId", userId);
                    WelcomeActivity.loadUserInfo(LoaderActivity.getAppContext(), userInfo);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void register(View view) {
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isUserLoggedIn() {

        Boolean ifUserLoggedIn = (Boolean)userInfo.get("IsLoggedIn");
        if(ifUserLoggedIn == null || !ifUserLoggedIn.booleanValue()) {
            return false;
        }
        return true;
    }

    public static Boolean isUserMerchant() {
        Boolean merchantFlag = (Boolean)userInfo.get("IsMerchant");
        if(merchantFlag == null || !merchantFlag) {
            return false;
        }
        return true;
    }

    public static String getUserId() {

        return (String)userInfo.get("UserId");
    }

    public static void loadUserInfo(Context context, Map<String, Object> userInfo) {
        WelcomeActivity.userInfo = userInfo;
    }
}
