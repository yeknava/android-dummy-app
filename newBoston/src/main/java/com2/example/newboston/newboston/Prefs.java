package com2.example.newboston.newboston;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by nava on 8/9/14.
 */
public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }

    private void addPreferencesFromResource() {
    }
}
