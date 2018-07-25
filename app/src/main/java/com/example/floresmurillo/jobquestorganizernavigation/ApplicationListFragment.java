package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link ListFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ApplicationListFragment.OnApplicationListItemSelected} interface
 * to handle interaction events.
 * Use the {@link ApplicationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationListFragment extends ListFragment {

    protected SqlHelper db;
    protected List<Application> applications;
    protected ApplicationListAdapter adapter;

    private OnApplicationListItemSelected mListener;
    private ApplicationAddFragment mApplicationAddFragment;

    public ApplicationListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplicationListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplicationListFragment newInstance(String param1, String param2) {
        ApplicationListFragment fragment = new ApplicationListFragment();
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
        applications = db.getAllApplications();
        ApplicationListAdapter adapter = new ApplicationListAdapter(getActivity(),
                R.layout.fragment_application_list, applications);
        setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create AddApplication fragment and new transaction
                mApplicationAddFragment = new ApplicationAddFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                fragmentTransaction.replace(R.id.content_frame, mApplicationAddFragment);
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
        return inflater.inflate(R.layout.fragment_application_list, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnApplicationListItemSelected) {
            mListener = (OnApplicationListItemSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnApplicationListItemSelected");
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
    public interface OnApplicationListItemSelected {
        // TODO: Update argument type and name
        void onApplicationSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Application selectedApplication = (Application) getListAdapter().getItem(position);

        Log.d("-Application Selected-", selectedApplication.toString());

        int applicationId = selectedApplication.getId();
        mListener.onApplicationSelected(applicationId);

        // Set the selected item as checked
        getListView().setItemChecked(position, true);
    }
}
