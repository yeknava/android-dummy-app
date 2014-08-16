package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nava on 8/16/14.
 */
public class SharedPreferences extends Activity implements View.OnClickListener {
    EditText sharedData;
    Button buttonSave;
    Button buttonLoad;
    TextView textViewLoad;
    android.content.SharedPreferences someData;
    public static String filename = "MySharedString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
        someData = getSharedPreferences(filename, 0);
    }

    private void setupVariables() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonLoad = (Button) findViewById(R.id.buttonLoad);
        sharedData = (EditText) findViewById(R.id.sharedData);
        textViewLoad = (TextView) findViewById(R.id.textViewLoad);
        buttonSave.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                String stringData = sharedData.getText().toString();
                android.content.SharedPreferences.Editor editor = someData.edit();
                editor.putString("ourPreferencesKey", stringData);
                editor.commit();
                break;
            case R.id.buttonLoad:
                someData = getSharedPreferences(filename, 0);
                String dataReturend = someData.getString("ourPreferencesKey", "Couldn't load data");
                textViewLoad.setText(dataReturend);
                break;
        }
    }
}
