package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ReceiveSMSActivity extends Activity implements AdapterView.OnItemClickListener {
    private static ReceiveSMSActivity inst;
    ArrayList<String> smsMessageList = new ArrayList<String>();
    ListView smsListView;

    ArrayAdapter arrayAdapter;


    public static ReceiveSMSActivity instance() {
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sms);
        smsListView = (ListView) findViewById(R.id.SMSlist);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessageList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);

        refreshsmsinbox();
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_HOME);


    }

    public void refreshsmsinbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxcursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);

        int indexBody = smsInboxcursor.getColumnIndex("body");
        int indexAddress = smsInboxcursor.getColumnIndex("address");



        if (indexBody < 0 || !smsInboxcursor.moveToFirst())
            return;
        arrayAdapter.clear();
        do {String date =  smsInboxcursor.getString(smsInboxcursor.getColumnIndex("date"));
            Long timestamp = Long.parseLong(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            Date finaldate = calendar.getTime();
            String smsDate = finaldate.toString();

            String str =  smsInboxcursor.getString(indexAddress) + "\n" + smsInboxcursor.getString(indexBody) + "\t" + "\nDate : " + smsDate+ "\n" ;

            arrayAdapter.add(str);
        }
        while (smsInboxcursor.moveToNext());


    }

    public void updateList(final String smsMessage)

    {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_receive_sm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dbfrominbox) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }

        if (id==R.id.feedback)
        {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "flt.vishal@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Mr. Vishal,"+"");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));

        }

        if (id==R.id.help)
        {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "flt.vishal@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Help");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Mr. Vishal," + "/n"+"");
            startActivity(Intent.createChooser(Email, "Help:"));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {


            String[] smsMessages = smsMessageList.get(position).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            String sender = address;


            for (int i = 1; i < smsMessages.length; i++) {
                smsMessage += smsMessages[i];


            }
            //  String smsMessageStr = address + "\n";
            String smsMessageStr = smsMessage;


            Intent intent = new Intent(this, MessageOption.class);


            String om = smsMessageStr;
            String se = sender;

            intent.putExtra("date", om);
            intent.putExtra("sender", se);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public void gotoCompose(View view) {
        Intent intent = new Intent(ReceiveSMSActivity.this, SendSMSActivity.class);
        startActivity(intent);
    }
}
