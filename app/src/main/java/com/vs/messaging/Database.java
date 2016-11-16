package com.vs.messaging;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;


public class Database extends Activity {


    ListView listView;
    private Datag dbHelper;
    Adapter adapter;
    ArrayList<Item> arrayList=new ArrayList<Item>();
   // ArrayList<Item> arrayList1=new ArrayList<Item>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE );
        dbHelper=new Datag(this);
            dbHelper.fet();

        Cursor cursor=dbHelper.fet();
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
        listView= (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.editdatabase) {
            Intent intent=new Intent(this,EditDataBase.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}

