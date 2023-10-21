package com.example.studentprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Student";
    private static int DB_VERSION = 1;
    private static final String TABLE_NAME = "Profile";
    private static final String KEY_PROFILE_ID = "id";
    private static final String PROFILE_NAME = "name";
    private static final String PROFILE_CONTACT = "contact";
    private static final String PROFILE_AGE = "age";
    private static final String PROFILE_EMAIL = "email";

    public StudentDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // when the obj of this class is created this methods runs and creates a new db of same
        // name if not created or will return the reference of previously existing database of same name
        String createTblSQL = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PROFILE_NAME + " TEXT, " +
                PROFILE_AGE + " INTEGER, " +
                PROFILE_CONTACT + " TEXT, " +
                PROFILE_EMAIL + " TEXT)";
        db.execSQL(createTblSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP Table IF EXISTS " + TABLE_NAME);
        //Again create table after dropping previous version table
        onCreate(db);
    }

    public void addProfile(String name, String email, int age, String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(PROFILE_NAME, name);
        dataToInsert.put(PROFILE_EMAIL, email);
        dataToInsert.put(PROFILE_AGE, age);
        dataToInsert.put(PROFILE_CONTACT, contact);
        db.insert(TABLE_NAME, null, dataToInsert);
    }

    public ArrayList<ProfileModel> getProfiles() {
        //Open the database
        SQLiteDatabase db = this.getReadableDatabase();
        // returns the cursor
        Cursor cr = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        //Moving cursor to next record

        //Data creation
        ArrayList<ProfileModel> data = new ArrayList<>();
        while (cr.moveToNext()) {
            ProfileModel model = new ProfileModel();
            model.id = cr.getInt(0);
            model.name = cr.getString(1);
            model.age = cr.getInt(2);
            model.contact = cr.getString(3);
            model.email = cr.getString(4);
            data.add(model);
        }
        return data;
    }

    public void updateProfile(ContentValues data, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, data, KEY_PROFILE_ID + "=" + id, null);
    }

    public void deleteProfile(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_PROFILE_ID + "=" + id, null);
    }

    public ProfileModel getProfile() {
        ProfileModel data = new ProfileModel();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_PROFILE_ID + " DESC LIMIT 1";
        Cursor cr = db.rawQuery(query, null);

        if (cr.moveToFirst()) {
            data.id = cr.getInt(0);
            data.name = cr.getString(1);
            data.age = cr.getInt(2);
            data.contact = cr.getString(3);
            data.email = cr.getString(4);
        }
        return data;
    }
}
