package com.cmoney.testlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cmoney.testlab.R;

public class ActivityItemDetail extends AppCompatActivity {
    public static final String ARG_ID = "Arg_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent intent = getIntent();
        if (intent.getExtras() == null) {
            Toast.makeText(this, R.string.msg_fail_to_load, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        int id = intent.getIntExtra(ARG_ID, -1);
        if (id <= 0) {
            Toast.makeText(this, R.string.msg_fail_to_load, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        SetupContent(id);
    }

    private void SetupContent(int id) {
        TextView itemTitle = findViewById(R.id.itemTitle);
        itemTitle.setText(id + "");

    }
}