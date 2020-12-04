package com.robotlab.expeditions2.activity.expeditionDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.databinding.LessonListLayoutBinding;
import com.robotlab.expeditions2.model.Lesson;

import java.util.List;

public class ExpeditionDetailAdapter extends RecyclerView.Adapter<ExpeditionDetailAdapter.ViewModel> {
    private Context context;
    private List<Lesson> lessonList;
    private LessonListLayoutBinding binding;

    public ExpeditionDetailAdapter(Context context, List<Lesson> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = LessonListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.bind(lesson);
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        LessonListLayoutBinding binding;
        public ViewModel(LessonListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(Lesson lesson){
            binding.titleTextView.setText(lesson.getTitle());
            binding.subtitleTextView.setText(lesson.getSubtitle());
            Glide.with(context)
                    .load(lesson.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.ic_application_icon)
                    .into(binding.logoImage);
        }
    }
}
