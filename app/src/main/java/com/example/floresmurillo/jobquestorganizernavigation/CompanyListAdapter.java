package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vanessaflores on 4/16/16.
 */
public class CompanyListAdapter extends ArrayAdapter<Company> {

    private List<Company> companies;

    public CompanyListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CompanyListAdapter(Context context, int resource, List<Company> companies) {
        super(context, resource, companies);

        this.companies = companies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.company_list_item_layout, null);
        }

        Company c = getItem(position);

        if (c != null) {
            ImageView iv1 = (ImageView) v.findViewById(R.id.company_logo);
            TextView tv1 = (TextView) v.findViewById(R.id.company_list_companyName);
            TextView tv2 = (TextView) v.findViewById(R.id.company_list_companyLocation);

            if (iv1 != null) {
                iv1.setImageResource(R.drawable.building);
            }
            if (tv1 != null) {
                tv1.setText("" + c.getCompanyName());
            }
            if (tv2 != null) {
                tv2.setText("" + c.getCity() + ", " + c.getState());
            }
        }

        return v;
    }
}
