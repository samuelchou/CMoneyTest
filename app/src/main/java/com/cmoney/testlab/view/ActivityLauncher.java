package com.cmoney.testlab.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cmoney.testlab.R;

public class ActivityLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }
}