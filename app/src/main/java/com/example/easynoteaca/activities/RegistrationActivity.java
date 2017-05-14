package com.example.easynoteaca.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.easynoteaca.R;
import com.example.easynoteaca.pojo.User;
import com.example.easynoteaca.util.PreferancesHelper;
import com.example.easynoteaca.util.user.FileUserStorage;
import com.example.easynoteaca.util.user.UserStorage;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText fNameTv;
    private EditText lNameTv;
    private EditText emailTv;
    private EditText usernameTv;
    private EditText password1Tv;
    private EditText password2Tv;
    private RadioGroup genderRg;
    private EditText ageTv;

    private UserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fNameTv = (EditText) findViewById(R.id.activity_reg_fname_et);
        lNameTv = (EditText) findViewById(R.id.activity_reg_lname_et);
        emailTv = (EditText) findViewById(R.id.activity_reg_email_et);
        usernameTv = (EditText) findViewById(R.id.activity_reg_username_et);
        password1Tv = (EditText) findViewById(R.id.activity_reg_password1_et);
        password2Tv = (EditText) findViewById(R.id.activity_reg_password2_et);
        genderRg = (RadioGroup) findViewById(R.id.activity_reg_gender_rg);
        ageTv = (EditText) findViewById(R.id.activity_reg_age_et);

        findViewById(R.id.activity_reg_btn).setOnClickListener(this);

        userStorage = new FileUserStorage();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_reg_btn:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        if(!checkAllFieldsValue()){
            return;
        }

        String fName = fNameTv.getText().toString();
        String lName = lNameTv.getText().toString();
        String email = emailTv.getText().toString();
        String username = usernameTv.getText().toString();
        String password = password1Tv.getText().toString();
        User.Gender gender = genderRg.getCheckedRadioButtonId() == R.id.activity_reg_gender_rb_male ? User.Gender.MALE : User.Gender.FEMALE;
        int age = Integer.valueOf(ageTv.getText().toString());
        User user = new User(username, email, password, fName, lName, gender, age);

        userStorage.registerUser(user, new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });

    }

    private void handelUserFound(User user){
        if(user == null){
            Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_SHORT).show();
            return;
        }

        PreferancesHelper preferancesHelper = PreferancesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
    private boolean checkAllFieldsValue(){

        if((fNameTv.getText().length() == 0 || lNameTv.getText().length() == 0 || emailTv.getText().length() == 0
                ||usernameTv.getText().length() == 0 || password1Tv.getText().length() == 0 ||
                password2Tv.getText().length() == 0 || genderRg.getCheckedRadioButtonId() == -1 || ageTv.getText().length() ==0)
                && password1Tv.getText().toString().equals(password2Tv.getText().toString()) ){

            Toast.makeText(this,R.string.wrong_data,Toast.LENGTH_LONG).show();
            return  false;
        }
        return true;
    }
}
