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
 * {@link ContactUpdateFragment.OnContactUpdateInfo} interface
 * to handle interaction events.
 * Use the {@link ContactUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUpdateFragment extends Fragment {
    protected static final String ARG_POSITION = "position";

    int mCurrentPosition = -1;

    private OnContactUpdateInfo mListener;

    public ContactUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUpdateFragment newInstance(String param1, String param2) {
        ContactUpdateFragment fragment = new ContactUpdateFragment();
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
        return inflater.inflate(R.layout.fragment_contact_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            updateContactInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            updateContactInfoView(mCurrentPosition);
        }
    }

    public void updateContactInfoView(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Contact contact = db.getContact(position);
        TextInputEditText tv1 = (TextInputEditText) getActivity().findViewById(R.id.contact_first_name);
        tv1.setText(contact.getFirstName());
        TextInputEditText tv2 = (TextInputEditText) getActivity().findViewById(R.id.contact_last_name);
        tv2.setText(contact.getLastName());
        TextInputEditText tv3 = (TextInputEditText) getActivity().findViewById(R.id.contact_email);
        tv3.setText(contact.getEmail());
        TextInputEditText tv4 = (TextInputEditText) getActivity().findViewById(R.id.contact_phone);
        tv4.setText(contact.getPhone());
        TextInputEditText tv5 = (TextInputEditText) getActivity().findViewById(R.id.contact_company);
        tv5.setText(contact.getCompanyName());
        TextInputEditText tv6 = (TextInputEditText) getActivity().findViewById(R.id.contact_linkedIn);
        tv6.setText(contact.getLinkedIn());

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
                TextInputLayout firstNameCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_first_name_input);
                String firstNameString = firstNameCheck.getEditText().getText().toString();
                contact.setFirstName(firstNameString);
                TextInputLayout lastNameCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_last_name_input);
                String lastNameString = lastNameCheck.getEditText().getText().toString();
                contact.setLastName(lastNameString);
                TextInputLayout emailCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_email_input);
                String emailString = emailCheck.getEditText().getText().toString();
                contact.setEmail(emailString);
                TextInputLayout phoneCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_phone_input);
                String phoneString = phoneCheck.getEditText().getText().toString();
                contact.setPhone(phoneString);
                TextInputLayout companyCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_company_input);
                String companyString = companyCheck.getEditText().getText().toString();
                contact.setCompanyName(companyString);
                TextInputLayout linkedInCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_linkedIn_input);
                String linkedInString = linkedInCheck.getEditText().getText().toString();
                contact.setLinkedIn(linkedInString);

                boolean fNamePass = false;
                boolean lNamePass = false;
                boolean emailPass = false;
                boolean companyPass = false;

                if (TextUtils.isEmpty(firstNameString)) {
                    firstNameCheck.setError("Required");
                    firstNameCheck.setErrorEnabled(true);
                } else {
                    firstNameCheck.setErrorEnabled(false);
                    fNamePass = true;
                }
                if (TextUtils.isEmpty(lastNameString)) {
                    lastNameCheck.setError("Required");
                    lastNameCheck.setErrorEnabled(true);
                } else {
                    lastNameCheck.setErrorEnabled(false);
                    lNamePass = true;
                }
                if (TextUtils.isEmpty(emailString)) {
                    emailCheck.setError("Required");
                    emailCheck.setErrorEnabled(true);
                } else {
                    emailCheck.setErrorEnabled(false);
                    emailPass = true;
                }
                if (TextUtils.isEmpty(companyString)) {
                    companyCheck.setError("Required");
                    companyCheck.setErrorEnabled(true);
                } else {
                    companyCheck.setErrorEnabled(false);
                    companyPass = true;
                }

                if (fNamePass == true && lNamePass == true && emailPass == true && companyPass == true)  {
                    Log.d("--New Contact--", contact.toString());
                    db.updateContact(contact);
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
        if (context instanceof OnContactUpdateInfo) {
            mListener = (OnContactUpdateInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnContactUpdateInfo");
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
    public interface OnContactUpdateInfo {
        // TODO: Update argument type and name
        void onContactUpdate(int position);
    }
}
