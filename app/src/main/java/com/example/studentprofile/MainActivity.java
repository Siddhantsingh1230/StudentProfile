package com.example.studentprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StudentDatabaseHelper dbHelper;
    Button HomeBtn;
    TextView HomeText;
    Boolean isData = false;
    ProfileModel data = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Initialisations
        dbHelper = new StudentDatabaseHelper(this);
//        dbHelper.addProfile("Siddhant","Siddhant@mail.com",20,"9023134084");
        HomeBtn = findViewById(R.id.HomeBtn);
        HomeText = findViewById(R.id.homeText);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.homeaccent));
        data = dbHelper.getProfile();
        if (data.name != null) {
            isData = true;
            HomeText.setText("Welcome, "+ data.name);
        }
        else{
            HomeText.setText("Welcome, Student");
        }


    }


    //Homebtn Click
    public void openProfile(View v) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("isData", isData);
        profileIntent.putExtra("name", data.name);
        profileIntent.putExtra("contact", data.contact);
        profileIntent.putExtra("age", data.age);
        profileIntent.putExtra("email", data.email);
        profileIntent.putExtra("id", data.id);
        startActivity(profileIntent);

    }

    // for logging purpose
    public void logProfiles(StudentDatabaseHelper dbHelper) {
        ArrayList<ProfileModel> data = dbHelper.getProfiles();
        for (int i = 0; i < data.size(); i++) {
            Log.d("TABLE_PROFILE", "Id-" + data.get(i).id + " name-" + data.get(i).name + " Age-" +
                    data.get(i).age + " email-" + data.get(i).email);
        }
    }
}


// for refrences
//        To insert data
//        dbHelper.addProfile("Siddhant","Siddhant@mail.com",20,"9023134084");

//        to get data
//        logProfiles(dbHelper);

//        update data
//        ContentValues cv = new ContentValues();
//        cv.put("name","Siddhant Singh");
//        dbHelper.updateProfile(cv,1);

//        delete data
//        dbHelper.deleteProfile(3);