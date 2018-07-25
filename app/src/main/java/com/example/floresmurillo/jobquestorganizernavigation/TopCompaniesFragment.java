package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.net.Uri;
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
 * {@link TopCompaniesFragment.OnTopCompaniesListItemSelected} interface
 * to handle interaction events.
 * Use the {@link TopCompaniesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopCompaniesFragment extends ListFragment {

    protected SqlHelper db;
    protected List<TopCompany> topCompanies;
    protected TopCompaniesListAdapter adapter;
    protected TopCompanyAddFragment mTopCompanyAddFragment;

    private OnTopCompaniesListItemSelected mListener;

    public TopCompaniesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopCompaniesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopCompaniesFragment newInstance(String param1, String param2) {
        TopCompaniesFragment fragment = new TopCompaniesFragment();
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
        topCompanies = db.getAllTopCompanies();
        adapter = new TopCompaniesListAdapter(getActivity(),
                R.layout.fragment_top_companies, topCompanies);
        setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create AddTopCompany fragment and new transaction
                mTopCompanyAddFragment = new TopCompanyAddFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                fragmentTransaction.replace(R.id.content_frame, mTopCompanyAddFragment);
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
        return inflater.inflate(R.layout.fragment_top_companies, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTopCompaniesListItemSelected) {
            mListener = (OnTopCompaniesListItemSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTopCompaniesListItemSelected");
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
    public interface OnTopCompaniesListItemSelected {
        // TODO: Update argument type and name
        void onTopCompanySelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        TopCompany selectedCompany = (TopCompany) getListAdapter().getItem(position);

        Log.d("-TopCompany Selected-", selectedCompany.toString());

        int companyId = selectedCompany.getId();
        mListener.onTopCompanySelected(companyId);

        // Set the selected item as checked
        getListView().setItemChecked(position, true);
    }
}
