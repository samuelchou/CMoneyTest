package com.cmoney.testlab.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmoney.testlab.R;
import com.cmoney.testlab.viewmodel.PictureListAdapter;

public class ActivityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        SetupList();
    }

    private void SetupList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        PictureListAdapter adapter = new PictureListAdapter();
        recyclerView.setAdapter(adapter);

    }
}