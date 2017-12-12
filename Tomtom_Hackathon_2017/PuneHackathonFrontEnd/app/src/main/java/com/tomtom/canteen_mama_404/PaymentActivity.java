package com.tomtom.canteen_mama_404;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    TextView merchantId;
    Button btn;
    EditText amount;
    private static final String url = "http://insrvwd-cpp01:9091/cantin-mama/payment/internal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Bundle bundle = getIntent().getExtras();
        final String qrData = bundle.getString("QRValue");

        merchantId = (TextView)findViewById(R.id.merchant_id_textView);
        merchantId.setText(qrData);

        btn = (Button)findViewById(R.id.payment_button);
        amount = findViewById(R.id.pay_amount_editText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap();

                map.put("userId",WelcomeActivity.getUserId());
                map.put("merchantId", qrData);
                map.put("amount",""+amount.getText());

                String response = PostWSUtility.PostWS(map, url);
                if(!response.contains("fail")){
                    try {
                        JSONObject json = new JSONObject(response);
                        if(json.getString("dueAmmount") != null){
                            Intent intent = new Intent(PaymentActivity.this,MainActivity.class);
                            intent.putExtra("dueAmount", json.getString("dueAmmount"));
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
