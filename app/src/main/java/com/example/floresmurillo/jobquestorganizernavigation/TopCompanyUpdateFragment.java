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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopCompanyUpdateFragment.OnTopCompanyUpdateInfo} interface
 * to handle interaction events.
 * Use the {@link TopCompanyUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopCompanyUpdateFragment extends Fragment {

    protected static final String ARG_POSITION = "position";

    int mCurrentPosition = -1;

    private OnTopCompanyUpdateInfo mListener;

    public TopCompanyUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopCompanyUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopCompanyUpdateFragment newInstance(String param1, String param2) {
        TopCompanyUpdateFragment fragment = new TopCompanyUpdateFragment();
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
        return inflater.inflate(R.layout.fragment_top_company_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if there are arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            updateTopCompanyInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set contact info based on saved instance state defined during onCreateView
            updateTopCompanyInfoView(mCurrentPosition);
        }
    }

    public void updateTopCompanyInfoView(final int position) {
        final SqlHelper db = new SqlHelper(getActivity());
        final TopCompany topCompany = db.getTopCompany(position);
        TextInputEditText tv1 = (TextInputEditText) getActivity().findViewById(R.id.topCompany_company);
        tv1.setText(topCompany.getCompanyName());
        CheckBox checkBox = (CheckBox) getActivity().findViewById(R.id.topCompany_contact);
        if (topCompany.getAlumni() == 1) {
            checkBox.setChecked(true);
        } else if (topCompany.getAlumni() == 0) {
            checkBox.setChecked(false);
        }
        CheckBox checkBox2 = (CheckBox) getActivity().findViewById(R.id.topCompany_position);
        if (topCompany.getPosition() == 1) {
            checkBox2.setChecked(true);
        } else if (topCompany.getPosition() == 0) {
            checkBox2.setChecked(false);
        }

        Spinner spinner = (Spinner) getActivity().findViewById(R.id.topCompany_motivation);
        if (topCompany.getMotivation() == 0) {
            spinner.setSelection(0);
        } else if (topCompany.getMotivation() == 1) {
            spinner.setSelection(1);
        } else if (topCompany.getMotivation() == 2) {
            spinner.setSelection(2);
        } else if (topCompany.getMotivation() == 3) {
            spinner.setSelection(3);
        } else if (topCompany.getMotivation() == 4) {
            spinner.setSelection(4);
        } else if (topCompany.getMotivation() == 5) {
            spinner.setSelection(5);
        }

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
                TextInputLayout companyNameCheck = (TextInputLayout) getActivity().findViewById(R.id.topCompany_company_input);
                String companyNameString = companyNameCheck.getEditText().getText().toString();
                topCompany.setCompanyName(companyNameString);
                TextInputLayout lastNameCheck = (TextInputLayout) getActivity().findViewById(R.id.contact_last_name_input);
                CheckBox contactCheckbox = (CheckBox) getActivity().findViewById(R.id.topCompany_contact);
                if (contactCheckbox.isChecked()) {
                    topCompany.setAlumni(1);
                } else if (!contactCheckbox.isChecked()) {
                    topCompany.setAlumni(0);
                }
                CheckBox positionCheckbox = (CheckBox) getActivity().findViewById(R.id.topCompany_position);
                if (positionCheckbox.isChecked()) {
                    topCompany.setPosition(1);
                } else if (!positionCheckbox.isChecked()) {
                    topCompany.setPosition(0);
                }

                // Spinner
                Spinner spinner = (Spinner) getActivity().findViewById(R.id.topCompany_motivation);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            //Log.d("Spinner item selected", "" + position);
                            topCompany.setMotivation(0);
                        }
                        if (position == 1) {
                            //Log.d("Spinner item selected", "" + position);
                            topCompany.setMotivation(1);
                        }
                        if (position == 2) {
                            //Log.d("Spinner item selected", "" + position);
                            topCompany.setMotivation(2);
                        }
                        if (position == 3) {
                            //Log.d("Spinner item selected", "" + position);
                            topCompany.setMotivation(3);
                        }
                        if (position == 4) {
                            //Log.d("Spinner item selected", "" + position);
                            topCompany.setMotivation(4);
                        }
                        if (position == 5) {
                            //Log.d("Spinner item selected", "" + position);
                            topCompany.setMotivation(5);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.motivation_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);


                boolean companyNamePass = false;

                if (TextUtils.isEmpty(companyNameString)) {
                    companyNameCheck.setError("Required");
                    companyNameCheck.setErrorEnabled(true);
                } else {
                    companyNameCheck.setErrorEnabled(false);
                    companyNamePass = true;
                }

                if (companyNamePass == true) {
                    Log.d("--Update TopCompany--", topCompany.toString());
                    db.updateTopCompany(topCompany);
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
        if (context instanceof OnTopCompanyUpdateInfo) {
            mListener = (OnTopCompanyUpdateInfo) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTopCompanyUpdateInfo");
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
    public interface OnTopCompanyUpdateInfo {
        // TODO: Update argument type and name
        void onTopCompanyUpdate(int position);
    }
}
