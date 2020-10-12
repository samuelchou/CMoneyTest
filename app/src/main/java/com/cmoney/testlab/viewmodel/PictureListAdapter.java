package com.cmoney.testlab.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmoney.testlab.R;

public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.PictureViewHolder> {


    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PictureViewHolder(layoutInflater.inflate(R.layout.item_picture_with_title, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        // TODO: 2020/10/12 完成binding
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
