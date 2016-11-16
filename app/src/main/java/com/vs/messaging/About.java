package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE );
    }


    public void feedback(View view)
    {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "flt.vishal@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        Email.putExtra(Intent.EXTRA_TEXT, "Dear Mr. Vishal," + "/n" + "");
        startActivity(Intent.createChooser(Email, "Send Feedback:"));

    }

    public void help(View view)
    {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "flt.vishal@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "Help");
        Email.putExtra(Intent.EXTRA_TEXT, "Dear Mr. Vishal," + "/n"+"");
        startActivity(Intent.createChooser(Email, "Help:"));
    }

}
