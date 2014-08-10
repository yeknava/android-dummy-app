package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by nava on 8/7/14.
 */
public class Data extends Activity implements View.OnClickListener {
    Button start, startFor;
    EditText sendEditText;
    TextView gotText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    private void initialize() {
        start = (Button) findViewById(R.id.startActivityButton);
        startFor = (Button) findViewById(R.id.startActivityForResultButton);
        sendEditText = (EditText) findViewById(R.id.sendEditText);
        gotText = (TextView) findViewById(R.id.gotTextView);

    }
//baraye enteghale dade beyne activityha tori raftar mikonim ke engar mikhaym berim piknik
// ye sabad darim toosh harchi mikhaym mizarim va barash ye klid tarif mikonim
// bad ba ye intent ke masiresh moshakhase pasesh midim bere ;)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startActivityButton:
                String bread = sendEditText.getText().toString();
                Bundle basket = new Bundle(); //ye bundle tarif mikonim be esme sabad
                basket.putString("key", bread); //esme klid ro mizarim key ke untaraf bedoonim kodoom klid male mast mikhaym baste ro bardarim
                Intent a = new Intent(Data.this, OpenedClass.class); //ye intent tarif mikonim ke az class Data mire be OpenedClass
                a.putExtras(basket); //baste ro mizarim to intent
                startActivity(a); // activity ro aghaz mikonim
                break;
            case R.id.startActivityForResultButton:
                Intent i = new Intent(Data.this, OpenedClass.class);
                startActivityForResult(i, 0);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle basket = data.getExtras();
            String s = basket.getString("answer");
            gotText.setText(s);

        }
    }
}
