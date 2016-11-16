package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EditDataBase extends Activity {

    ListView listView;
    private Datag dbHelper;
    Adapter adapter;
Cursor cursor;

    ArrayList<Item> arrayList=new ArrayList<Item>();


    Button b1,b2;
    EditText e1,e2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_base);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE );
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        b1=(Button)findViewById(R.id.button6);
        b2=(Button)findViewById(R.id.button3);
        e1=(EditText)findViewById(R.id.editText7);
        e2=(EditText)findViewById(R.id.editText6);


        retr();
delete();

        dbHelper=new Datag(this);
        dbHelper.fet();

        cursor=dbHelper.fet();
        if ( cursor !=null){
            if (cursor.moveToFirst()){
                do {

                    Item item=new Item();
                    item.setId(cursor.getString(0));

                    item.setTitle(cursor.getString(1));
                    item.setContent(cursor.getString(2));

                    arrayList.add(item);
                }while (cursor.moveToNext());


            }

            adapter=new Adapter(this,R.layout.list_items,arrayList);
            listView= (ListView) findViewById(R.id.editdblist);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

    public void delete()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows = dbHelper.del(e1.getText().toString());
                if (deletedrows > 0) {
                    Toast.makeText(EditDataBase.this, "data deleted", Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(EditDataBase.this, "data not deleted", Toast.LENGTH_LONG).show();
                }

            }

        });

    }


    public void retr() {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbHelper.fet();
                if (res.getColumnCount() == 0) {
                    Nmessage("sorry", "no data");
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if (e2.getText().toString().equals(res.getString(1))) {
                        buffer.append("Sender is " + res.getString(1) + "\n");
                        buffer.append("Message is :" + res.getString(2) + "\n" + "\n");
                    }


                }




                Nmessage("Message", buffer.toString());


            }
        });

    }
    public void Nmessage(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }




}
