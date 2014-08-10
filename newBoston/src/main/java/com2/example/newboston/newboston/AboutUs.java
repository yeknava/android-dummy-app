package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by nava on 8/8/14.
 */
public class AboutUs extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        TextView tv = (TextView) findViewById(R.id.textView);
    }
}
