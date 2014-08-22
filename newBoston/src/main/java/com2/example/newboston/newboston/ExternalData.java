package com2.example.newboston.newboston;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

/**
 * Created by nava on 8/17/14.
 * baraye inke befahmim be external storage dastresi darim ya na
 * va ye file tu folderi ke karbar entekhab mikone save konim
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView canWrite, canRead;
    String state;
    boolean canR, canW;
    Spinner spinner;
    String[] paths = {"music", "picture", "downloads"};
    File path = null;
    File path2 = null;
    EditText editTextSaveAs;
    Button buttonConfirmSaveAs, buttonSaveFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canWrite = (TextView) findViewById(R.id.textViewWrite);
        canRead = (TextView) findViewById(R.id.textViewRead);
        editTextSaveAs = (EditText) findViewById(R.id.editTextSaveAs);
        buttonConfirmSaveAs = (Button) findViewById(R.id.buttonConfirmSaveAs);
        buttonSaveFile = (Button) findViewById(R.id.buttonSaveFile);
        buttonConfirmSaveAs.setOnClickListener(this);
        buttonSaveFile.setOnClickListener(this);
        checkState();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //read and write
            canWrite.setText("true");
            canRead.setText("true");
            canR = canW = true;
        } else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            //read but cant write
            canWrite.setText("false");
            canRead.setText("true");
            canR = true;
            canW = false;
        } else {
            canWrite.setText("false");
            canRead.setText("false");
            canR = canW = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int position = spinner.getSelectedItemPosition();
        switch (position) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonConfirmSaveAs:
                String f = editTextSaveAs.getText().toString();
                path2 = new File(path, f);
                checkState();
                if (canWrite == canRead == true) {
                    //baraye sakhtan directory az in dastur estefade mishe path.mkdirs()
                    try {
                        InputStream inputStream = getResources().openRawResource(R.drawable.splash_screen);
                        OutputStream outputStream = new FileOutputStream(path2);
                        byte[] data = new byte[inputStream.available()];
                        inputStream.read(data);
                        outputStream.write(data);
                        inputStream.close();
                        outputStream.close();
                        Toast toast = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
                        toast.show();
                        //Update Files for user to use
                        MediaScannerConnection.scanFile
                                (ExternalData.this,
                                        new String[] {path2.toString()},
                                        null,
                                        new MediaScannerConnection.OnScanCompletedListener() {
                                            @Override
                                            public void onScanCompleted(String s, Uri uri) {
                                                Toast toast1 = Toast.makeText(ExternalData.this, "scan compelete",
                                                        Toast.LENGTH_SHORT);
                                                toast1.show();
                                            }
                                        }
                                );
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.buttonSaveFile:
                buttonSaveFile.setVisibility(View.VISIBLE);
                break;
        }
    }
}
