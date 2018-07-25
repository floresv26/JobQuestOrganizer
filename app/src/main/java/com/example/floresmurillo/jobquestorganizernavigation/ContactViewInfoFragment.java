package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactViewInfoFragment.OnContactViewInfo} interface
 * to handle interaction events.
 * Use the {@link ContactViewInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactViewInfoFragment extends Fragment {

    protected static final String ARG_POSITION = "position";
    protected static int contactId = 0;

    int mCurrentPosition = -1;

    private OnContactViewInfo mListener;

    public ContactViewInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactViewInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactViewInfoFragment newInstance(String param1, String param2) {
        ContactViewInfoFragment fragment = new ContactViewInfoFragment();
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
        contactId = getArguments().getInt(ARG_POSITION);
        Log.d("--I chose--", String.valueOf(contactId));
        // Restore
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_view_info, container, false);
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
        TextView tv1 = (TextView) getActivity().findViewById(R.id.contact_read_firstName);
        tv1.setText(contact.getFirstName());
        TextView tv2 = (TextView) getActivity().findViewById(R.id.contact_read_lastName);
        tv2.setText(contact.getLastName());
        TextView tv3 = (TextView) getActivity().findViewById(R.id.contact_read_email);
        tv3.setText(contact.getEmail());
        TextView tv4 = (TextView) getActivity().findViewById(R.id.contact_read_phone);
        tv4.setText(contact.getPhone());
        TextView tv5 = (TextView) getActivity().findViewById(R.id.contact_read_companyName);
        tv5.setText(contact.getCompanyName());
        TextView tv6 = (TextView) getActivity().findViewById(R.id.contact_read_linkedIn);
        tv6.setText(contact.getLinkedIn());

        mCurrentPosition = position;

        TextView delete = (TextView) getActivity().findViewById(R.id.contact_delete);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Contact Deleted", Snackbar.LENGTH_SHORT)
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                db.deleteContact(contact);
                                getActivity().getSupportFragmentManager().popBackStack();
                            }
                        });

                snackbar.show();
            }
        });

        Button edit = (Button) getActivity().findViewById(R.id.contact_edit);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Contact selectedContact = db.getContact(position);

                Log.d("---Contact Selected---", selectedContact.toString());

                int contactId = selectedContact.getId();
                mListener.onContactViewInfo(contactId);
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
        if (context instanceof OnContactViewInfo) {
            mListener = (OnContactViewInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnContactViewInfo");
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
    public interface OnContactViewInfo {
        // TODO: Update argument type and name
        void onContactViewInfo(int position);
    }
}
