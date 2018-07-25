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
public class InterviewListAdapter extends ArrayAdapter<Interview> {

    private List<Interview> interviews;

    public InterviewListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public InterviewListAdapter(Context context, int resource, List<Interview> interviews) {
        super(context, resource, interviews);

        this.interviews = interviews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.interview_list_item_layout, null);
        }

        Interview i = getItem(position);

        if (i != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.interview_list_companyName);
            TextView tv2 = (TextView) v.findViewById(R.id.interview_list_position);

            if (tv1 != null) {
                tv1.setText("" + i.getCompanyName());
            }
            if (tv2 != null) {
                tv2.setText("" + i.getPosition());
            }
        }

        return v;
    }
}
