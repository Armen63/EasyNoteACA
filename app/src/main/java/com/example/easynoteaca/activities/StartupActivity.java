package com.example.easynoteaca.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.easynoteaca.R;
import com.example.easynoteaca.util.PreferancesHelper;


public class StartupActivity extends AppCompatActivity {


    private static final int DELAY = 1000;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_startup);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate();
            }
        }, DELAY);
    }

    private void navigate() {
        Class<? extends Activity> activityClass;

        if (PreferancesHelper.getInstance(this).isLoggedIn()) {
            activityClass = MainActivity.class;
        } else {
            activityClass = LoginActivity.class;
        }

        startActivity(new Intent(this, activityClass));
        finish();
    }

    @Override
    public void onBackPressed() {
    }

}