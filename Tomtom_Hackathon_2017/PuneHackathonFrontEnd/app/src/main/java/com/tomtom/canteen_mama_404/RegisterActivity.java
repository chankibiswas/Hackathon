package com.tomtom.canteen_mama_404;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText userId;
    String url = "http://insrvwd-cpp01:9091/cantin-mama/employee/register";
    String merchant_url = "http://insrvwd-cpp01:9091/cantin-mama/merchant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view){

        Button btn = (Button) findViewById(R.id.register_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userId = (EditText) findViewById(R.id.user_id_editText);
                EditText emailId = (EditText) findViewById(R.id.email_editText);
                EditText name = (EditText) findViewById(R.id.name_editText);
                EditText mobile = (EditText) findViewById(R.id.mobile_editText);
                EditText password = (EditText) findViewById(R.id.password_edit_text);
                CheckBox isMerchant = (CheckBox) findViewById(R.id.checkBox);

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);

                QRCodeUtilityActivity qrCodeUtilityActivity = new QRCodeUtilityActivity();
                qrCodeUtilityActivity.generateQRCode(userId.getText().toString());

                Map<String, String> map = new HashMap<>();

                map.put("email",emailId.getText().toString());
                if(isMerchant.isChecked()) {
                    map.put("id","0");
                    map.put("userFirstName",name.getText().toString());
                    map.put("userLastName","");
                    map.put("merchantQrCode",userId.getText().toString());
                    map.put("merchantId",userId.getText().toString());
                    PostWSUtility.PostWS(map, merchant_url);
                } else {
                    map.put("id","");
                    map.put("firstName",name.getText().toString());
                    map.put("lastName","");
                    map.put("userId",userId.getText().toString());
                    PostWSUtility.PostWS(map, url);
                }
                saveUserLoginInfo(LoaderActivity.getAppContext(), true, isMerchant.isChecked(), userId.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void saveUserLoginInfo(Context context, boolean isLoggedIn, boolean isMerchant, String userId){

        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("IsLoggedIn", isLoggedIn);
        userInfo.put("IsMerchant", isMerchant);
        userInfo.put("UserId", userId);
        WelcomeActivity.loadUserInfo(LoaderActivity.getAppContext(), userInfo);
    }
}
