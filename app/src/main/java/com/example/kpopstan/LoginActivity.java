package com.example.kpopstan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kpopstan.db.Tables.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public static User user;
    private EditText userLogin;
    private EditText userPassword;
    private Button signIn;
    private Button signUp;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String USER_KEY = "user";
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userLogin = findViewById(R.id.login);
        userPassword = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            findUserInDB(mAuth.getCurrentUser().getEmail());
        }
        signUp.setOnClickListener(view -> {
            Registration(userLogin.getText().toString(), userPassword.getText().toString());
        });
        signIn.setOnClickListener(view -> {
            Authorization(userLogin.getText().toString(), userPassword.getText().toString());
        });
    }

    public void Authorization(String email, String password) {
        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email and password can`t be empty", Toast.LENGTH_LONG).show();
        } else if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email can`t be empty", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty() || password.length() < 8) {
            Toast.makeText(LoginActivity.this, "Password can`t be empty and must be 8 characters or more", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Successful authorization.\nWelcome!", Toast.LENGTH_LONG).show();
                    findUserInDB(userLogin.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong authorization\n" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void Registration(String email, String password) {
        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email and password can`t be empty", Toast.LENGTH_LONG).show();
        } else if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email can`t be empty", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty() || password.length() < 8) {
            Toast.makeText(LoginActivity.this, "Password can`t be empty and must be 8 characters or more", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    addUserInDB(userLogin.getText().toString());
                    Toast.makeText(LoginActivity.this, "Successful registration.\nWelcome!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong registration\n" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void addUserInDB(String email) {
        if (email.isEmpty() && userPassword.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email and password can`t be empty", Toast.LENGTH_LONG).show();
        }
        if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email can`t be empty", Toast.LENGTH_LONG).show();
        } else if (userPassword.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Password can`t be empty", Toast.LENGTH_LONG).show();
        } else {
            mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
            mDatabase.push().setValue(new User(email, "user"));
            goToMainPage();
        }
    }

    public void findUserInDB(String email) {
        userArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user_ = ds.getValue(User.class);
                    userArrayList.add(user_);
                }
                for (int i = 0; i < userArrayList.size(); i++) {
                    if (userArrayList.get(i).getLogin().equals(email)) {
                        user = userArrayList.get(i);
                    }
                }
                if (user != null) {
                    if (user.getRole().equals("user")) {
                        goToMainPage();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(LoginActivity.this), error.getMessage());
            }
        });
    }

    public void goToMainPage() {
        Intent intent = new Intent(LoginActivity.this, GroupListActivity.class);
        startActivity(intent);
    }
}