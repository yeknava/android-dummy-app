package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by nava on 8/7/14.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView tvChoice, textView;
    Button returnButton;
    RadioGroup selection;
    String gotBread, setData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();
//        Bundle gotBasket = getIntent().getExtras();
//        gotBread = gotBasket.getString("key");
//        tvChoice.setText(gotBread);
        selection.setOnCheckedChangeListener(this);
        returnButton.setOnClickListener(this);

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name", "Dena is ...");
        String values = getData.getString("list", "4");
        if (values.contentEquals("4")) {
            tvChoice.setText("Dena is ...");
        }
    }

    private void initialize() {
        tvChoice = (TextView) findViewById(R.id.choiceTextView);
        textView = (TextView) findViewById(R.id.textView22);
        returnButton = (Button) findViewById(R.id.returnButton);
        selection = (RadioGroup) findViewById(R.id.radioGroup);
    }

    @Override
    public void onClick(View view) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer", setData);
        person.putExtras(backpack);
        setResult(RESULT_OK, person);
        finish();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.developerRadioButton:
                setData = "Yes";
                break;
            case R.id.managerRadioButton:
                setData = "Yes again";
                break;
            case R.id.bothRadioButton:
                setData = "again yes!!!";
                break;
        }
        textView.setText(setData);
    }
}
