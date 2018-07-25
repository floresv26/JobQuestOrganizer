package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
 * {@link TopCompanyAddFragment.OnAddTopCompany} interface
 * to handle interaction events.
 * Use the {@link TopCompanyAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopCompanyAddFragment extends Fragment {


    private OnAddTopCompany mListener;

    public TopCompanyAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopCompanyAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopCompanyAddFragment newInstance(String param1, String param2) {
        TopCompanyAddFragment fragment = new TopCompanyAddFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_company_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final SqlHelper db = new SqlHelper(getActivity());
        final TopCompany topCompany = new TopCompany();
        final Spinner spinner = (Spinner) getActivity().findViewById(R.id.topCompany_motivation);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.motivation_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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
                TextInputLayout companyNameCheck = (TextInputLayout) getActivity().findViewById(R.id.topCompany_company_input);
                String companyNameString = companyNameCheck.getEditText().getText().toString();
                topCompany.setCompanyName(companyNameString);
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
                //Spinner spinner = (Spinner) getActivity().findViewById(R.id.topCompany_motivation);
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
                /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.motivation_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);*/


                boolean companyNamePass = false;

                if (TextUtils.isEmpty(companyNameString)) {
                    companyNameCheck.setError("Required");
                    companyNameCheck.setErrorEnabled(true);
                } else {
                    companyNameCheck.setErrorEnabled(false);
                    companyNamePass = true;
                }

                if (companyNamePass == true) {
                    Log.d("--New TopCompany--", topCompany.toString());
                    db.addTopCompany(topCompany);
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddTopCompany) {
            mListener = (OnAddTopCompany) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddTopCompany");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddTopCompany {
        // TODO: Update argument type and name
        void onTopCompanyAdded(Uri uri);
    }
}
