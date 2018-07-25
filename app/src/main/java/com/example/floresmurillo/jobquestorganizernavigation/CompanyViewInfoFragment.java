package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompanyViewInfoFragment.OnCompanyViewInfoListener} interface
 * to handle interaction events.
 * Use the {@link CompanyViewInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyViewInfoFragment extends Fragment {

    protected static final String ARG_POSITION = "position";
    protected static int companyId = 0;

    int mCurrentPosition = -1;

    private OnCompanyViewInfoListener mListener;

    public CompanyViewInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompanyViewInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompanyViewInfoFragment newInstance(String param1, String param2) {
        CompanyViewInfoFragment fragment = new CompanyViewInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        companyId = getArguments().getInt(ARG_POSITION);
        Log.d("--I chose--", String.valueOf(companyId));
        // Restore
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_view_info, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        int position = getArguments().getInt(ARG_POSITION);
        if (args != null) {
            // Set company info based on argument passed in
            showCompanyInfo(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            showCompanyInfo(mCurrentPosition);
        }
    }

    public void showCompanyInfo(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Company company = db.getCompany(position);
        TextView tv1 = (TextView) getActivity().findViewById(R.id.company_read_companyName);
        tv1.setText(company.getCompanyName());
        TextView tv2 = (TextView) getActivity().findViewById(R.id.company_read_website);
        tv2.setText(company.getWebsite());
        TextView tv3 = (TextView) getActivity().findViewById(R.id.company_read_location);
        final String location;
        final String singleLineLocation;
        if (company.getAddress2() != null) {
            location = company.getAddress1() + "\n" + company.getAddress2() +
                    "\n" + company.getCity() + ", " + company.getState() + "\n" + company.getZip();
            singleLineLocation = company.getAddress1() + ", " + company.getCity()
                    + ", " + company.getState() + " " + company.getZip();
        } else {
            location = company.getAddress1() + "\n" + company.getCity() +
                    ", " + company.getState() + "\n" + company.getZip();
            singleLineLocation = company.getAddress1() + ", " + company.getCity()
                    + ", " + company.getState() + " " + company.getZip();
        }
        tv3.setText(location);

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("address", singleLineLocation);
                intent.putExtra("company", company.getCompanyName());
                startActivity(intent);*/
                String city = company.getCity().replace(" ", "+");
                String searchAddress = company.getAddress1() + ", " + city + ", " + company.getState();
                String uriString = "geo:0,0?q=" + searchAddress ;
                Uri gmmIntentUri = Uri.parse(uriString);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        /*TextView tv3 = (TextView) getActivity().findViewById(R.id.company_read_address1);
        tv3.setText(company.getAddress1());
        TextView tv4 = (TextView) getActivity().findViewById(R.id.company_read_address2);
        if (company.getAddress2() != null) {
            tv4.setText(company.getAddress2());
        } else {
            tv4.setVisibility(View.INVISIBLE);
        }
        TextView tv5 = (TextView) getActivity().findViewById(R.id.company_read_city);
        tv5.setText(company.getCity());
        TextView tv6 = (TextView) getActivity().findViewById(R.id.company_read_state);
        tv6.setText(company.getState());
        TextView tv7 = (TextView) getActivity().findViewById(R.id.company_read_zip);
        tv7.setText(company.getZip());*/
        TextView tv8 = (TextView) getActivity().findViewById(R.id.company_read_phone);
        tv8.setText(company.getPhone());

        mCurrentPosition = position;

        TextView delete = (TextView) getActivity().findViewById(R.id.company_delete);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Company Deleted", Snackbar.LENGTH_SHORT)
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                db.deleteCompany(company);
                                getActivity().getSupportFragmentManager().popBackStack();
                            }
                        });

                snackbar.show();
            }
        });

        Button edit = (Button) getActivity().findViewById(R.id.company_edit);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Company selectedCompany = db.getCompany(position);

                Log.d("---Company Selected---", selectedCompany.toString());

                int companyId = selectedCompany.getId();
                mListener.onViewCompanyInfo(companyId);
            }
        });
    }

    public void onSavedInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current contact view info in case the fragment has to be recreated
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCompanyViewInfoListener) {
            mListener = (OnCompanyViewInfoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCompanyViewInfoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnCompanyViewInfoListener {
        // TODO: Update argument type and name
        void onViewCompanyInfo(int position);
    }
}
