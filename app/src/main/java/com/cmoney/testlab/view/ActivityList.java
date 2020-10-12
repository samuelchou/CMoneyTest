package com.cmoney.testlab.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmoney.testlab.R;
import com.cmoney.testlab.databinding.ActivityListBinding;
import com.cmoney.testlab.model.SinglePicture;
import com.cmoney.testlab.viewmodel.GalleryViewModel;
import com.cmoney.testlab.viewmodel.PictureListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {
    private GalleryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(GalleryViewModel.class);

        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        binding.setListModel(viewModel);

        SetupList();

        // Fetch Data
        viewModel.FetchGallery();
    }

    private void SetupList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        final PictureListAdapter adapter = new PictureListAdapter(new ArrayList<SinglePicture>());
        recyclerView.setAdapter(adapter);

        // Data Binding
        viewModel.getPictureList().observe(this, new Observer<List<SinglePicture>>() {
            @Override
            public void onChanged(List<SinglePicture> list) {
                adapter.updateList(list);
            }
        });
    }
}