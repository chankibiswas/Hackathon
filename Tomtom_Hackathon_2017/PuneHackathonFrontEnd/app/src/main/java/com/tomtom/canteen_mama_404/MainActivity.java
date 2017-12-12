package com.tomtom.canteen_mama_404;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private String SERVICE_URL = "http://insrvwd-cpp01:9091/cantin-mama/paymenthistory/";
    private String EMPLOYEE_SERVICE_URL = "http://insrvwd-cpp01:9091/cantin-mama/employee/";
    //Url:- http://localhost:8080/cantin-mama/paymenthistory/{type}/{user_type}/{id}
    private RequestQueue requestQueue;
    JSONObject orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(WelcomeActivity.isUserMerchant()) {
            Button btn = (Button) findViewById(R.id.settleDue);
            btn.setVisibility(View.VISIBLE);
        } else {
            Button btn = (Button) findViewById(R.id.settleDue);
            btn.setVisibility(View.INVISIBLE);
        }
        Bundle bundle = getIntent().getExtras();
        String amount;
        if(bundle!= null && bundle.containsKey("dueAmount")){
            amount = bundle.getString("dueAmount");
            TextView tv = (TextView) findViewById(R.id.show_due_amount_view);
            tv.setText(amount);
        }

        Button settltDueBtn = (Button) findViewById(R.id.settleDue);
        settltDueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQRFromPay(view);
            }
        });

    }

    public void scanQRFromPay(View view) {

        Intent payIntent = new Intent(MainActivity.this, PayActivity.class);
        startActivity(payIntent);
    }

    public void fetchOrderHistory(View view){
        //TODO call web service to fetch order history for this user

        requestQueue = Volley.newRequestQueue(this);
        String url = SERVICE_URL + "";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SERVICE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                orderHistory = response;
                Log.d("Response****", response.toString());
                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", error.getMessage());
                requestQueue.stop();
            }
        });
        requestQueue.add(jsonObjectRequest);

       // Log.d("",orderHistory.toString());
        Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
    }

    public void showQRCode(View view) {
        Intent myQRCodeIntent = new Intent(MainActivity.this, MyQRCodeActivity.class);
        startActivity(myQRCodeIntent);
    }
}
