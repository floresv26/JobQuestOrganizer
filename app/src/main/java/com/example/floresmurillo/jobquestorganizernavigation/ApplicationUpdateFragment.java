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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ApplicationUpdateFragment.OnApplicationUpdateInfo} interface
 * to handle interaction events.
 * Use the {@link ApplicationUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationUpdateFragment extends Fragment {
    protected static final String ARG_POSITION = "position";

    int mCurrentPosition = -1;

    private OnApplicationUpdateInfo mListener;

    public ApplicationUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplicationUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplicationUpdateFragment newInstance(String param1, String param2) {
        ApplicationUpdateFragment fragment = new ApplicationUpdateFragment();
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
        return inflater.inflate(R.layout.fragment_application_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            updateApplicationInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            updateApplicationInfoView(mCurrentPosition);
        }
    }

    public void updateApplicationInfoView(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final Application application = db.getApplication(position);
        TextInputEditText tv1 = (TextInputEditText) getActivity().findViewById(R.id.application_companyName);
        tv1.setText(application.getCompanyName());
        TextInputEditText tv2 = (TextInputEditText) getActivity().findViewById(R.id.application_jobId);
        tv2.setText(application.getJobId());
        TextInputEditText tv3 = (TextInputEditText) getActivity().findViewById(R.id.application_jobTitle);
        tv3.setText(application.getJobTitle());

        String dateApplied = application.getDateApplied();
        String[] dateSplit = dateApplied.split("/");
        TextInputEditText tv4 = (TextInputEditText) getActivity().findViewById(R.id.application_dateApplied_month);
        tv4.setText(dateSplit[0]);
        TextInputEditText tv5 = (TextInputEditText) getActivity().findViewById(R.id.application_dateApplied_day);
        tv5.setText(dateSplit[1]);
        TextInputEditText tv6 = (TextInputEditText) getActivity().findViewById(R.id.application_dateApplied_year);
        tv6.setText(dateSplit[2]);

        CheckBox checkBox = (CheckBox) getActivity().findViewById(R.id.application_interview_checkbox);
        if (application.getInterview().equals("Yes")) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        TextInputEditText tv7 = (TextInputEditText) getActivity().findViewById(R.id.application_jobDescription);
        tv7.setText(application.getDescription());

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
                TextInputLayout companyNameCheck = (TextInputLayout) getActivity().findViewById(R.id.application_companyName_input);
                String companyNameString = companyNameCheck.getEditText().getText().toString();
                application.setCompanyName(companyNameString);
                TextInputEditText jobIdCheck = (TextInputEditText) getActivity().findViewById(R.id.application_jobId);
                String jobIdString = jobIdCheck.getText().toString();
                application.setJobId(jobIdString);
                TextInputLayout jobTitleCheck = (TextInputLayout) getActivity().findViewById(R.id.application_jobTitle_input);
                String jobTitleString = jobTitleCheck.getEditText().getText().toString();
                application.setJobTitle(jobTitleString);
                TextInputLayout monthCheck = (TextInputLayout) getActivity().findViewById(R.id.application_dateApplied_month_input);
                String monthString = monthCheck.getEditText().getText().toString();
                TextInputLayout dayCheck = (TextInputLayout) getActivity().findViewById(R.id.application_dateApplied_day_input);
                String dayString = dayCheck.getEditText().getText().toString();
                TextInputLayout yearCheck = (TextInputLayout) getActivity().findViewById(R.id.application_dateApplied_year_input);
                String yearString = yearCheck.getEditText().getText().toString();
                String dateApplied = monthString + "/" + dayString + "/" + yearString;
                application.setDateApplied(dateApplied);

                CheckBox interview = (CheckBox) getActivity().findViewById(R.id.application_interview_checkbox);
                if (interview.isChecked()) {
                    application.setInterview("Yes");
                } else {
                    application.setInterview("No");
                }
                TextInputEditText jobDescriptionCheck = (TextInputEditText) getActivity().findViewById(R.id.application_jobDescription);
                String jobDescriptionString = jobDescriptionCheck.getText().toString();
                application.setDescription(jobDescriptionString);

                boolean companyNamePass = false;
                boolean jobTitlePass = false;
                boolean monthPass = true;
                boolean dayPass = true;
                boolean yearPass = true;

                if (TextUtils.isEmpty(companyNameString)) {
                    companyNameCheck.setError("Required");
                    companyNameCheck.setErrorEnabled(true);
                } else {
                    companyNameCheck.setErrorEnabled(false);
                    companyNamePass = true;
                }
                if (TextUtils.isEmpty(jobTitleString)) {
                    jobTitleCheck.setError("Required");
                    jobTitleCheck.setErrorEnabled(true);
                } else {
                    jobTitleCheck.setErrorEnabled(false);
                    jobTitlePass = true;
                }
                if (!TextUtils.isEmpty(monthString) && Integer.parseInt(monthString) < 1 || Integer.parseInt(monthString) > 12 ) {
                    monthCheck.setError("Invalid");
                    monthCheck.setErrorEnabled(true);
                    monthPass = false;
                } else {
                    monthCheck.setErrorEnabled(false);
                    monthPass = true;
                }
                if (!TextUtils.isEmpty(dayString) && Integer.parseInt(dayString) < 1 || Integer.parseInt(dayString) > 31 ) {
                    dayCheck.setError("Invalid");
                    dayCheck.setErrorEnabled(true);
                    dayPass = false;
                } else {
                    dayCheck.setErrorEnabled(false);
                    dayPass = true;
                }
                if (!TextUtils.isEmpty(yearString) && Integer.parseInt(yearString) < 2016 || Integer.parseInt(yearString) > 2030 ) {
                    yearCheck.setError("Invalid");
                    yearCheck.setErrorEnabled(true);
                    monthPass = false;
                } else {
                    yearCheck.setErrorEnabled(false);
                    yearPass = true;
                }

                if (companyNamePass == true && jobTitlePass == true && monthPass == true && dayPass == true && yearPass == true)  {
                    Log.d("--New Application--", application.toString());
                    db.updateApplication(application);
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
        if (context instanceof OnApplicationUpdateInfo) {
            mListener = (OnApplicationUpdateInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnApplicationUpdateInfo");
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
    public interface OnApplicationUpdateInfo {
        // TODO: Update argument type and name
        void onApplicationUpdate(int position);
    }
}
