package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nava on 8/16/14.
 */
public class InternalData extends Activity implements View.OnClickListener {
    EditText sharedData;
    Button buttonSave;
    Button buttonLoad;
    TextView textViewLoad;
    FileOutputStream fileOutputStream;
    String name = "InternalString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
    }

    private void setupVariables() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonLoad = (Button) findViewById(R.id.buttonLoad);
        sharedData = (EditText) findViewById(R.id.sharedData);
        textViewLoad = (TextView) findViewById(R.id.textViewLoad);
        buttonSave.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
        try {
            fileOutputStream = openFileOutput(name, Context.MODE_PRIVATE);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                String data = sharedData.getText().toString();
                //Saving Data via File
                /*
                File file = new File(name);
                try {
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                try {
                    fileOutputStream = openFileOutput(name, Context.MODE_PRIVATE);
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonLoad:
                FileInputStream fileInputStream = null;
                String collected = null;
                try {
                    fileInputStream = openFileInput(name);
                    byte[] dataArray = new byte[fileInputStream.available()];
                    while (fileInputStream.read(dataArray) != -1) {
                        collected = new String(dataArray);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileInputStream.close();
                        textViewLoad.setText(collected);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
