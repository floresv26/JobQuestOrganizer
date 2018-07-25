package com.example.floresmurillo.jobquestorganizernavigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vanessaflores on 4/16/16.
 */
public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;
    //Database Name
    private static final String DATABASE_NAME = "JqlDB";


    /** ---------- Company Table ---------- **/
    // Company table name
    private static final String TABLE_COMPANY = "company";

    // Company Table Columns names
    private static final String COMPANY_KEY_ID = "id";
    private static final String COMPANY_KEY_COMPANY_NAME = "companyName";
    private static final String COMPANY_KEY_WEBSITE = "website";
    private static final String COMPANY_KEY_ADDRESS1 = "address1";
    private static final String COMPANY_KEY_ADDRESS2 = "address2";
    private static final String COMPANY_KEY_CITY = "city";
    private static final String COMPANY_KEY_STATE = "state";
    private static final String COMPANY_KEY_ZIP = "zip";
    private static final String COMPANY_KEY_PHONE = "phone";

    /** ---------- Contacts Table ---------- **/
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String CONTACTS_KEY_ID = "id";
    private static final String CONTACTS_KEY_FIRST_NAME = "firstName";
    private static final String CONTACTS_KEY_LAST_NAME = "lastName";
    private static final String CONTACTS_KEY_EMAIL = "email";
    private static final String CONTACTS_KEY_PHONE = "phone";
    private static final String CONTACTS_KEY_LINKEDIN = "linkedIn";
    private static final String CONTACTS_KEY_COMPANY_NAME = "companyName";

    /** ---------- Interview Table ---------- **/
    // Interview table name
    private static final String TABLE_INTERVIEW = "interview";

    // Interview Table Columns names
    private static final String INTERVIEW_KEY_ID = "id";
    private static final String INTERVIEW_KEY_DATE = "date";
    private static final String INTERVIEW_KEY_TIME = "time";
    private static final String INTERVIEW_KEY_ADDRESS1 = "address1";
    private static final String INTERVIEW_KEY_ADDRESS2 = "address2";
    private static final String INTERVIEW_KEY_CITY = "city";
    private static final String INTERVIEW_KEY_STATE = "state";
    private static final String INTERVIEW_KEY_ZIP = "zip";
    private static final String INTERVIEW_KEY_POSITION = "position";
    private static final String INTERVIEW_KEY_INTERVIEWER = "interviewer";
    private static final String INTERVIEW_KEY_DETAILS = "details";
    private static final String INTERVIEW_KEY_COMPANY_NAME = "companyName";

    /** ---------- Application Table ---------- **/
    // Application table name
    private static final String TABLE_APPLICATION = "application";

    // Application Table Columns names
    private static final String APPLICATION_KEY_ID = "id";
    private static final String APPLICATION_KEY_COMPANY_NAME = "companyName";
    private static final String APPLICATION_KEY_JOBID = "jobId";
    private static final String APPLICATION_KEY_DATE_APPLIED = "dateApplied";
    private static final String APPLICATION_KEY_JOB_TITLE = "jobTitle";
    private static final String APPLICATION_KEY_INTERVIEW = "interview";
    private static final String APPLICATION_KEY_DESCRIPTION = "description";

    /** ---------- TopCompany Table ---------- **/
    // TopCompany table name
    private static final String TABLE_TOP_COMPANY = "topCompany";

    // TopCompany Table Columns names
    private static final String TOPCOMPANY_KEY_ID = "id";
    private static final String TOPCOMPANY_KEY_COMPANY_NAME = "companyName";
    private static final String TOPCOMPANY_KEY_ALUMNI = "alumni";
    private static final String TOPCOMPANY_KEY_MOTIVATION = "motivation";
    private static final String TOPCOMPANY_KEY_POSITION = "position";

    /** ---------- Reminder Table ---------- **/
    // Reminder table name
    private static final String TABLE_REMINDER = "reminder";

    // Reminder Table Columns names
    private static final String REMINDER_KEY_ID = "id";
    private static final String REMINDER_KEY_DATE = "date";
    private static final String REMINDER_KEY_TIME = "time";
    private static final String REMINDER_KEY_TITLE = "title";
    private static final String REMINDER_KEY_NOTES = "notes";


    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Call method to create company table
        createCompanyTable(db);

        // Call method to create contacts table
        createContactsTable(db);

        // Call method to create application table
        createApplicationTable(db);

        // Call method to create interview table
        createInterviewTable(db);

        // Call method to create topCompany table
        createTopCompany(db);

        // Call method to create reminder table
        createReminderTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older company table if existed
        db.execSQL("DROP TABLE IF EXISTS company");
        // Create fresh company table
        this.createCompanyTable(db);

        // Drop older contacts table if existed
        db.execSQL("DROP TABLE IF EXISTS contacts");
        // Create fresh contacts table
        this.createContactsTable(db);

        // Drop older application table if existed
        db.execSQL("DROP TABLE IF EXISTS application");
        // Create fresh application table
        this.createApplicationTable(db);

        // Drop older interview table if existed
        db.execSQL("DROP TABLE IF EXISTS interview");
        // Create fresh interview table
        this.createInterviewTable(db);

        // Drop older topCompany table if existed
        db.execSQL("DROP TABLE IF EXISTS topCompany");
        // Create fresh topCompany table
        this.createTopCompany(db);

        // Drop older reminder table if existed
        db.execSQL("DROP TABLE IF EXISTS reminder");
        // Create fresh reminder table
        this.createReminderTable(db);
    }

    /** ---------- Create company table and create CRUD operations ---------- **/

    // Method to create company table
    public void createCompanyTable(SQLiteDatabase db) {
        // SQL statement to create company table
        String CREATE_COMPANY_TABLE = "CREATE TABLE company ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "companyName TEXT, " +
                "website TEXT, " +
                "address1 TEXT, " +
                "address2 TEXT, " +
                "city TEXT, " +
                "state TEXT, " +
                "zip TEXT, " +
                "phone TEXT )";

        // Create company table
        db.execSQL(CREATE_COMPANY_TABLE);
    }

    /* CRUD operations (create "add", read "get", update, delete) */
    public void addCompany(Company company) {
        Log.d("addCompany", company.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COMPANY_KEY_COMPANY_NAME, company.getCompanyName());  // get companyName
        values.put(COMPANY_KEY_WEBSITE, company.getWebsite());  // get website
        values.put(COMPANY_KEY_ADDRESS1, company.getAddress1());  // get address1
        values.put(COMPANY_KEY_ADDRESS2, company.getAddress2());  // get address2
        values.put(COMPANY_KEY_CITY, company.getCity());  // get city
        values.put(COMPANY_KEY_STATE, company.getState());  // get state
        values.put(COMPANY_KEY_ZIP, company.getZip());  // get zip
        values.put(COMPANY_KEY_PHONE, company.getPhone());  // get phone

        // 3. insert
        db.insert(TABLE_COMPANY, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. close dbase
        db.close();
    }

    // Get All Companies
    public List<Company> getAllCompanies() {
        List<Company> companies = new LinkedList<Company>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_COMPANY + " ORDER BY companyName ASC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build company and add it to list
        Company company = null;
        if (cursor.moveToFirst()) {
            do {
                company = new Company();
                company.setId(Integer.parseInt(cursor.getString(0)));
                company.setCompanyName(cursor.getString(1));
                company.setWebsite(cursor.getString(2));
                company.setAddress1(cursor.getString(3));
                company.setAddress2(cursor.getString(4));
                company.setCity(cursor.getString(5));
                company.setState(cursor.getString(6));
                company.setZip(cursor.getString(7));
                company.setPhone(cursor.getString(8));

                // Add company to companies
                companies.add(company);

            } while (cursor.moveToNext());
        }

        Log.d("getAllCompanies()", companies.toString());

        db.close();

        return companies; // return companies
    }

    // Get single company
    public Company getCompany(int id) {
        Company company = null;

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_COMPANY + " WHERE id = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                company = new Company();
                company.setId(Integer.parseInt(cursor.getString(0)));
                company.setCompanyName(cursor.getString(1));
                company.setWebsite(cursor.getString(2));
                company.setAddress1(cursor.getString(3));
                company.setAddress2(cursor.getString(4));
                company.setCity(cursor.getString(5));
                company.setState(cursor.getString(6));
                company.setZip(cursor.getString(7));
                company.setPhone(cursor.getString(8));
            }
        } catch (Exception e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        }

        Log.d("getCompany()", company.toString());

        cursor.close();
        db.close();

        return company; // return companies

    }


    // Updating single company
    public int updateCompany(Company company) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COMPANY_KEY_COMPANY_NAME, company.getCompanyName());  // get companyName
        values.put(COMPANY_KEY_WEBSITE, company.getWebsite());  // get website
        values.put(COMPANY_KEY_ADDRESS1, company.getAddress1());  // get address1
        values.put(COMPANY_KEY_ADDRESS2, company.getAddress2());  // get address2
        values.put(COMPANY_KEY_CITY, company.getCity());  // get city
        values.put(COMPANY_KEY_STATE, company.getState());  // get state
        values.put(COMPANY_KEY_ZIP, company.getZip());  // get zip
        values.put(COMPANY_KEY_PHONE, company.getPhone());  // get phone

        // 3. updating row
        int i  = db.update(TABLE_COMPANY, //table
                values, // column/value
                COMPANY_KEY_ID + " = ?", // selections
                new String[]{String.valueOf(company.getId())}); // selection args

        // 4. close dbase
        db.close();
        Log.d("UpdateCompany", company.toString());
        return i;
    }

    // Deleting single company
    public void deleteCompany(Company company) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_COMPANY,
                COMPANY_KEY_ID + " = ?",
                new String[] {String.valueOf(company.getId()) });

        // 3. close dbase
        db.close();

        Log.d("deleteCompany", company.toString());
    }


    /** ---------- Create contacts table and create CRUD operations ---------- **/

    // Method to create contacts table
    public void createContactsTable(SQLiteDatabase db) {
        // SQL statement to create contacts table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE contacts ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "email TEXT, " +
                "phone TEXT, " +
                "linkedIn TEXT, " +
                "companyName TEXT )";

        // Create contacts table
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    /* CRUD operations (create "add", read "get", update, delete) */
    public void addContact(Contact contact) {
        Log.d("addContact", contact.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CONTACTS_KEY_FIRST_NAME, contact.getFirstName());  // get firstName
        values.put(CONTACTS_KEY_LAST_NAME, contact.getLastName());  // get lastName
        values.put(CONTACTS_KEY_EMAIL, contact.getEmail());  // get email
        values.put(CONTACTS_KEY_PHONE, contact.getPhone());  // get phone
        values.put(CONTACTS_KEY_LINKEDIN, contact.getLinkedIn());  // get linkedIn
        values.put(COMPANY_KEY_COMPANY_NAME, contact.getCompanyName());  // get companyName

        // 3. insert
        db.insert(TABLE_CONTACTS, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. close dbase
        db.close();
    }

    // Get All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new LinkedList<Contact>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_CONTACTS + " ORDER BY lastName ASC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build contact and add it to list
        Contact contact = null;
        if (cursor. moveToFirst()) {
            do {
                contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setFirstName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setPhone(cursor.getString(4));
                contact.setLinkedIn(cursor.getString(5));
                contact.setCompanyName(cursor.getString(6));

                // Add contact to contacts
                contacts.add(contact);

            } while (cursor.moveToNext());
        }

        Log.d("getAllContacts()", contacts.toString());

        db.close();

        return contacts; // return contacts
    }

    // Get single contact
    public Contact getContact(int id) {
        Contact contact = null;

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE id = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setFirstName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setPhone(cursor.getString(4));
                contact.setLinkedIn(cursor.getString(5));
                contact.setCompanyName(cursor.getString(6));
            }
        } catch (Exception e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        }

        Log.d("getContact()", contact.toString());

        cursor.close();
        db.close();

        return contact; // return companies

    }

    // Updating single contact
    public int updateContact(Contact contact) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CONTACTS_KEY_FIRST_NAME, contact.getFirstName());  // get firstName
        values.put(CONTACTS_KEY_LAST_NAME, contact.getLastName());  // get lastName
        values.put(CONTACTS_KEY_EMAIL, contact.getEmail());  // get email
        values.put(CONTACTS_KEY_PHONE, contact.getPhone());  // get phone
        values.put(CONTACTS_KEY_LINKEDIN, contact.getLinkedIn());  // get linkedIn
        values.put(CONTACTS_KEY_COMPANY_NAME, contact.getCompanyName()); // get companyName

        // 3. updating row
        int i  = db.update(TABLE_CONTACTS, //table
                values, // column/value
                CONTACTS_KEY_ID + " = ?", // selections
                new String[] {String.valueOf(contact.getId()) } ); // selection args

        // 4. close dbase
        db.close();
        Log.d("Update Contact", contact.toString());
        return i;
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CONTACTS,
                CONTACTS_KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});

        // 3. close dbase
        db.close();

        Log.d("deleteContact", contact.toString());
    }

    /** ---------- Create interview table and create CRUD operations ---------- **/

    // Method to create interview table
    public void createInterviewTable(SQLiteDatabase db) {
        // SQL statement to create interview table
        String CREATE_INTERVIEW_TABLE = "CREATE TABLE interview ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "time TEXT, " +
                "address1 TEXT, " +
                "address2 TEXT, " +
                "city TEXT, " +
                "state TEXT, " +
                "zip TEXT, " +
                "position TEXT, " +
                "interviewer TEXT, " +
                "details TEXT, " +
                "companyName TEXT )";

        // Create company table
        db.execSQL(CREATE_INTERVIEW_TABLE);
    }

    /* CRUD operations (create "add", read "get", update, delete) */
    public void addInterview(Interview interview) {
        Log.d("addInterview", interview.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(INTERVIEW_KEY_DATE, interview.getDate());  // get date
        values.put(INTERVIEW_KEY_TIME, interview.getTime());  // get time
        values.put(INTERVIEW_KEY_ADDRESS1, interview.getAddress1());  // get address1
        values.put(INTERVIEW_KEY_ADDRESS2, interview.getAddress2());  // get address2
        values.put(INTERVIEW_KEY_CITY, interview.getCity());  // get city
        values.put(INTERVIEW_KEY_STATE, interview.getState());  // get state
        values.put(INTERVIEW_KEY_ZIP, interview.getZip());  // get zip
        values.put(INTERVIEW_KEY_POSITION, interview.getPosition());  // get position
        values.put(INTERVIEW_KEY_INTERVIEWER, interview.getInterviewer());  // get interviewer
        values.put(INTERVIEW_KEY_DETAILS, interview.getDetails());  // get details
        values.put(INTERVIEW_KEY_COMPANY_NAME, interview.getCompanyName());  // get companyName

        // 3. insert
        db.insert(TABLE_INTERVIEW, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. close dbase
        db.close();
    }

    // Get All Interviews
    public List<Interview> getAllInterviews() {
        List<Interview> interviews = new LinkedList<Interview>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_INTERVIEW + " ORDER BY date ASC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build interview and add it to list
        Interview interview = null;
        if (cursor.moveToFirst()) {
            do {
                interview = new Interview();
                interview.setId(Integer.parseInt(cursor.getString(0)));
                interview.setDate(cursor.getString(1));
                interview.setTime(cursor.getString(2));
                interview.setAddress1(cursor.getString(3));
                interview.setAddress2(cursor.getString(4));
                interview.setCity(cursor.getString(5));
                interview.setState(cursor.getString(6));
                interview.setZip(cursor.getString(7));
                interview.setPosition(cursor.getString(8));
                interview.setInterviewer(cursor.getString(9));
                interview.setDetails(cursor.getString(10));
                interview.setCompanyName(cursor.getString(11));

                // Add company to companies
                interviews.add(interview);

            } while (cursor.moveToNext());
        }

        Log.d("getAllInterviews()", interviews.toString());

        db.close();

        return interviews; // return interviews
    }

    // Get single interview
    public Interview getInterview(Integer id) {
        Interview interview = null;

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_INTERVIEW + " WHERE id = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                interview = new Interview();
                interview.setId(Integer.parseInt(cursor.getString(0)));
                interview.setDate(cursor.getString(1));
                interview.setTime(cursor.getString(2));
                interview.setAddress1(cursor.getString(3));
                interview.setAddress2(cursor.getString(4));
                interview.setCity(cursor.getString(5));
                interview.setState(cursor.getString(6));
                interview.setZip(cursor.getString(7));
                interview.setPosition(cursor.getString(8));
                interview.setInterviewer(cursor.getString(9));
                interview.setDetails(cursor.getString(10));
                interview.setCompanyName(cursor.getString(11));
            }
        } catch (Exception e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        }

        Log.d("getInterview()", interview.toString());

        cursor.close();
        db.close();

        return interview; // return companies

    }

    // Updating single interview
    public int updateInterview(Interview interview) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(INTERVIEW_KEY_DATE, interview.getDate());  // get date
        values.put(INTERVIEW_KEY_TIME, interview.getTime());  // get time
        values.put(INTERVIEW_KEY_ADDRESS1, interview.getAddress1());  // get address1
        values.put(INTERVIEW_KEY_ADDRESS2, interview.getAddress2());  // get address2
        values.put(INTERVIEW_KEY_CITY, interview.getCity());  // get city
        values.put(INTERVIEW_KEY_STATE, interview.getState());  // get state
        values.put(INTERVIEW_KEY_ZIP, interview.getZip());  // get zip
        values.put(INTERVIEW_KEY_POSITION, interview.getPosition());  // get position
        values.put(INTERVIEW_KEY_INTERVIEWER, interview.getInterviewer());  // get interviewer
        values.put(INTERVIEW_KEY_DETAILS, interview.getDetails());  // get details
        values.put(INTERVIEW_KEY_COMPANY_NAME, interview.getCompanyName());  // get companyName

        // 3. updating row
        int i  = db.update(TABLE_INTERVIEW, //table
                values, // column/value
                INTERVIEW_KEY_ID + " = ?", // selections
                new String[]{String.valueOf(interview.getId())}); // selection args

        // 4. close dbase
        db.close();
        Log.d("UpdateInterview", interview.toString());
        return i;
    }

    // Deleting single interview
    public void deleteInterview(Interview interview) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_INTERVIEW,
                INTERVIEW_KEY_ID + " = ?",
                new String[] {String.valueOf(interview.getId()) });

        // 3. close dbase
        db.close();

        Log.d("deleteInterview", interview.toString());
    }

    /** ---------- Create application table and create CRUD operations ---------- **/

    // Method to create application table
    public void createApplicationTable(SQLiteDatabase db) {
        // SQL statement to create application table
        String CREATE_APPLICATION_TABLE = "CREATE TABLE application ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "companyName TEXT, " +
                "jobId TEXT, " +
                "dateApplied TEXT, " +
                "jobTitle TEXT, " +
                "interview TEXT, " +
                "description TEXT )";

        // Create application table
        db.execSQL(CREATE_APPLICATION_TABLE);
    }

    /* CRUD operations (create "add", read "get", update, delete) */
    public void addApplication(Application application) {
        Log.d("addApplication", application.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(APPLICATION_KEY_COMPANY_NAME, application.getCompanyName());  // get companyName
        values.put(APPLICATION_KEY_JOBID, application.getJobId());  // get jobId
        values.put(APPLICATION_KEY_DATE_APPLIED, application.getDateApplied());  // get dateApplied
        values.put(APPLICATION_KEY_JOB_TITLE, application.getJobTitle());  // get jobTitle
        values.put(APPLICATION_KEY_INTERVIEW, application.getInterview());  // get interview
        values.put(APPLICATION_KEY_DESCRIPTION, application.getDescription());  // get description

        // 3. insert
        db.insert(TABLE_APPLICATION, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. close dbase
        db.close();
    }

    // Get All Applications
    public List<Application> getAllApplications() {
        List<Application> applications = new LinkedList<Application>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_APPLICATION + " ORDER BY companyName ASC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build application and add it to list
        Application application = null;
        if (cursor.moveToFirst()) {
            do {
                application = new Application();
                application.setId(Integer.parseInt(cursor.getString(0)));
                application.setCompanyName(cursor.getString(1));
                application.setJobId(cursor.getString(2));
                application.setDateApplied(cursor.getString(3));
                application.setJobTitle(cursor.getString(4));
                application.setInterview(cursor.getString(5));
                application.setDescription(cursor.getString(6));

                // Add company to applications
                applications.add(application);

            } while (cursor.moveToNext());
        }

        Log.d("getAllApplications()", applications.toString());

        db.close();

        return applications; // return companies
    }

    // Get single application
    public Application getApplication(Integer id) {
        Application application = null;

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_APPLICATION + " WHERE id = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                application = new Application();
                application.setId(Integer.parseInt(cursor.getString(0)));
                application.setCompanyName(cursor.getString(1));
                application.setJobId(cursor.getString(2));
                application.setDateApplied(cursor.getString(3));
                application.setJobTitle(cursor.getString(4));
                application.setInterview(cursor.getString(5));
                application.setDescription(cursor.getString(6));
            }
        } catch (Exception e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        }

        Log.d("getApplication()", application.toString());

        cursor.close();
        db.close();

        return application; // return applications

    }

    // Updating single application
    public int updateApplication(Application application) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(APPLICATION_KEY_COMPANY_NAME, application.getCompanyName());  // get companyName
        values.put(APPLICATION_KEY_JOBID, application.getJobId());  // get jobId
        values.put(APPLICATION_KEY_DATE_APPLIED, application.getDateApplied());  // get dateApplied
        values.put(APPLICATION_KEY_JOB_TITLE, application.getJobTitle());  // get jobTitle
        values.put(APPLICATION_KEY_INTERVIEW, application.getInterview());  // get interview
        values.put(APPLICATION_KEY_DESCRIPTION, application.getDescription());  // get description

        // 3. updating row
        int i  = db.update(TABLE_APPLICATION, //table
                values, // column/value
                APPLICATION_KEY_ID + " = ?", // selections
                new String[]{String.valueOf(application.getId())}); // selection args

        // 4. close dbase
        db.close();
        Log.d("UpdateApplication", application.toString());
        return i;
    }

    // Deleting single application
    public void deleteApplication(Application application) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_APPLICATION,
                APPLICATION_KEY_ID + " = ?",
                new String[] {String.valueOf(application.getId()) });

        // 3. close dbase
        db.close();

        Log.d("deleteApplication", application.toString());
    }

    /** ---------- Create TopCompany table and create CRUD operations ---------- **/

    // Method to create top company table
    public void createTopCompany(SQLiteDatabase db) {
        // SQL statement to create top company table
        String CREATE_TOP_COMPANY_TABLE = "CREATE TABLE topCompany ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "companyName TEXT, " +
                "alumni INTEGER, " +
                "motivation INTEGER, " +
                "position INTEGER )";

        // Create company table
        db.execSQL(CREATE_TOP_COMPANY_TABLE);
    }

    /* CRUD operations (create "add", read "get", update, delete) */
    public void addTopCompany(TopCompany topCompany) {
        Log.d("addTopCompany", topCompany.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(TOPCOMPANY_KEY_COMPANY_NAME, topCompany.getCompanyName());  // get companyName
        values.put(TOPCOMPANY_KEY_ALUMNI, topCompany.getAlumni());  // get alumni
        values.put(TOPCOMPANY_KEY_MOTIVATION, topCompany.getMotivation());  // get motivation
        values.put(TOPCOMPANY_KEY_POSITION, topCompany.getPosition());  // get position

        // 3. insert
        db.insert(TABLE_TOP_COMPANY, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. close dbase
        db.close();
    }

    // Get All TopCompanies
    public List<TopCompany> getAllTopCompanies() {
        List<TopCompany> topCompanies = new LinkedList<TopCompany>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_TOP_COMPANY + " ORDER BY motivation, alumni, position DESC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build top company and add it to list
        TopCompany topCompany = null;
        if (cursor.moveToFirst()) {
            do {
                topCompany = new TopCompany();
                topCompany.setId(Integer.parseInt(cursor.getString(0)));
                topCompany.setCompanyName(cursor.getString(1));
                topCompany.setAlumni(Integer.parseInt(cursor.getString(2)));
                topCompany.setMotivation(Integer.parseInt(cursor.getString(3)));
                topCompany.setPosition(Integer.parseInt(cursor.getString(4)));

                // Add topCompany to topCompanies
                topCompanies.add(topCompany);

            } while (cursor.moveToNext());
        }

        Log.d("getAllTopCompanies()", topCompanies.toString());

        db.close();

        return topCompanies; // return top companies
    }

    // Get single topCompany
    public TopCompany getTopCompany(Integer id) {
        TopCompany topCompany = null;

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_TOP_COMPANY + " WHERE id = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                topCompany = new TopCompany();
                topCompany.setId(Integer.parseInt(cursor.getString(0)));
                topCompany.setCompanyName(cursor.getString(1));
                topCompany.setAlumni(Integer.parseInt(cursor.getString(2)));
                topCompany.setMotivation(Integer.parseInt(cursor.getString(3)));
                topCompany.setPosition(Integer.parseInt(cursor.getString(4)));
            }
        } catch (Exception e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        }

        Log.d("getTopCompany()", topCompany.toString());

        cursor.close();
        db.close();

        return topCompany; // return topCompany

    }

    // Updating single topCompany
    public int updateTopCompany(TopCompany topCompany) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(TOPCOMPANY_KEY_COMPANY_NAME, topCompany.getCompanyName());  // get companyName
        values.put(TOPCOMPANY_KEY_ALUMNI, topCompany.getAlumni());  // get alumni
        values.put(TOPCOMPANY_KEY_MOTIVATION, topCompany.getMotivation());  // get motivation
        values.put(TOPCOMPANY_KEY_POSITION, topCompany.getPosition());  // get position

        // 3. updating row
        int i  = db.update(TABLE_TOP_COMPANY, //table
                values, // column/value
                TOPCOMPANY_KEY_ID + " = ?", // selections
                new String[]{String.valueOf(topCompany.getId())}); // selection args

        // 4. close dbase
        db.close();
        Log.d("UpdateTopCompany", topCompany.toString());
        return i;
    }

    // Deleting single topCompany
    public void deleteTopCompany(TopCompany topCompany) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_TOP_COMPANY,
                TOPCOMPANY_KEY_ID + " = ?",
                new String[] {String.valueOf(topCompany.getId()) });

        // 3. close dbase
        db.close();

        Log.d("deleteTopCompany", topCompany.toString());
    }

    /** ---------- Create Reminder table and create CRUD operations ---------- **/

    // Method to create reminder table
    public void createReminderTable(SQLiteDatabase db) {
        // SQL statement to create reminder table
        String CREATE_REMINDER_TABLE = "CREATE TABLE reminder ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "time TEXT, " +
                "title TEXT, " +
                "notes TEXT )";

        // Create company table
        db.execSQL(CREATE_REMINDER_TABLE);
    }

    /* CRUD operations (create "add", read "get", update, delete) */
    public void addReminder(Reminder reminder) {
        Log.d("addReminder", reminder.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(REMINDER_KEY_DATE, reminder.getDate());  // get date
        values.put(REMINDER_KEY_TIME, reminder.getTime());  // get time
        values.put(REMINDER_KEY_TITLE, reminder.getTitle());  // get title
        values.put(REMINDER_KEY_NOTES, reminder.getNotes());  // get notes

        // 3. insert
        db.insert(TABLE_REMINDER, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. close dbase
        db.close();
    }

    // Get All Reminder
    public List<Reminder> getAllReminders() {
        List<Reminder> reminders = new LinkedList<Reminder>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_REMINDER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build reminder and add it to list
        Reminder reminder = null;
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setId(Integer.parseInt(cursor.getString(0)));
                reminder.setDate(cursor.getString(1));
                reminder.setTime(cursor.getString(2));
                reminder.setTitle(cursor.getString(3));
                reminder.setNotes(cursor.getString(4));

                // Add reminder to reminders
                reminders.add(reminder);

            } while (cursor.moveToNext());
        }

        Log.d("getAllReminders()", reminders.toString());

        db.close();

        return reminders; // return top reminders
    }

    // Get single reminder
    public Reminder getReminder(Integer id) {
        Reminder reminder = null;

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_REMINDER + " WHERE id = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                reminder = new Reminder();
                reminder.setId(Integer.parseInt(cursor.getString(0)));
                reminder.setDate(cursor.getString(1));
                reminder.setTime(cursor.getString(2));
                reminder.setTitle(cursor.getString(3));
                reminder.setNotes(cursor.getString(4));
            }
        } catch (Exception e) {
            Log.d("SQL Error", e.getMessage());
            return null;
        }

        Log.d("getReminder()", reminder.toString());

        cursor.close();
        db.close();

        return reminder; // return reminder

    }

    // Updating single reminder
    public int updateReminder(Reminder reminder) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(REMINDER_KEY_DATE, reminder.getDate());  // get date
        values.put(REMINDER_KEY_TIME, reminder.getTime());  // get time
        values.put(REMINDER_KEY_TITLE, reminder.getTitle());  // get title
        values.put(REMINDER_KEY_NOTES, reminder.getNotes());  // get notes

        // 3. updating row
        int i  = db.update(TABLE_REMINDER, //table
                values, // column/value
                REMINDER_KEY_ID + " = ?", // selections
                new String[]{String.valueOf(reminder.getId())}); // selection args

        // 4. close dbase
        db.close();
        Log.d("UpdateReminder", reminder.toString());
        return i;
    }

    // Deleting single reminder
    public void deleteReminder(Reminder reminder) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_REMINDER,
                REMINDER_KEY_ID + " = ?",
                new String[] {String.valueOf(reminder.getId()) });

        // 3. close dbase
        db.close();

        Log.d("deleteReminder", reminder.toString());
    }
}