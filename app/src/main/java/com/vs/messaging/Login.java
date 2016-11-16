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


public class Login extends Activity {

    Button b1;
    EditText ed,ed1;
    Loging dt;
    String username="1";
    String password="1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE );
        b1 = (Button) findViewById(R.id.login);
        ed= (EditText) findViewById(R.id.pass);
        ed1= (EditText) findViewById(R.id.user);
        dt = new Loging(this);


    }

    public void retrieve() {



                Cursor res = dt.fet();
        if (res.getColumnCount()>0) {
            while (res.moveToNext()) {
                username = res.getString(0);
                password = res.getString(1);
            }

        }


    }


    public void edit(View view)
    {


        retrieve();
        if (ed.getText().toString().equals(password) && ed1.getText().toString().equals(username))
        {
            Intent intent=new Intent(Login.this,EditDataBase.class);
            ed1.setText("");
            ed.setText("");
            startActivity(intent);

        }


        else
            Toast.makeText(this,"Invalid username or password" , Toast.LENGTH_LONG).show();
    }



    public void check(View view)
    {
        retrieve();

        if (ed.getText().toString().equals(password) && ed1.getText().toString().equals(username))
        {
            Intent intent=new Intent(Login.this,Database.class);
            startActivity(intent);
            ed1.setText("");
            ed.setText("");
        }
        else
            Toast.makeText(this,"Invalid Username or Password" , Toast.LENGTH_LONG).show();
    }

    public void register(View view)
    {
        retrieve();
        if (username.equals("1") && password.equals("1"))
        {
            Intent intent=new Intent(this,Register.class);
            startActivity(intent);
        }


        if (username != "1" && password !="1")

            Toast.makeText(this,"You already registered" , Toast.LENGTH_LONG).show();
    }

    public void forget(View view)
    {
        Intent intent=new Intent(this,ForgetPassword.class);
        startActivity(intent);
    }
}
