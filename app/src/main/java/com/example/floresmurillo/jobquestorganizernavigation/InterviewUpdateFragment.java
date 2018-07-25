package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InterviewUpdateFragment.OnInterviewUpdateInfo} interface
 * to handle interaction events.
 * Use the {@link InterviewUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterviewUpdateFragment extends Fragment {
    protected static final String ARG_POSITION = "position";

    int mCurrentPosition = -1;
    Context thisContext;
    String timeOfDay = "PM";

    private OnInterviewUpdateInfo mListener;

    public InterviewUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterviewUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterviewUpdateFragment newInstance(String param1, String param2) {
        InterviewUpdateFragment fragment = new InterviewUpdateFragment();
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
        return inflater.inflate(R.layout.fragment_interview_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            updateInterviewInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            updateInterviewInfoView(mCurrentPosition);
        }
    }

    public void updateInterviewInfoView(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Interview interview = db.getInterview(position);
        TextInputEditText tv1 = (TextInputEditText) getActivity().findViewById(R.id.interview_companyName);
        tv1.setText(interview.getCompanyName());
        TextInputEditText tv2 = (TextInputEditText) getActivity().findViewById(R.id.interview_position);
        tv2.setText(interview.getPosition());

        String interviewDate = interview.getDate();
        String[] dateSplit = interviewDate.split("/");
        TextInputEditText t11 = (TextInputEditText) getActivity().findViewById(R.id.interview_month);
        t11.setText(dateSplit[0]);
        TextInputEditText tv12 = (TextInputEditText) getActivity().findViewById(R.id.interview_day);
        tv12.setText(dateSplit[1]);
        TextInputEditText tv13  = (TextInputEditText) getActivity().findViewById(R.id.interview_year);
        tv13.setText(dateSplit[2]);

        String interviewTime = interview.getTime();
        String[] timeSplit = interviewTime.split(":| ");
        TextInputEditText tv3 = (TextInputEditText) getActivity().findViewById(R.id.interview_hour);
        tv3.setText(timeSplit[0]);
        TextInputEditText tv4 = (TextInputEditText) getActivity().findViewById(R.id.interview_minute);
        tv4.setText(timeSplit[1]);
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.time_spinner);
        if (timeSplit[2].equals("AM")) {
            spinner.setSelection(1);
        } else {
            spinner.setSelection(0);
        }

        TextView tv5 = (TextView) getActivity().findViewById(R.id.interview_interviewer);
        tv5.setText(interview.getInterviewer());
        TextView tv6 = (TextView) getActivity().findViewById(R.id.interview_address1);
        tv6.setText(interview.getAddress1());
        TextView tv7 = (TextView) getActivity().findViewById(R.id.interview_address2);
        tv7.setText(interview.getAddress2());
        TextView tv8 = (TextView) getActivity().findViewById(R.id.interview_city);
        tv8.setText(interview.getCity());
        TextView tv9 = (TextView) getActivity().findViewById(R.id.interview_state);
        tv9.setText(interview.getState());
        TextView tv10 = (TextView) getActivity().findViewById(R.id.interview_zip);
        tv10.setText(interview.getZip());
        TextView tv11 = (TextView) getActivity().findViewById(R.id.interview_details);
        tv11.setText(interview.getDetails());

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
                TextInputLayout companyNameCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_companyName_input);
                String companyNameString = companyNameCheck.getEditText().getText().toString();
                interview.setCompanyName(companyNameString);
                TextInputLayout positionCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_position_input);
                String positionString = positionCheck.getEditText().getText().toString();
                interview.setPosition(positionString);
                TextInputEditText interviewerCheck = (TextInputEditText) getActivity().findViewById(R.id.interview_interviewer);
                String interviewerString = interviewerCheck.getText().toString();
                interview.setInterviewer(interviewerString);

                TextInputLayout monthCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_month_input);
                String monthString = monthCheck.getEditText().getText().toString();
                TextInputLayout dayCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_day_input);
                String dayString = dayCheck.getEditText().getText().toString();
                TextInputLayout yearCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_year_input);
                String yearString = yearCheck.getEditText().getText().toString();
                String interviewDate = monthString + "/" + dayString + "/" + yearString;
                interview.setDate(interviewDate);

                TextInputLayout hourCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_hour_input);
                String hourString = hourCheck.getEditText().getText().toString();
                TextInputLayout minuteCheck = (TextInputLayout) getActivity().findViewById(R.id.interview_minute_input);
                String minuteString = minuteCheck.getEditText().getText().toString();

                // Spinner
                Spinner timeSpinner = (Spinner) getActivity().findViewById(R.id.time_spinner);
                timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            timeOfDay = "PM";
                        }
                        if (position == 1) {
                            timeOfDay = "AM";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(thisContext,
                        R.array.time_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                timeSpinner.setAdapter(adapter);

                String interviewTime = hourString + ":" + minuteString + " " + timeOfDay;
                interview.setTime(interviewTime);

                TextInputEditText address1Check = (TextInputEditText) getActivity().findViewById(R.id.interview_address1);
                String address1String = address1Check.getText().toString();
                interview.setAddress1(address1String);
                TextInputEditText address2Check = (TextInputEditText) getActivity().findViewById(R.id.interview_address2);
                String address2String = address2Check.getText().toString();
                interview.setAddress2(address2String);
                TextInputEditText cityCheck = (TextInputEditText) getActivity().findViewById(R.id.interview_city);
                String cityString = cityCheck.getText().toString();
                interview.setCity(cityString);
                TextInputEditText stateCheck = (TextInputEditText) getActivity().findViewById(R.id.interview_state);
                String stateString = stateCheck.getText().toString();
                interview.setState(stateString);
                TextInputEditText zipCheck = (TextInputEditText) getActivity().findViewById(R.id.interview_zip);
                String zipString = zipCheck.getText().toString();
                interview.setZip(zipString);

                TextInputEditText detailsCheck = (TextInputEditText) getActivity().findViewById(R.id.interview_details);
                String detailsString = detailsCheck.getText().toString();
                interview.setDetails(detailsString);

                boolean companyNamePass = false;
                boolean positionPass = false;
                boolean hourPass = false;
                boolean minutePass = false;

                if (TextUtils.isEmpty(companyNameString)) {
                    companyNameCheck.setError("Required");
                    companyNameCheck.setErrorEnabled(true);
                } else {
                    companyNameCheck.setErrorEnabled(false);
                    companyNamePass = true;
                }
                if (TextUtils.isEmpty(positionString)) {
                    positionCheck.setError("Required");
                    positionCheck.setErrorEnabled(true);
                } else {
                    positionCheck.setErrorEnabled(false);
                    positionPass = true;
                }
                if (TextUtils.isEmpty(hourString) || Integer.parseInt(hourString) < 1 || Integer.parseInt(hourString) > 12) {
                    hourCheck.setError("Invalid");
                    hourCheck.setErrorEnabled(true);
                } else {
                    hourCheck.setErrorEnabled(false);
                    hourPass = true;
                }
                if (TextUtils.isEmpty(minuteString) || Integer.parseInt(minuteString) < 0 || Integer.parseInt(minuteString) > 60) {
                    minuteCheck.setError("Invalid");
                    minuteCheck.setErrorEnabled(true);
                } else {
                    minuteCheck.setErrorEnabled(false);
                    minutePass = true;
                }

                if (companyNamePass == true && positionPass == true && hourPass == true && minutePass == true) {
                    Log.d("--New Interview--", interview.toString());
                    db.addInterview(interview);
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
        if (context instanceof OnInterviewUpdateInfo) {
            mListener = (OnInterviewUpdateInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInterviewUpdateInfo");
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
    public interface OnInterviewUpdateInfo {
        // TODO: Update argument type and name
        void onInterviewUpdate(int position);
    }
}
