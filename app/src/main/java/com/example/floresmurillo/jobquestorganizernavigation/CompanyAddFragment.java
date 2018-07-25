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
 * {@link CompanyAddFragment.OnAddCompany} interface
 * to handle interaction events.
 * Use the {@link CompanyAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyAddFragment extends Fragment {

    private OnAddCompany mListener;

    public CompanyAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompanyAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompanyAddFragment newInstance(String param1, String param2) {
        CompanyAddFragment fragment = new CompanyAddFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Company company = new Company();

        TextView cancel = (TextView) view.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        Button saveButton = (Button) view.findViewById(R.id.save_button);
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
                    Log.d("--New Company--", company.toString());
                    db.addCompany(company);
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddCompany) {
            mListener = (OnAddCompany) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddCompany");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddCompany {
        // TODO: Update argument type and name
        void onCompanyAdded(Uri uri);
    }
}
