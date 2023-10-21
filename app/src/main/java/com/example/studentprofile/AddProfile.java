package com.example.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddProfile extends AppCompatActivity {
    EditText name;
    EditText age;
    EditText contact;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
    }

    // create profile
    public void createProfile(View v) {
        String nameValue = name.getText().toString().trim();
        String ageValue = age.getText().toString().trim();
        String contactValue = contact.getText().toString().trim();
        String emailValue = email.getText().toString().trim();
        if (!nameValue.isEmpty() && !ageValue.isEmpty() && !contactValue.isEmpty() &&
                !emailValue.isEmpty()) {
            StudentDatabaseHelper dbHelper = new StudentDatabaseHelper(this);
            dbHelper.addProfile(nameValue,emailValue,Integer.parseInt(ageValue),contactValue);
            Toast.makeText(this, "Profile Created!", Toast.LENGTH_SHORT).show();
            Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            finish();
        } else {
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show();
        }
    }
}