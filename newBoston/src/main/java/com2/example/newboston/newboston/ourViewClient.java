package com2.example.newboston.newboston;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by nava on 8/16/14.
 */
public class ourViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
        v.loadUrl(url);
        return true;
    }
}
