package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by nava on 8/18/14.
 */
public class SQLview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView textViewGetInfo = (TextView) findViewById(R.id.textViewGetInfo);
        HotOrNot info = new HotOrNot(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data = info.getData();
        info.close();
        textViewGetInfo.setText(data);
    }
}
