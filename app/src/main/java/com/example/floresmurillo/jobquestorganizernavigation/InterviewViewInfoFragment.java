package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
 * {@link InterviewViewInfoFragment.OnInterviewViewInfo} interface
 * to handle interaction events.
 * Use the {@link InterviewViewInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterviewViewInfoFragment extends Fragment {

    protected static final String ARG_POSITION = "position";
    protected static int interviewId = 0;

    int mCurrentPosition = -1;

    private OnInterviewViewInfo mListener;

    public InterviewViewInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterviewViewInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterviewViewInfoFragment newInstance(String param1, String param2) {
        InterviewViewInfoFragment fragment = new InterviewViewInfoFragment();
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
        return inflater.inflate(R.layout.fragment_interview_view_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            showInterviewInfo(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            showInterviewInfo(mCurrentPosition);
        }
    }

    public void showInterviewInfo(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Interview interview = db.getInterview(position);
        TextView tv1 = (TextView) getActivity().findViewById(R.id.interview_read_companyName);
        tv1.setText(interview.getCompanyName());
        TextView tv2 = (TextView) getActivity().findViewById(R.id.interview_read_position);
        tv2.setText(interview.getPosition());
        TextView tv3 = (TextView) getActivity().findViewById(R.id.interview_read_date);
        tv3.setText(interview.getDate());
        TextView tv4 = (TextView) getActivity().findViewById(R.id.interview_read_time);
        tv4.setText(interview.getTime());
        TextView tv5 = (TextView) getActivity().findViewById(R.id.interview_read_interviewer);
        tv5.setText(interview.getInterviewer());

        TextView tv6 = (TextView) getActivity().findViewById(R.id.interview_read_location);
        final String location;
        if (interview.getAddress2() != null) {
            location = interview.getAddress1() + "\n" + interview.getAddress2() +
                    "\n" + interview.getCity() + ", " + interview.getState() + "\n" + interview.getZip();
        } else {
            location = interview.getAddress1() + "\n" + interview.getCity() +
                    ", " + interview.getState() + "\n" + interview.getZip();
        }
        tv6.setText(location);

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = interview.getCity().replace(" ", "+");
                String searchAddress = interview.getAddress1() + ", " + city + ", " + interview.getState();
                String uriString = "geo:0,0?q=" + searchAddress;
                Uri gmmIntentUri = Uri.parse(uriString);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        TextView tv11 = (TextView) getActivity().findViewById(R.id.interview_read_details);
        tv11.setText(interview.getDetails());

        mCurrentPosition = position;

        TextView delete = (TextView) getActivity().findViewById(R.id.interview_delete);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Interview Deleted", Snackbar.LENGTH_SHORT)
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                db.deleteInterview(interview);
                                getActivity().getSupportFragmentManager().popBackStack();
                            }
                        });

                snackbar.show();
            }
        });

        Button edit = (Button) getActivity().findViewById(R.id.interview_edit);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Interview selectedInterview = db.getInterview(position);

                Log.d("-Interview Selected-", selectedInterview.toString());

                int interviewId = selectedInterview.getId();
                mListener.onInterviewViewInfo(interviewId);
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
        if (context instanceof OnInterviewViewInfo) {
            mListener = (OnInterviewViewInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInterviewViewInfo");
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
    public interface OnInterviewViewInfo {
        // TODO: Update argument type and name
        void onInterviewViewInfo(int position);
    }
}
