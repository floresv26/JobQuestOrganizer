package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vanessaflores on 4/30/16.
 */
public class TopCompaniesListAdapter extends ArrayAdapter<TopCompany> {

    private List<TopCompany> topCompanies;

    public TopCompaniesListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TopCompaniesListAdapter(Context context, int resource, List<TopCompany> topCompanies) {
        super(context, resource, topCompanies);

        this.topCompanies = topCompanies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.top_company_info_layout, null);
        }

        TopCompany c = getItem(position);

        if (c != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.company_name);
            Spinner spinner = (Spinner) v.findViewById(R.id.motivationSpinner);
            CheckBox checkBox1 = (CheckBox) v.findViewById(R.id.contactCheckbox);
            CheckBox checkBox2 = (CheckBox) v.findViewById(R.id.positionCheckbox);

            if (tv1 != null) {
                tv1.setText(c.getCompanyName());
            }
            if (checkBox1 != null && c.getAlumni() == 1) {
                checkBox1.setChecked(true);
            } else if (checkBox1 != null && c.getAlumni() == 0) {
                checkBox1.setChecked(false);
            }
            if (checkBox2 != null && c.getPosition() == 1) {
                checkBox2.setChecked(true);
            } else if (checkBox2 != null && c.getPosition() == 0) {
                checkBox2.setChecked(false);
            }
            if (spinner != null && c.getMotivation() == 0) {
                spinner.setSelection(0);
            } else if (spinner != null && c.getMotivation() == 1) {
                spinner.setSelection(1);
            } else if (spinner != null && c.getMotivation() == 2) {
                spinner.setSelection(2);
            } else if (spinner != null && c.getMotivation() == 3) {
                spinner.setSelection(3);
            } else if (spinner != null && c.getMotivation() == 4) {
                spinner.setSelection(4);
            } else if (spinner != null && c.getMotivation() == 5) {
                spinner.setSelection(5);
            }
        }

        return v;
    }
}
