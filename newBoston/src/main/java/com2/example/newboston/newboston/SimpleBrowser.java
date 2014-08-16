package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by nava on 8/15/14.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {
    EditText url = (EditText) findViewById(R.id.url);
    Button back = (Button) findViewById(R.id.buttonBack);
    Button forward = (Button) findViewById(R.id.buttonForward);
    Button refresh = (Button) findViewById(R.id.buttonRefresh);
    Button go = (Button) findViewById(R.id.buttonGo);
    WebView webView = (WebView) findViewById(R.id.webView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        go.setOnClickListener(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new ourViewClient());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                if (webView.canGoBack())
                    webView.goBack();

                break;
            case R.id.buttonForward:
                if (webView.canGoForward())
                    webView.goForward();

                break;
            case R.id.buttonRefresh:
                webView.reload();

                break;
            case R.id.buttonGo:
                String site = url.getText().toString();
                webView.loadUrl(site);
                //hiding the keyboard after click on go button, or using EditText
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
        }
    }
}
