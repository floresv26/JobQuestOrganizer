package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vanessaflores on 4/25/16.
 */
public class ContactListAdapter extends ArrayAdapter<Contact> {

    private List<Contact> contacts;

    public ContactListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ContactListAdapter(Context context, int resource, List<Contact> contacts) {
        super(context, resource, contacts);

        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.contact_list_item_layout, null);
        }

        Contact c = getItem(position);

        if (c != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.contact_list_contactName);
            TextView tv2 = (TextView) v.findViewById(R.id.contact_list_companyName);

            if (tv1 != null) {
                tv1.setText("" + c.getFirstName() + " " + c.getLastName());
            }
            if (tv2 != null) {
                tv2.setText("" + c.getCompanyName());
            }
        }

        return v;
    }
}
