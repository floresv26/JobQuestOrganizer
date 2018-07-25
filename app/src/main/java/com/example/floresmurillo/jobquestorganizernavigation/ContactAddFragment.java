package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactAddFragment.OnAddContact} interface
 * to handle interaction events.
 * Use the {@link ContactAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactAddFragment extends Fragment {

    private OnAddContact mListener;

    public ContactAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactAddFragment newInstance(String param1, String param2) {
        ContactAddFragment fragment = new ContactAddFragment();
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
        return inflater.inflate(R.layout.fragment_contact_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Contact contact = new Contact();

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
                    db.addContact(contact);
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddContact) {
            mListener = (OnAddContact) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddContact");
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
    public interface OnAddContact {
        // TODO: Update argument type and name
        void onContactAdded(Uri uri);
    }
}
