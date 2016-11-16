package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity{

    EditText ed1, ed2;
    Button b1;

    Loging dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE );
        b1 = (Button) findViewById(R.id.button);


        dt = new Loging(this);
        insert();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insert() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = dt.ins(ed1.getText().toString(), ed2.getText().toString());

                if (inserted == true) {
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    finish();

                } else {

                    Toast.makeText(Register.this, "data not inserted", Toast.LENGTH_LONG).show();

                }
            }

        });


    }










}
