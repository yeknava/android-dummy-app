package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by nava on 8/14/14.
 */
public class Tabs extends Activity implements View.OnClickListener {
    TabHost tabHost;
    TextView showResults;
    long start, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        Button buttonStop = (Button) findViewById(R.id.buttonStop);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        showResults = (TextView) findViewById(R.id.showResults);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("StopWatch");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Tab 2");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("Tab 3");
        tabHost.addTab(tabSpec);
        start = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                start = System.currentTimeMillis();

                break;
            case R.id.buttonStop:
                stop = System.currentTimeMillis();

                if (start != 0) {
                    long result = stop-start;
                    int millis = (int) result;
                    int seconds = millis / 1000;
                    int minutes = seconds / 60;
                    millis = millis % 100;
                    seconds = seconds % 60;
                    showResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
                }
                break;
            case R.id.buttonAdd:
                TabHost.TabSpec newTab = tabHost.newTabSpec("tag4");
                newTab.setContent(new TabHost.TabContentFactory() {
                    public View createTabContent(String tag) {
                        TextView textView = new TextView(Tabs.this);
                        textView.setText("You've created a new Tab");
                        return (textView);
                    }
                });
                newTab.setIndicator("New");
                tabHost.addTab(newTab);
                break;
        }
    }
}
