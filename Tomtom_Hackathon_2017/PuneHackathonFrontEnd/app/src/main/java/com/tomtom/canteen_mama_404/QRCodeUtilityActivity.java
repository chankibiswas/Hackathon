package com.tomtom.canteen_mama_404;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static java.lang.System.out;

public class QRCodeUtilityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_utility);
    }

    public void generateQRCode(String qrText) {
        MultiFormatWriter multiformatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiformatWriter.encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            saveImage(LoaderActivity.getAppContext(), bitmap, qrText);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImage(Context context, Bitmap bitmap, String fileName){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput("UserQR.jpg", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Bitmap loadImageBitmap(Context context, String fileName){
        FileInputStream fileInputStream = null;
        Bitmap bitmap = null;
        try{
            fileInputStream = context.openFileInput("UserQR.jpg");
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
