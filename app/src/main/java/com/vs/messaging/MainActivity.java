package com.vs.messaging;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends Activity {


ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=(ImageView)findViewById(R.id.imageView2);

        Thread thread = new Thread() {
            public void run() {
                try {
                    Animation animation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.a);
                    iv.startAnimation(animation);
                    sleep(2000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(MainActivity.this, ReceiveSMSActivity.class);
                    startActivity(intent);
                }
            }


        };
        thread.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }








}
