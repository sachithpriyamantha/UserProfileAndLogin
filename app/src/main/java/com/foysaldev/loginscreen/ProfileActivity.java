package com.foysaldev.loginscreen;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private TextView occupationTxtView, nameTxtView, workTxtView;
    private TextView emailTxtView, phoneTxtView, videoTxtView, facebookTxtView, twitterTxtView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    private String userid;
    private static final String USERS = "users";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //receive data from login screen
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        Log.v("USERID", userRef.getKey());

        occupationTxtView = findViewById(R.id.occupation_textview);
        nameTxtView = findViewById(R.id.name_textview);
        workTxtView = findViewById(R.id.workplace_textview);
        emailTxtView = findViewById(R.id.email_textview);
        phoneTxtView = findViewById(R.id.phone_textview);
        videoTxtView = findViewById(R.id.video_textview);
        facebookTxtView = findViewById(R.id.facebook_textview1);
        twitterTxtView = findViewById(R.id.twitter_textview1);

        CircleImageView userImageView = findViewById(R.id.user_imageview);
        ImageView emailImageView = findViewById(R.id.email_imageview);
        @SuppressLint("CutPasteId") ImageView phoneImageView = findViewById(R.id.phone_imageview);
        @SuppressLint("CutPasteId") ImageView videoImageView = findViewById(R.id.phone_imageview);
        ImageView facebookImageView = this.<ImageView>findViewById(R.id.facebook_imageview);
        ImageView twitterImageView = this.<ImageView>findViewById(R.id.twitter_imageview);

        // Read from the database
        userRef.addValueEventListener(new ValueEventListener() {
            String fname, mail, profession, workplace, phone, facebook, twitter;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (Objects.equals(keyId.child("email").getValue(), email)) {
                        fname = keyId.child("fullName").getValue(String.class);
                        profession = keyId.child("profession").getValue(String.class);
                        workplace = keyId.child("workplace").getValue(String.class);
                        phone = keyId.child("phone").getValue(String.class);
                        facebook = keyId.child("facebook").getValue(String.class);
                        twitter = keyId.child("twitter").getValue(String.class);
                        break;
                    }
                }
                nameTxtView.setText(fname);
                emailTxtView.setText(email);
                occupationTxtView.setText(profession);
                workTxtView.setText(workplace);
                phoneTxtView.setText(phone);
                videoTxtView.setText(phone);
                facebookTxtView.setText(facebook);
                twitterTxtView.setText(twitter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}