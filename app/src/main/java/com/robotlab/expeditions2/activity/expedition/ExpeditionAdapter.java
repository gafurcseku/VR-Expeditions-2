package com.robotlab.expeditions2.activity.expedition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.databinding.ExpeditionListLayoutBinding;
import com.robotlab.expeditions2.model.Expedition;

import java.util.ArrayList;
import java.util.List;

public class ExpeditionAdapter extends RecyclerView.Adapter<ExpeditionAdapter.ViewModel> {

    private Context context;
    private List<Expedition> expeditionList;
    private List<Expedition> OldExpeditionList = new ArrayList<>();
    private ExpeditionListLayoutBinding binding;
    private ItemClick itemClick;

    public ExpeditionAdapter(Context context, List<Expedition> expeditionList, ItemClick itemClick) {
        this.context = context;
        this.expeditionList = expeditionList;
        this.OldExpeditionList.addAll(expeditionList);
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

    public void searchText(String text){
       if(!expeditionList.isEmpty()){
           expeditionList.clear();
           if(text.isEmpty()){
               expeditionList.addAll(OldExpeditionList);
           }else{
               text = text.toLowerCase();
               for (Expedition expedition : OldExpeditionList) {
                   if(expedition.getTitle().toLowerCase().contains(text) || expedition.getDescription().toLowerCase().contains(text)){
                       expeditionList.add(expedition);
                   }
               }
           }
           notifyDataSetChanged();
       }
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
//            Glide.with(context)
//                    .load(expedition.getImage_url())
//                    .centerCrop()
//                    .placeholder(R.drawable.ic_application_icon)
//                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                    .into(binding.coverImageView);
            binding.titleTextView.setText(expedition.getTitle());
            binding.subtitleTextView.setText(expedition.getDescription());
            binding.lessonTextView.setText(expedition.getLesson());
            binding.gradeTextView.setText(expedition.getGrade());
            binding.typeTextView.setText(expedition.getType());
            binding.rootView.setOnClickListener(view -> itemClick.OnClick(expedition,false));
        }
    }
}


