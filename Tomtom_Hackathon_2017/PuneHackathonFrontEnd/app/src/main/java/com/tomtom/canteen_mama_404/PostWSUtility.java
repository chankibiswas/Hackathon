package com.tomtom.canteen_mama_404;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by biswasc on 12/8/2017.
 */

public class PostWSUtility {



    public static String PostWS(Map<String, String> map, String url) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject json = new JSONObject(map);
        final String input = json.toString();
        Log.d("",input);

        try{
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection)url1.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            System.out.println("Post parameters : " + input);
            wr.write(input.getBytes());
            wr.flush();
            wr.close();

            int responseCode = urlConnection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = null;
            if (urlConnection.getResponseCode() >= 400) {
                in = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
            return response.toString();

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
