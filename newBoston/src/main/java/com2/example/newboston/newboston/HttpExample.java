package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by nava on 8/20/14.
 */
public class HttpExample extends Activity {
    TextView textViewLoading;
    HttpClient client;

    final static String url = "http://";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpexample);
        textViewLoading = (TextView) findViewById(R.id.textViewLoading);
        client = new DefaultHttpClient();
        try {
            String test = lastTweet().getJSONArray("name").toString();
            textViewLoading.setText(test);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        baraye gereftan ba method HTTP
//
//        GetMethodEx test = new GetMethodEx();
//        try {
//            String returned = test.GetInternetData();
//            textViewLoading.setText(returned);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public JSONObject lastTweet() throws ClientProtocolException, IOException, JSONException {
        HttpGet get = new HttpGet(url.toString());
        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status == 200 ) {
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity);
            JSONArray timeline = new JSONArray(data);
            JSONObject last = timeline.getJSONObject(0);
            return last;
        } else {
            Toast.makeText(HttpExample.this, "error", Toast.LENGTH_SHORT);
            return null;
        }
    }
}
