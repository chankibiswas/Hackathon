package com.tomtom.canteen_mama_404;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MyQRCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrcode);
        ImageView mImageView = (ImageView) findViewById(R.id.imageDisplay);
        mImageView.setImageBitmap(new QRCodeUtilityActivity().loadImageBitmap(LoaderActivity.getAppContext(), WelcomeActivity.getUserId()));
    }
}
