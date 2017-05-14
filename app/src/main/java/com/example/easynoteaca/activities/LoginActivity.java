package com.example.easynoteaca.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easynoteaca.R;
import com.example.easynoteaca.pojo.User;
import com.example.easynoteaca.util.PreferancesHelper;
import com.example.easynoteaca.util.user.FileUserStorage;
import com.example.easynoteaca.util.user.UserStorage;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText usernameEt;
    private EditText passwordEt;

    private UserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = (EditText) findViewById(R.id.activity_login_username_et);
        passwordEt = (EditText) findViewById(R.id.activity_login_password_et);

        findViewById(R.id.activity_login_login_btn).setOnClickListener(this);
        findViewById(R.id.activity_login_signup_btn).setOnClickListener(this);

        hideSoftKeyboard();
        userStorage = new FileUserStorage();
    }

    private void signUp(){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    private void login(){
        if(!checkInputValid()){
            return;
        }

        userStorage.checkAndGetUser(usernameEt.getText().toString(), passwordEt.getText().toString(), new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });
    }

    private void handelUserFound(User user){
        if(user == null){
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferancesHelper preferancesHelper = PreferancesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean checkInputValid(){
        boolean valid = true;

        if(usernameEt.getText().length() == 0 || passwordEt.getText().length() == 0){
            Toast.makeText(this, "Username and password fields are required", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if (usernameEt.getText() == null || usernameEt.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            usernameEt.requestFocus();
            return false;
        } else if (usernameEt.getText().toString().trim().length() < 3) {
            usernameEt.setError("You must have 3 characters in your Username");
            return false;
        } else if (passwordEt.getText() == null || passwordEt.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            passwordEt.requestFocus();
            return false;
        } else if (passwordEt.getText().toString().trim().length() < 3) {
            passwordEt.setError("You must have 3 characters in your Username");
            return false;
        }


        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_login_btn:
                login();
                break;

            case R.id.activity_login_signup_btn:
                signUp();
                break;
        }
    }

    public void hideSoftKeyboard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


}
