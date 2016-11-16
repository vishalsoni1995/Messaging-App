package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ForgetPassword extends Activity {

    Loging dt;
    EditText e1, e2, e3;
    String p,u;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        dt = new Loging(this);
        e1 = (EditText) findViewById(R.id.editText3);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE );
        e2 = (EditText) findViewById(R.id.editText4);
        e3 = (EditText) findViewById(R.id.editText5);
        b=(Button)findViewById(R.id.button4);
        update();
    }

    public void cancel(View view) {
        finish();

    }


    public void retrieve() {


        Cursor res = dt.fet();
        if (res.getColumnCount() > 0) {
            while (res.moveToNext()) {
                u = res.getString(0);
                p = res.getString(1);

            }

        }
    }



    public void update()
    {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve();

                if (e1.getText().toString().equals(p)) {





                     if (e2.getText().toString().equals(e3.getText().toString())) {

                         if (e2.getText().toString().equals("")) {

                             Toast.makeText(ForgetPassword.this, "Please enter new password", Toast.LENGTH_LONG).show();


                         } else {

                             boolean updated = dt.upd(u, e2.getText().toString());
                             if (updated == true) {
                                 Toast.makeText(ForgetPassword.this, "Password has been changed successfully", Toast.LENGTH_LONG).show();
                                 finish();
                             }
                         }
                     }

                    else
                         Toast.makeText(ForgetPassword.this, "New password and confirmed password not matched", Toast.LENGTH_LONG).show();



                }

                else
                    Toast.makeText(ForgetPassword.this, "This is not your current password", Toast.LENGTH_LONG).show();










            }
        });
    }



}




