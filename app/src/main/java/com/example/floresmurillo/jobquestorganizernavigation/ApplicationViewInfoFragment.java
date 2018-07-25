package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
 * {@link ApplicationViewInfoFragment.OnApplicationViewInfo} interface
 * to handle interaction events.
 * Use the {@link ApplicationViewInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationViewInfoFragment extends Fragment {

    protected static final String ARG_POSITION = "position";
    protected static int applicationId = 0;

    int mCurrentPosition = -1;

    private OnApplicationViewInfo mListener;

    public ApplicationViewInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplicationViewInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplicationViewInfoFragment newInstance(String param1, String param2) {
        ApplicationViewInfoFragment fragment = new ApplicationViewInfoFragment();
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
        applicationId = getArguments().getInt(ARG_POSITION);
        Log.d("--I chose--", String.valueOf(applicationId));
        // Restore
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_application_view_info, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            showApplicationInfo(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set application info based on saved instance state defined during onCreateView
            showApplicationInfo(mCurrentPosition);
        }
    }

    public void showApplicationInfo(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Application application = db.getApplication(position);
        TextView tv1 = (TextView) getActivity().findViewById(R.id.application_read_companyName);
        tv1.setText(application.getCompanyName());
        TextView tv2 = (TextView) getActivity().findViewById(R.id.application_read_jobTitle);
        tv2.setText(application.getJobTitle());
        TextView tv3 = (TextView) getActivity().findViewById(R.id.application_read_jobId);
        tv3.setText(application.getJobId());
        TextView tv4 = (TextView) getActivity().findViewById(R.id.application_read_dateApplied);
        tv4.setText(application.getDateApplied());
        TextView tv5 = (TextView) getActivity().findViewById(R.id.application_read_interview);
        tv5.setText(application.getInterview());
        TextView tv6 = (TextView) getActivity().findViewById(R.id.application_read_description);
        tv6.setText(application.getDescription());

        mCurrentPosition = position;

        TextView delete = (TextView) getActivity().findViewById(R.id.application_delete);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Application Deleted", Snackbar.LENGTH_SHORT)
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                db.deleteApplication(application);
                                getActivity().getSupportFragmentManager().popBackStack();
                            }
                        });

                snackbar.show();
            }
        });

        Button edit = (Button) getActivity().findViewById(R.id.application_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application selectedApplication = db.getApplication(position);

                Log.d("-Application selected-", selectedApplication.toString());

                int applicationId = selectedApplication.getId();
                mListener.onApplicationViewInfo(applicationId);
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
        if (context instanceof OnApplicationViewInfo) {
            mListener = (OnApplicationViewInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnApplicationViewInfo");
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
    public interface OnApplicationViewInfo {
        // TODO: Update argument type and name
        void onApplicationViewInfo(int position);
    }
}
