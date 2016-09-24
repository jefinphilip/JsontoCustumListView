package com.example.jefin.jsontocustumlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jefin on 24/9/16.
 */
public class ContactAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ContactAdapter(Context context, int resource) {
        super(context, resource);

    }


    public void add( Contact object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        ContactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_data,parent,false);
            contactHolder= new ContactHolder();
            contactHolder.text_username=(TextView)row.findViewById(R.id.username);
            contactHolder.text_email=(TextView)row.findViewById(R.id.email);
            row.setTag(contactHolder);
        }
        else
        {

            contactHolder=(ContactHolder)row.getTag();


        }

         Contact contact=(Contact)this.getItem(position);
         contactHolder.text_username.setText(contact.getUsername());
         contactHolder.text_email.setText(contact.getEmail());
        return row;
    }
    static class ContactHolder
    {
        TextView text_username,text_email;

    }
}
