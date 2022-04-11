package com.zakariahnaf.sqlitecrud.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zakariahnaf.sqlitecrud.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginClicked(View view) {
        Intent intent  =  new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}