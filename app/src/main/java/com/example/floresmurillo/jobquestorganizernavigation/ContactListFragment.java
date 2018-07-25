package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link ListFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactListFragment.OnContactListItemSelected} interface
 * to handle interaction events.
 * Use the {@link ContactListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactListFragment extends ListFragment {

    protected SqlHelper db;
    protected List<Contact> contacts;
    protected ContactListAdapter adapter;

    private OnContactListItemSelected mListener;
    private ContactAddFragment mContactAddFragment;

    public ContactListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactListFragment newInstance(String param1, String param2) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        db = new SqlHelper(getActivity());
        contacts = db.getAllContacts();
        adapter = new ContactListAdapter(getActivity(),
                R.layout.fragment_contact_list, contacts);
        setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create AddContact fragment and new transaction
                mContactAddFragment = new ContactAddFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                fragmentTransaction.replace(R.id.content_frame, mContactAddFragment);
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContactListItemSelected) {
            mListener = (OnContactListItemSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnContactListItemSelected");
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
    public interface OnContactListItemSelected {
        // TODO: Update argument type and name
        void onContactSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Contact selectedContact = (Contact) getListAdapter().getItem(position);

        Log.d("---Contact Selected---", selectedContact.toString());

        int contactId = selectedContact.getId();
        mListener.onContactSelected(contactId);

        // Set the selected item as checked
        getListView().setItemChecked(position, true);
    }
}
