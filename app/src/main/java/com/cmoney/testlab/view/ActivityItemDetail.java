package com.cmoney.testlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.cmoney.testlab.R;
import com.cmoney.testlab.viewmodel.SinglePictureViewModel;

public class ActivityItemDetail extends AppCompatActivity {
    public static final String ARG_ID = "Arg_ID";

    private SinglePictureViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SinglePictureViewModel.class);

        com.cmoney.testlab.databinding.ActivityItemDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail);
        binding.setViewModel(viewModel);

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

        FetchContent(id);
    }

    private void FetchContent(int id) {
        viewModel.Request(id);
    }
}