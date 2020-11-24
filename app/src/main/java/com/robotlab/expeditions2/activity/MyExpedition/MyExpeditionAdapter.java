package com.robotlab.expeditions2.activity.MyExpedition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robotlab.expeditions2.databinding.ExpeditionListLayoutBinding;
import com.robotlab.expeditions2.databinding.MyExpeditionListLayoutBinding;
import com.robotlab.expeditions2.model.Expedition;

import java.util.List;

public class MyExpeditionAdapter extends RecyclerView.Adapter<MyExpeditionAdapter.ViewModel> {

    private Context context;
    private List<Expedition> expeditionList;
    private MyExpeditionListLayoutBinding binding;

    public MyExpeditionAdapter(Context context, List<Expedition> expeditionList) {
        this.context = context;
        this.expeditionList = expeditionList;
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
            binding.titleTextView.setText(expedition.getTitle());
            binding.subtitleTextView.setText(expedition.getShortTitle());
            binding.lessonTextView.setText(expedition.getLesson());
            binding.gradeTextView.setText(expedition.getGrade());
            binding.typeTextView.setText(expedition.getType());
        }
    }
}
