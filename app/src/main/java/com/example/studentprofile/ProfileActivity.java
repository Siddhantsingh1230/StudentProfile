package com.example.studentprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout profileData;
    TextView profileName;
    TextView profileAge;
    TextView profileContact;
    TextView profileEmail;
    TextView emptyDatatext;
    Button profileDeleteBtn;
    Button profileEditBtn;
    Button addProfile;
    int profileId;
    Intent dataIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.profileaccent));
        profileData = findViewById(R.id.ProfileData);
        profileAge = findViewById(R.id.profileAge);
        profileName = findViewById(R.id.profileName);
        profileContact = findViewById(R.id.profileContact);
        profileEmail = findViewById(R.id.profileEmail);
        emptyDatatext = findViewById(R.id.emptyDatatext);
        profileDeleteBtn = findViewById(R.id.profileDeleteBtn);
        profileEditBtn = findViewById(R.id.profileEditBtn);
        addProfile = findViewById(R.id.addProfile);
        dataIntent = getIntent();
        Boolean isData = dataIntent.getBooleanExtra("isData", false);
        profileId = dataIntent.getIntExtra("id", 0);
        if (isData) {
            profileData.setVisibility(View.VISIBLE);
            profileDeleteBtn.setVisibility(View.VISIBLE);
            profileEditBtn.setVisibility(View.VISIBLE);
            emptyDatatext.setVisibility(View.GONE);
            profileContact.setText((String) dataIntent.getStringExtra("contact"));
            profileName.setText((String) dataIntent.getStringExtra("name"));
            profileAge.setText(Integer.toString(dataIntent.getIntExtra("age", 0)));
            profileEmail.setText((String) dataIntent.getStringExtra("email"));
            addProfile.setVisibility(View.GONE);
        }

    }

    // delete profile btn
    public void deleteProfile(View v) {
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        db.deleteProfile(profileId);
        Toast.makeText(this, "Profile Deleted!", Toast.LENGTH_SHORT).show();
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        finish();

    }
    // edit profile btn

    public void editProfile(View v) {
        Intent updateProfileIntent = new Intent(this, UpdateProfile.class);
        updateProfileIntent.putExtra("profileId",profileId);
        updateProfileIntent.putExtra("name",(String) dataIntent.getStringExtra("name"));
        updateProfileIntent.putExtra("age",Integer.toString(dataIntent.getIntExtra("age", 0)));
        updateProfileIntent.putExtra("contact",(String) dataIntent.getStringExtra("contact"));
        updateProfileIntent.putExtra("email",(String) dataIntent.getStringExtra("email"));
        startActivity(updateProfileIntent);
    }

    // add profile btn
    public void addProfile(View v){
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        Intent addProfileIntent = new Intent(this, AddProfile.class);
        startActivity(addProfileIntent);
    }
}