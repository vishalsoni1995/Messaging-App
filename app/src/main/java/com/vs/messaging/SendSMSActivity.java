package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SendSMSActivity extends Activity implements View.OnClickListener {

    Button sendSmsBtn;
    EditText toPhoneNumber,smsMessageEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
       // sendSmsBtn=(Button)findViewById(R.id.btnSENDsms);
        toPhoneNumber=(EditText)findViewById(R.id.EditTextPhoneNo);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
        smsMessageEt=(EditText)findViewById(R.id.EditTextMessage);
      //  sendSmsBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_sm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.inbox) {
            Intent intent= new Intent(SendSMSActivity.this,ReceiveSMSActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.call) {
           call();

            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        sendsms();

    }

    private void sendsms()
    {
        String toPhone=toPhoneNumber.getText().toString();
        String smsMessage=smsMessageEt.getText().toString();
        try
        {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(toPhone,null,smsMessage,null,null);
            Toast.makeText(this, "SMS sent",Toast.LENGTH_LONG).show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void call()
    {
        String s=toPhoneNumber.getText().toString();
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + s));
        startActivity(intent);
    }


}
