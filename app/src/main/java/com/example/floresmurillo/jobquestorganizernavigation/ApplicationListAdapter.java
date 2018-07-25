package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vanessaflores on 4/25/16.
 */
public class ApplicationListAdapter extends ArrayAdapter<Application> {

    private List<Application> applications;

    public ApplicationListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ApplicationListAdapter(Context context, int resource, List<Application> applications) {
        super(context, resource, applications);

        this.applications = applications;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.application_list_item_layout, null);
        }

        Application a = getItem(position);

        if (a != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.application_list_companyName);
            TextView tv2 = (TextView) v.findViewById(R.id.application_list_jobTitle);

            if (tv1 != null) {
                tv1.setText("" + a.getCompanyName());
            }
            if (tv2 != null) {
                tv2.setText("" + a.getJobTitle());
            }
        }

        return v;
    }
}
