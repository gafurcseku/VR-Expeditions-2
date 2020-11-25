package com.robotlab.expeditions2.activity.expedition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.databinding.ExpeditionListLayoutBinding;
import com.robotlab.expeditions2.model.Expedition;

import java.util.List;

public class ExpeditionAdapter extends RecyclerView.Adapter<ExpeditionAdapter.ViewModel> {

    private Context context;
    private List<Expedition> expeditionList;
    private ExpeditionListLayoutBinding binding;
    private ItemClick itemClick;

    public ExpeditionAdapter(Context context, List<Expedition> expeditionList, ItemClick itemClick) {
        this.context = context;
        this.expeditionList = expeditionList;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ExpeditionAdapter.ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ExpeditionListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpeditionAdapter.ViewModel holder, int position) {
        Expedition expedition = expeditionList.get(position);
        holder.bind(expedition);
    }

    @Override
    public int getItemCount() {
        return expeditionList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        ExpeditionListLayoutBinding binding;
        public ViewModel(ExpeditionListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Expedition expedition) {
            binding.titleTextView.setText(expedition.getTitle());
            binding.subtitleTextView.setText(expedition.getShortTitle());
            binding.lessonTextView.setText(expedition.getLesson());
            binding.gradeTextView.setText(expedition.getGrade());
            binding.typeTextView.setText(expedition.getType());
            binding.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.OnClick(expedition);
                }
            });
        }
    }
}


