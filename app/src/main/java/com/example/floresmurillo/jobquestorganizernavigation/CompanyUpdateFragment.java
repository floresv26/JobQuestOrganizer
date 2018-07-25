package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompanyUpdateFragment.OnCompanyUpdateInfo} interface
 * to handle interaction events.
 * Use the {@link CompanyUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyUpdateFragment extends Fragment {
    protected static final String ARG_POSITION = "position";

    int mCurrentPosition = -1;

    private OnCompanyUpdateInfo mListener;

    public CompanyUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompanyUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompanyUpdateFragment newInstance(String param1, String param2) {
        CompanyUpdateFragment fragment = new CompanyUpdateFragment();
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
        int position = getArguments().getInt(ARG_POSITION);
        Log.d("--I chose--", String.valueOf(position));
        // Restore
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            updateCompanyInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            updateCompanyInfoView(mCurrentPosition);
        }
    }

    public void updateCompanyInfoView(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Company company = db.getCompany(position);
        TextInputEditText tv1 = (TextInputEditText) getActivity().findViewById(R.id.company_companyName);
        tv1.setText(company.getCompanyName());
        TextInputEditText tv2 = (TextInputEditText) getActivity().findViewById(R.id.company_website);
        tv2.setText(company.getWebsite());
        TextInputEditText tv3 = (TextInputEditText) getActivity().findViewById(R.id.company_address1);
        tv3.setText(company.getAddress1());
        TextInputEditText tv4 = (TextInputEditText) getActivity().findViewById(R.id.company_address2);
        tv4.setText(company.getAddress2());
        TextInputEditText tv5 = (TextInputEditText) getActivity().findViewById(R.id.company_city);
        tv5.setText(company.getCity());
        TextInputEditText tv6 = (TextInputEditText) getActivity().findViewById(R.id.company_state);
        tv6.setText(company.getState());
        TextInputEditText tv7 = (TextInputEditText) getActivity().findViewById(R.id.company_zip);
        tv7.setText(company.getZip());
        TextInputEditText tv8 = (TextInputEditText) getActivity().findViewById(R.id.company_phone);
        tv8.setText(company.getPhone());

        mCurrentPosition = position;

        TextView cancel = (TextView) getActivity().findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        Button saveButton = (Button) getActivity().findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextInputLayout companyNameCheck = (TextInputLayout) getActivity().findViewById(R.id.company_companyName_input);
                String companyNameString = companyNameCheck.getEditText().getText().toString();
                company.setCompanyName(companyNameString);
                TextInputLayout websiteCheck = (TextInputLayout) getActivity().findViewById(R.id.company_website_input);
                String websiteString = websiteCheck.getEditText().getText().toString();
                company.setWebsite(websiteString);
                TextInputEditText address1Check = (TextInputEditText) getActivity().findViewById(R.id.company_address1);
                String address1String = address1Check.getText().toString();
                company.setAddress1(address1String);
                TextInputEditText address2Check = (TextInputEditText) getActivity().findViewById(R.id.company_address2);
                String address2String = address2Check.getText().toString();
                company.setAddress2(address2String);
                TextInputEditText cityCheck = (TextInputEditText) getActivity().findViewById(R.id.company_city);
                String cityString = cityCheck.getText().toString();
                company.setCity(cityString);
                TextInputEditText stateCheck = (TextInputEditText) getActivity().findViewById(R.id.company_state);
                String stateString = stateCheck.getText().toString();
                company.setState(stateString);
                TextInputEditText zipCheck = (TextInputEditText) getActivity().findViewById(R.id.company_zip);
                String zipString = zipCheck.getText().toString();
                company.setZip(zipString);
                TextInputLayout phoneCheck = (TextInputLayout) getActivity().findViewById(R.id.company_phone_input);
                String phoneString = phoneCheck.getEditText().getText().toString();
                company.setPhone(phoneString);

                boolean companyNamePass = false;

                if (TextUtils.isEmpty(companyNameString)) {
                    companyNameCheck.setError("Required");
                    companyNameCheck.setErrorEnabled(true);
                } else {
                    companyNameCheck.setErrorEnabled(false);
                    companyNamePass = true;
                }

                if (companyNamePass == true)  {
                    Log.d("--Update Company--", company.toString());
                    db.updateCompany(company);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
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
        if (context instanceof OnCompanyUpdateInfo) {
            mListener = (OnCompanyUpdateInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCompanyUpdateInfo");
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
    public interface OnCompanyUpdateInfo {
        void onCompanyUpdate(int position);
    }
}
