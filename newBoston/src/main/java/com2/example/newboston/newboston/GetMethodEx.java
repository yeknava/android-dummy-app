package com2.example.newboston.newboston;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by nava on 8/20/14.
 */
public class GetMethodEx {
    public String GetInternetData() throws Exception {
        BufferedReader in = null;
        String data = null;
        try {
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://");
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String string = "";
            String newline = System.getProperty("line.separator");
            while ((string = in.readLine()) != null) {
                stringBuffer.append(string+newline);
            }
            in.close();
             data = stringBuffer.toString();
            return data;
        } finally {
            if (in != null) {
                try {
                    in.close();
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
