package com.cmoney.testlab.viewmodel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmoney.testlab.R;
import com.cmoney.testlab.model.SinglePicture;
import com.cmoney.testlab.view.ActivityItemDetail;

import java.util.List;

public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.PictureViewHolder> {
    private List<SinglePicture> pictureList;

    public PictureListAdapter(List<SinglePicture> list) {
        pictureList = list;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PictureViewHolder(layoutInflater.inflate(R.layout.item_picture_with_title, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PictureViewHolder holder, final int position) {
        // TODO: 2020/10/12 完成binding
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityItemDetail.class);
                intent.putExtra(ActivityItemDetail.ARG_ID, position);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public void updateList(List<SinglePicture> pictures) {
        pictureList.clear();
        pictureList.addAll(pictures);
        notifyDataSetChanged();
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
