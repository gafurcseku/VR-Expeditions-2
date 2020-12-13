package com.robotlab.expeditions2.activity.MyExpedition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.expedition.ItemClick;
import com.robotlab.expeditions2.databinding.MyExpeditionListLayoutBinding;
import com.robotlab.expeditions2.model.Expedition;

import java.util.List;

public class MyExpeditionAdapter extends RecyclerView.Adapter<MyExpeditionAdapter.ViewModel> {

    private Context context;
    private List<Expedition> expeditionList;
    private MyExpeditionListLayoutBinding binding;
    private ItemClick itemClick;

    public MyExpeditionAdapter(Context context, List<Expedition> expeditionList,ItemClick itemClick) {
        this.context = context;
        this.expeditionList = expeditionList;
        this.itemClick=itemClick;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MyExpeditionListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        Expedition expedition = expeditionList.get(position);
        holder.bind(expedition);
    }

    @Override
    public int getItemCount() {
        return expeditionList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        MyExpeditionListLayoutBinding binding;
        public ViewModel(MyExpeditionListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Expedition expedition) {
            Glide.with(context)
                    .load(expedition.getImage_url())
                    .centerCrop()
                    .placeholder(R.drawable.ic_application_icon)
                    .into(binding.coverImage);
            binding.titleTextView.setText(expedition.getTitle());
            binding.subtitleTextView.setText(expedition.getDescription());
            binding.lessonTextView.setText(expedition.getLesson());
            binding.gradeTextView.setText(expedition.getGrade());
            binding.typeTextView.setText(expedition.getType());
            binding.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.OnClick(expedition, true);
                }
            });
        }
    }
}
