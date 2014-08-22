package com2.example.newboston.newboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by nava on 8/18/14.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {
    Button buttonSqlUpdate, buttonViewDatabase, buttonGetInfo, buttonEditEntry, buttonDeleteEntry;
    EditText editTextSqlName, editTextHotRate, editTextRowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);
        buttonSqlUpdate = (Button) findViewById(R.id.buttonSqlUpdate);
        buttonViewDatabase = (Button) findViewById(R.id.buttonViewDatabase);
        buttonGetInfo = (Button) findViewById(R.id.buttonGetInfo);
        buttonEditEntry = (Button) findViewById(R.id.buttonEditEntry);
        buttonDeleteEntry = (Button) findViewById(R.id.buttonDeleteEntry);
        editTextSqlName = (EditText) findViewById(R.id.editTextSqlName);
        editTextHotRate = (EditText) findViewById(R.id.editTextHotRate);
        editTextRowId = (EditText) findViewById(R.id.editTextRowId);
        buttonSqlUpdate.setOnClickListener(this);
        buttonViewDatabase.setOnClickListener(this);
        buttonGetInfo.setOnClickListener(this);
        buttonEditEntry.setOnClickListener(this);
        buttonDeleteEntry.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSqlUpdate:
                boolean didItWork = true;
                try {
                    String name = editTextSqlName.getText().toString();
                    String hotness = editTextHotRate.getText().toString();
                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createentry(name, hotness);
                    entry.close();
                } catch (Exception e) {
                    didItWork = false;
                    Dialog dialog = new Dialog(this);
                    String error = e.toString();
                    dialog.setTitle("Nooooo :(");
                    TextView textView = new TextView(this);
                    textView.setText(error);
                    dialog.setContentView(textView);
                    dialog.show();
                } finally {
                    if (didItWork) {
                        Dialog dialog = new Dialog(this);
                        dialog.setTitle("Yessss");
                        TextView textView = new TextView(this);
                        textView.setText("Success");
                        dialog.setContentView(textView);
                        dialog.show();
                    }
                }
                break;
            case R.id.buttonViewDatabase:
                try {
                    Intent intent = new Intent("com2.example.newboston.newboston.SQLVIEW");
                    startActivity(intent);
                } catch (Exception e) {
                    Dialog dialog = new Dialog(this);
                    String error = e.toString();
                    dialog.setTitle("error");
                    TextView textView = new TextView(this);
                    textView.setText(error);
                    dialog.setContentView(textView);
                    dialog.show();
                }
                break;
            case R.id.buttonGetInfo:
                try {
                    String s = editTextRowId.getText().toString();
                    long rowId = Long.parseLong(s);
                    HotOrNot hon = new HotOrNot(this);
                    hon.open();
                    String returnedName = hon.getName(rowId);
                    String returnedHotness = hon.getHotness(rowId);
                    hon.close();
                    editTextSqlName.setText(returnedName);
                    editTextHotRate.setText(returnedHotness);
                } catch (SQLException e) {
                    Dialog dialog = new Dialog(this);
                    String error = e.toString();
                    dialog.setTitle("error");
                    TextView textView = new TextView(this);
                    textView.setText(error);
                    dialog.setContentView(textView);
                    dialog.show();
                }
                break;
            case R.id.buttonEditEntry:
                try {
                String name = editTextSqlName.getText().toString();
                String hotness = editTextHotRate.getText().toString();
                String s = editTextRowId.getText().toString();
                long rowId = Long.parseLong(s);
                HotOrNot ex = new HotOrNot(this);
                    ex.open();
                    ex.updateEntry(rowId, name, hotness);
                    ex.close();
                } catch (SQLException e) {
                    Dialog dialog = new Dialog(this);
                    String error = e.toString();
                    dialog.setTitle("error");
                    TextView textView = new TextView(this);
                    textView.setText(error);
                    dialog.setContentView(textView);
                    dialog.show();
                }
                break;
            case R.id.buttonDeleteEntry:
                try {
                String s = editTextRowId.getText().toString();
                long rowId = Long.parseLong(s);
                HotOrNot del = new HotOrNot(this);
                    del.open();
                    del.deleteEntry(rowId);
                    del.close();
                } catch (SQLException e) {
                    Dialog dialog = new Dialog(this);
                    String error = e.toString();
                    dialog.setTitle("error");
                    TextView textView = new TextView(this);
                    textView.setText(error);
                    dialog.setContentView(textView);
                    dialog.show();
                }
                break;
        }
    }
}
