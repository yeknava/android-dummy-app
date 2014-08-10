package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by nava on 8/6/14.
 */

public class Camera extends Activity implements View.OnClickListener {
    ImageView wall;
    ImageButton setWall;
    Button takePic;
    Intent imageIntent;
    final static int cameraData = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        initialize();
        InputStream is = getResources().openRawResource(R.drawable.splash_screen);
        bmp = BitmapFactory.decodeStream(is);
        //setWall.setOnClickListener(this);
        //takePic.setOnClickListener(this);
    }

    private void initialize() {
        wall = (ImageView) findViewById(R.id.wall);
        setWall = (ImageButton) findViewById(R.id.setWall);
        takePic = (Button) findViewById(R.id.takePic);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setWall:

                break;
            case R.id.takePic:
                imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(imageIntent, cameraData);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            wall.setImageBitmap(bmp);
        }
    }
}
