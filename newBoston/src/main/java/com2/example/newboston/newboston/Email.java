package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nava on 8/6/14.
 */
public class Email extends Activity implements View.OnClickListener {
    TextView TextViewEmail;
    TextView TextViewEmailText;
    EditText EditTextEmail;
    EditText EditTextEmailText;
    Button SendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initializeVariablesMethod();
        SendButton.setOnClickListener(this);
    }
    private void initializeVariablesMethod() {
        TextViewEmail = (TextView) findViewById(R.id.TextViewEmail);
        TextViewEmailText = (TextView) findViewById(R.id.TextViewEmailText);
        EditTextEmail = (EditText) findViewById(R.id.EditTextEmail);
        EditTextEmailText = (EditText) findViewById(R.id.EditTextEmailText);
        SendButton = (Button) findViewById(R.id.SendButton);
    }
    @Override
    public void onClick(View view) {
        String emailAddress[] = { EditTextEmail.getText().toString() };
        String emailMainText = EditTextEmailText.getText().toString();

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hiii");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailMainText);
        startActivity(emailIntent);
    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}
