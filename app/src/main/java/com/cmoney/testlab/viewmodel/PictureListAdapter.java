package com.cmoney.testlab.viewmodel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmoney.testlab.databinding.ItemPictureWithTitleBinding;
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
        ItemPictureWithTitleBinding binding = ItemPictureWithTitleBinding.inflate(layoutInflater, parent, false);
        return new PictureViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final PictureViewHolder holder, int position) {
        final SinglePicture picture = pictureList.get(position);
        holder.bindWith(picture);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityItemDetail.class);
                intent.putExtra(ActivityItemDetail.ARG_ID, picture.getId());
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
        private ItemPictureWithTitleBinding binding;

        public PictureViewHolder(@NonNull ItemPictureWithTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindWith(SinglePicture picture) {
            binding.setItemModel(picture);
            binding.executePendingBindings();
            // 即刻更新資料到Layout上。參見： https://stackoverflow.com/questions/53043412/android-why-use-executependingbindings-in-recyclerview
        }
    }
}
