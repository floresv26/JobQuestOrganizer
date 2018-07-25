package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;


/**
 * A simple {@link ListFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompanyListFragment.OnCompanyListItemSelected} interface
 * to handle interaction events.
 * Use the {@link CompanyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyListFragment extends ListFragment {

    protected SqlHelper db;
    protected List<Company> companies;
    protected CompanyListAdapter adapter;

    private OnCompanyListItemSelected mListener;
    private CompanyAddFragment mCompanyAddFragment;

    public CompanyListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompanyListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompanyListFragment newInstance(String param1, String param2) {
        CompanyListFragment fragment = new CompanyListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_list, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        db = new SqlHelper(getActivity());
        companies = db.getAllCompanies();
        adapter = new CompanyListAdapter(getActivity(),
                R.layout.fragment_company_list, companies);
        setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create AddCompany fragment and new transaction
                mCompanyAddFragment = new CompanyAddFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                fragmentTransaction.replace(R.id.content_frame, mCompanyAddFragment);
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCompanyListItemSelected) {
            mListener = (OnCompanyListItemSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCompanyListItemSelected");
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
    public interface OnCompanyListItemSelected {
        // TODO: Update argument type and name
        void onCompanySelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Company selectedCompany = (Company) getListAdapter().getItem(position);

        Log.d("---Company Selected---", selectedCompany.toString());

        int companyId = selectedCompany.getId();
        mListener.onCompanySelected(companyId);

        // Set the selected item as checked
        getListView().setItemChecked(position, true);
    }
}
