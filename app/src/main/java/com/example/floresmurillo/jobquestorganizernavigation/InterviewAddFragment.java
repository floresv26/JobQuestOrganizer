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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InterviewAddFragment.OnAddInterview} interface
 * to handle interaction events.
 * Use the {@link InterviewAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterviewAddFragment extends Fragment {

    Context thisContext;
    private OnAddInterview mListener;
    String timeOfDay = "PM";

    public InterviewAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterviewAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterviewAddFragment newInstance(String param1, String param2) {
        InterviewAddFragment fragment = new InterviewAddFragment();
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
        thisContext = container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interview_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Interview interview = new Interview();
        final Spinner timeSpinner = (Spinner) getActivity().findViewById(R.id.time_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(thisContext,
                R.array.time_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timeSpinner.setAdapter(adapter);

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
                //Spinner timeSpinner = (Spinner) getActivity().findViewById(R.id.time_spinner);
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
                /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(thisContext,
                        R.array.time_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                timeSpinner.setAdapter(adapter);*/

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddInterview) {
            mListener = (OnAddInterview) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddInterview");
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
    public interface OnAddInterview {
        // TODO: Update argument type and name
        void onInterviewAdded(Uri uri);
    }
}
