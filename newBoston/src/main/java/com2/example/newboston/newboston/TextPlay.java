package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * farghe "extends" ba "implements" ine ke tu extends niazi nist
 * az hame methodhaye un class estefade konim vali tu implements
 * bayad az tamamde methodhaye be kar rafte tu un class estefade beshe
 */
public class TextPlay extends Activity implements View.OnClickListener {
    Button buttonCommand;
    ToggleButton toggleButton;
    EditText editTextPassword;
    TextView textViewCommand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        methodacton();

        toggleButton.setOnClickListener(this);
        buttonCommand.setOnClickListener(this);
    }
//baraye inke codemun khalvat beshe inaro tu ye method tarif kardim
    private void methodacton() {
        buttonCommand = (Button) findViewById(R.id.buttonCommand);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewCommand = (TextView) findViewById(R.id.textViewCommand);
    }
//in method onClick az class View.OnClickListener hast
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCommand:
                String check = editTextPassword.getText().toString();
                if(check.contentEquals("left")) {
                    textViewCommand.setGravity(Gravity.LEFT);
                }else if(check.contentEquals("center")) {
                    textViewCommand.setGravity(Gravity.CENTER);
                }else if(check.contentEquals("right")) {
                    textViewCommand.setGravity(Gravity.RIGHT);
                } else {
                    textViewCommand.setText(check);
                }
                break;
            case R.id.toggleButton:
                if (toggleButton.isChecked()) {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
