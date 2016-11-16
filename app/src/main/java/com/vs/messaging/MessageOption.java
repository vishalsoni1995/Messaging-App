package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;


public class MessageOption extends Activity {


    TextView tmessage , tsender;
Button b1;
    Datag dt;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_option);
      tsender = (TextView) findViewById(R.id.optionsender);
        tmessage = (TextView) findViewById(R.id.optionmessage);
        b1 = (Button) findViewById(R.id.bt1);
        tsender.setText(getIntent().getExtras().getString("sender"));
        tmessage.setText(getIntent().getExtras().getString("date"));
        dt=new Datag(this);
        insert();
   //  retrieve();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_option, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_check)

        {
Intent intent=new Intent(this,Login.class);
            startActivity(intent);

            return true;}

        if (id == R.id.call1) {
            call1();

            return true;
        }

        if (id == R.id.action_share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, tmessage.getText().toString() );
            sendIntent.setType("text/plain");
            startActivity(sendIntent);


        }


        return super.onOptionsItemSelected(item);
    }




    public void insert() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = dt.ins(tsender.getText().toString(),tmessage.getText().toString());

                if (inserted == true) {
                    Toast.makeText(MessageOption.this, "data inserted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MessageOption.this, "data not inserted", Toast.LENGTH_LONG).show();

                }
            }

        });


    }

    public void call1()
    {
        String s=tsender.getText().toString();
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + s));
        startActivity(intent);
    }




}
