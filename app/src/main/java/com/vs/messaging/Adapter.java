package com.vs.messaging;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyhoang on 11/30/2015.
 */
public class Adapter extends ArrayAdapter<Item> {
    private Activity activity;
    int id;
    ArrayList<Item> items;
    public Adapter(Activity context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        this.activity=context;
        this.id=resource;
        this.items=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=activity.getLayoutInflater();
            convertView=inflater.inflate(id,null);
        }
        Item item=items.get(position);
        TextView tv_id= (TextView) convertView.findViewById(R.id.textView10);
        TextView tv_id1= (TextView) convertView.findViewById(R.id.textView6);
        TextView tv_id2= (TextView) convertView.findViewById(R.id.textView4);



        tv_id.setText(item.getId());
        tv_id1.setText(item.getTitle());
        tv_id2.setText(item.getContent());
        return convertView;
    }
}
