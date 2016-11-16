package com.vs.messaging;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class SMSBroadcastReceiver extends BroadcastReceiver {

public static final String SMS_BUNDLE = "pdus";



    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle intentExtra=intent.getExtras();
        if (intentExtra != null)
        {
            Object[] sms=(Object[]) intentExtra.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for(int i =0; i < sms.length; i++)
            {
                SmsMessage smsMessage = SmsMessage.createFromPdu( (byte[]) sms[i]);
                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();
                long time = smsMessage.getTimestampMillis();

                Date date = new Date(time);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                String dataText=format.format(date);
                smsMessageStr += address + " at " + "\t" + dataText + "\n";
                smsMessageStr += smsBody + "\n";



            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();
            ReceiveSMSActivity inst = ReceiveSMSActivity.instance();
            if(inst != null)
            {
                inst.updateList(smsMessageStr);

            }
        }
    }
}
