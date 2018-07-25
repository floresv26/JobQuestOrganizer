package com.example.floresmurillo.jobquestorganizernavigation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MenuFragment.OnMenuFragmentInteractionListener,
        CompanyListFragment.OnCompanyListItemSelected, CompanyViewInfoFragment.OnCompanyViewInfoListener,
        CompanyAddFragment.OnAddCompany, CompanyUpdateFragment.OnCompanyUpdateInfo,
        ContactListFragment.OnContactListItemSelected,ContactViewInfoFragment.OnContactViewInfo,
        ContactAddFragment.OnAddContact, ContactUpdateFragment.OnContactUpdateInfo,
        ApplicationListFragment.OnApplicationListItemSelected, ApplicationViewInfoFragment.OnApplicationViewInfo,
        ApplicationAddFragment.OnAddApplication, ApplicationUpdateFragment.OnApplicationUpdateInfo,
        InterviewListFragment.OnInterviewListItemSelected, InterviewViewInfoFragment.OnInterviewViewInfo,
        InterviewAddFragment.OnAddInterview, InterviewUpdateFragment.OnInterviewUpdateInfo,
        TopCompaniesFragment.OnTopCompaniesListItemSelected, TopCompanyAddFragment.OnAddTopCompany,
        TopCompanyUpdateFragment.OnTopCompanyUpdateInfo {

    FragmentManager fragmentManager = getSupportFragmentManager();
    private MenuFragment mMenuFragment;
    private TopCompaniesFragment mTopCompaniesFragment;
    private CompanyListFragment mCompanyListFragment;
    private ContactListFragment mContactListFragment;
    private ApplicationListFragment mApplicationListFragment;
    private InterviewListFragment mInterviewListFragment;

    private PendingIntent pendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqlHelper db = new SqlHelper(this);

        // add company
        /*db.addCompany(new Company("Google", "http://www.google.com/about/careers/",
                "1600 Amphitheatre Parkway", null, "Mountain View", "CA", "94043", "650-253-0000"));
        db.addCompany(new Company("Exxon Mobile", "http://corporate.exxonmobil.com/en/company/careers",
                "5959 Las Colinas Boulevard", null, "Irving", "TX", "75039-2298", "(972) 444-1000"));
        db.addCompany(new Company("Apple", "http://www.apple.com/jobs/us/",
                "1 Infinite Loop", null, "Cupertino", "CA", "95014", "(408) 996–1010"));
        db.addCompany(new Company("Twitter", "https://about.twitter.com/careers",
                "1355 Market Street", "Suite 900", "San Francisco", "CA", "94103", null));*/

        db.getAllCompanies();

        // add contact
        /*db.addContact(new Contact("Michael J.", "Fox", "mjfox@gmail.com", "630-222-3456", "www.linkedin.com/in/mjfox52", "Google"));
        db.addContact(new Contact("Jessica", "Biel", "jBiel@gmail.com", "630-234-7654", "www.linkedin.com/in/kbiel26", "Exxon Mobile"));
        db.addContact(new Contact("Matt", "Damon", "mdamon@gmail.com", "312-157-3789", null, "Apple"));
        db.addContact(new Contact("Jenna", "Dewan", "jdewan@gmail.com", "708-222-1023", null, "Twitter"));*/

        db.getAllContacts();

        // add applications
        /*db.addApplication(new Application("Google", "DF-123", "4/7/2016", "Software Engineer",
                "Yes", "Work with world-class experts in the field of Mapping and Navigation. " +
                "Advance the state of the art in getting real robots to understand their environment."));
        db.addApplication((new Application("Twitter", "120-564", "3/29/2016", "Android Software Engineer", "Yes",
                "Contribute to all layers of the Android stack, from UI and animations to network, storage, " +
                        "and video. Contribute to tools, testing, and scripts that improve efficiency and code quality.")));*/

        db.getAllApplications();

        // add interview
        /*db.addInterview(new Interview("4/28/2016", "11:00 AM", "1600 Amphitheatre Parkway", null,
                "Mountain View", "CA", "94043", "Software Engineer", null, "Casual dress", "Google"));
        db.addInterview(new Interview("5/4/2016", "10:00 AM", "1355 Market Street", "Suite 900",
                "San Francisco", "CA", "94103", "Android Software Engineer", "Robert Smith", "Casual dress", "Twitter"));*/

        db.getAllInterviews();

        // add reminders
        /*db.addReminder(new Reminder("4/27/2016", "12:00 PM", "Google Interview", "Make sure to have stuff on github."));
        db.addReminder(new Reminder("4/28/2016", "6:00 AM", "WAKE UP!!!", "Google interview."));
        db.addReminder(new Reminder("5/1/2016", "8:00 AM", "Android Apps", "Make sure android app projects work."));*/

        db.getAllReminders();

        // add topCompany
        /*db.addTopCompany(new TopCompany("Google", 1, 5, 1));
        db.addTopCompany(new TopCompany("Twitter", 1, 5, 1));
        db.addTopCompany(new TopCompany("Facebook", 1, 5, 1));
        db.addTopCompany(new TopCompany("Elmhurst Memorial Hospital", 0, 2, 1));
        db.addTopCompany(new TopCompany("Apple", 1, 5, 1));
        db.addTopCompany(new TopCompany("Abbot", 0, 3, 1));*/

        db.getAllTopCompanies();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Get fragment manager and begin new transaction
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Check whether the activity using the right layout and add the first fragment
        if (findViewById(R.id.content_frame) != null) {

            // If being restored from a previous state don't do anything and return
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of the MenuFragment
            mMenuFragment = new MenuFragment();

            // Add the fragment to content_frame
            //fragmentTransaction.add(R.id.content_frame, mMenuFragment).commit();
            fragmentTransaction.add(R.id.content_frame, mMenuFragment).commit();
        }

    }

    /** ---------- MenuFragment Interface ---------- **/
    @Override
    public void onMenuFragmentInteraction(Uri uri) {

    }


    /** ---------- Company Fragments Interfaces ---------- **/
    /**
     * Implement interface onCompanySelected
     * @param position
     */
    @Override
    public void onCompanySelected(int position) {
        // The user selected a company from the CompanyListFragment
        // Create CompanyViewInfoFragment and give it an argument for the selected company
        CompanyViewInfoFragment newFragment = new CompanyViewInfoFragment();
        Bundle args = new Bundle();
        args.putInt(CompanyViewInfoFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Log.d("--Company Selected--", String.valueOf(position));
    }

    /**
     * Implement interface onViewCompanyInfo
     * @param position
     */
    @Override
    public void onViewCompanyInfo(int position) {
        // The user selected a company from the CompanyListFragment
        // Create CompanyUpdate fragment and give it an argument for the selected contact
        CompanyUpdateFragment newFragment = new CompanyUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(CompanyUpdateFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Log.d("--Updating Company--", String.valueOf(position));
    }

    /**
     * Implement interface onCompanyAdded
     * @param uri
     */
    @Override
    public  void onCompanyAdded(Uri uri) {

    }

    @Override
    public void onCompanyUpdate(int position) {

    }

    /** ---------- Contact Fragments Interfaces ---------- **/
    /**
     * Implement interface onContactSelected
     * @param position
     */
    @Override
    public void onContactSelected(int position) {
        // The user selected a contact from the ContactListFragment
        // Create ContactViewInfoFragment and give it an argument for the selected contact
        ContactViewInfoFragment newFragment = new ContactViewInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ContactViewInfoFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Log.d("--Contact Selected--", String.valueOf(position));
    }

    @Override
    public void onContactViewInfo(int position) {
        // The user selected a contact from the ContactListFragment
        // Create ContactUpdate fragment and give it an argument for the selected contact
        ContactUpdateFragment newFragment = new ContactUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(ContactUpdateFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Log.d("--Updating Contact--", String.valueOf(position));

    }

    @Override
    public void onContactAdded(Uri uri) {

    }

    @Override
    public void onContactUpdate(int position) {

    }

    /** ---------- Application Fragments Interfaces ---------- **/

    @Override
    public void onApplicationSelected(int position) {
        // The user selected an application from the ApplicationListFragment
        // Create ApplicationViewInfoFragment and give it an argument for the selected contact
        ApplicationViewInfoFragment newFragment = new ApplicationViewInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ApplicationViewInfoFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onApplicationViewInfo(int position) {
        // The user selected an application from the ApplicationListFragment
        // Create ApplicationUpdate fragment and give it an argument for the selected application
        ApplicationUpdateFragment newFragment = new ApplicationUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(ApplicationUpdateFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Log.d("-Updating Application-", String.valueOf(position));

    }

    @Override
    public void onApplicationAdded(Uri uri) {

    }

    @Override
    public void onApplicationUpdate(int position) {

    }

    /** ---------- Interview Fragments Interfaces ---------- **/
    @Override
    public void onInterviewSelected(int position) {
        // The user selected an application from the ApplicationListFragment
        // Create ApplicationViewInfoFragment and give it an argument for the selected contact
        InterviewViewInfoFragment newFragment = new InterviewViewInfoFragment();
        Bundle args = new Bundle();
        args.putInt(InterviewViewInfoFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onInterviewViewInfo(int position) {
        // The user selected an interview from the InterviewListFragment
        // Create InterviewUpdate fragment and give it an argument for the selected interview
        InterviewUpdateFragment newFragment = new InterviewUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(InterviewUpdateFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Log.d("-Updating Interview-", String.valueOf(position));

    }

    @Override
    public void onInterviewAdded(Uri uri) {

    }

    @Override
    public void onInterviewUpdate(int position) {

    }

    /** ---------- TopCompany Fragments Interfaces ---------- **/

    @Override
    public void onTopCompanySelected(int position) {
        // The user selected an application from the ApplicationListFragment
        // Create TopCompanyUpdateFragment and give it an argument for the selected topCompany
        TopCompanyUpdateFragment newFragment = new TopCompanyUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(TopCompanyUpdateFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onTopCompanyAdded(Uri uri) {

    }

    @Override
    public void onTopCompanyUpdate(int position) {
// The user selected an application from the ApplicationListFragment
        // Create TopCompanyUpdateFragment and give it an argument for the selected topCompany
        TopCompanyUpdateFragment newFragment = new TopCompanyUpdateFragment();
        Bundle args = new Bundle();
        args.putInt(TopCompanyUpdateFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace what the content_frame view is displaying with the newFragment and add the
        // transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /** ---------- Notification ---------- **/
    private void notificationMethod() {
        Intent myIntent = new Intent(this, NotifyService.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
            // Create Menu fragment and new transaction
            mMenuFragment = new MenuFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.content_frame, mMenuFragment);

            // Commit the transaction
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Job Quest Organizer");

        } else if (id == R.id.nav_top_list) {

            // Create TopCompanies fragment and new transaction
            mTopCompaniesFragment = new TopCompaniesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.content_frame, mTopCompaniesFragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Top Companies");

        } else if (id == R.id.nav_companies) {

            // Create CompanyList fragment and new transaction
            mCompanyListFragment = new CompanyListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.content_frame, mCompanyListFragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Companies");


        } else if (id == R.id.nav_contacts) {

            // Create ContactList fragment and new transaction
            mContactListFragment = new ContactListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.content_frame, mContactListFragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Contacts");


        } else if (id == R.id.nav_applications) {

            // Create ApplicationList fragment and new transaction
            mApplicationListFragment = new ApplicationListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.content_frame, mApplicationListFragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Applications");

        } else if (id == R.id.nav_interviews) {

            // Create InterviewList fragment and new transaction
            mInterviewListFragment = new InterviewListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.content_frame, mInterviewListFragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Interviews");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
