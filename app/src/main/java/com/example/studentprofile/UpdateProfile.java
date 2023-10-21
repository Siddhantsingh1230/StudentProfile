package com.example.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateProfile extends AppCompatActivity {
    TextView name;
    TextView age;
    TextView email;
    TextView contact;
    Button updateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        name = findViewById(R.id.name);
        age = findViewById((R.id.age));
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        updateProfile = findViewById(R.id.updateProfile);
        Intent data = getIntent();
        name.setText((String) data.getStringExtra("name"));
        age.setText((String) data.getStringExtra("age"));
        contact.setText((String) data.getStringExtra("contact"));
        email.setText((String) data.getStringExtra("email"));
        //OnUpdate btn click
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDatabaseHelper db = new StudentDatabaseHelper(getApplicationContext());
                ContentValues updateData = new ContentValues();
                String nameValue = name.getText().toString();
                int ageValue = Integer.parseInt(age.getText().toString());
                String contactValue = contact.getText().toString();
                String emailValue = email.getText().toString();
                updateData.put("name", nameValue);
                updateData.put("age", ageValue);
                updateData.put("contact", contactValue);
                updateData.put("email", emailValue);
                db.updateProfile(updateData, data.getIntExtra("profileId", 0));
                Toast.makeText(getApplicationContext(), "Profile Updated!", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                finish();
            }
        });
    }

}