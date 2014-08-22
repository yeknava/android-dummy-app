package com2.example.newboston.newboston;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nava on 8/16/14.
 */
public class Async extends Activity implements View.OnClickListener {
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
                new loadSomeStuff().execute(name);
                break;
        }
    }

    public class loadSomeStuff extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDialog;
        protected void onPreExecute() {
            //example of setting up something
            //progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
            progressDialog = new ProgressDialog(Async.this);
            progressDialog.setMax(100);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String...strings) {
            FileInputStream fileInputStream = null;
            String collected = null;

            for (int i=0; i<20; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            progressDialog.dismiss();
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
            return null;
        }
        protected void onProgressUpdate(Integer...progress) {
            progressDialog.incrementProgressBy(progress[0]);
        }
        protected void onPostExecute(String result) {
            textViewLoad.setText(result);
        }
    }
}