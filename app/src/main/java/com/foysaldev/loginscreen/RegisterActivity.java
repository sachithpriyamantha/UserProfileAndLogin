package com.foysaldev.loginscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout nameEditText, professionEditText, workEditText, passwordEditText;
    private TextInputLayout phoneEditText, emailEditText, facebookEditText, twitterEditText;
    private DatabaseReference mDatabase;
    private static final String USERS = "users";
    private final String TAG = "RegisterActivity";
    private String username, fname, email, profession, phone, workplace, facebook, twitter;
    private String password;
    private User user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.fullname_edittext);
        professionEditText = findViewById(R.id.profession_edittext);
        workEditText = findViewById(R.id.workplace_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);
        passwordEditText = findViewById(R.id.enterpass_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        facebookEditText = findViewById(R.id.facebook_edittext);
        twitterEditText = findViewById(R.id.twitter_edittext);
        CircleImageView picImageView = findViewById(R.id.pic_imageview);
        CheckBox maleCheckBox = findViewById(R.id.male_checkbox);
        CheckBox femaleCheckBox = findViewById(R.id.female_checkbox);
        Button registerButton = findViewById(R.id.register_button);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                Objects.requireNonNull(emailEditText.getEditText()).getText().toString();
                String s = Objects.requireNonNull(passwordEditText.getEditText()).getText().toString();
                fname = Objects.requireNonNull(nameEditText.getEditText()).getText().toString();
                email = emailEditText.getEditText().getText().toString();
                phone = Objects.requireNonNull(phoneEditText.getEditText()).getText().toString();
                profession = Objects.requireNonNull(professionEditText.getEditText()).getText().toString();
                workplace = Objects.requireNonNull(workEditText.getEditText()).getText().toString();
                password = passwordEditText.getEditText().getText().toString();
                facebook = Objects.requireNonNull(facebookEditText.getEditText()).getText().toString();
                twitter = Objects.requireNonNull(twitterEditText.getEditText()).getText().toString();
                user = new User(fname, email, profession, workplace, phone, facebook, twitter);
                registerUser();
            }
        });

    }

    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * adding user information to database and redirect to login screen
     */
    public void updateUI(FirebaseUser currentUser) {
        String keyid = mDatabase.push().getKey();
        assert keyid != null;
        mDatabase.child(keyid).setValue(user); //adding user info to database
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
    }
}