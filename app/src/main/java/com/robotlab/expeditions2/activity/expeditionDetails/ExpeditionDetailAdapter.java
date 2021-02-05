package com.robotlab.expeditions2.activity.expeditionDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.lesson.LessonActivity;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.databinding.LessonListLayoutBinding;
import com.robotlab.expeditions2.download.DownloadListener;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;
import com.robotlab.expeditions2.utility.FileStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class ExpeditionDetailAdapter extends RecyclerView.Adapter<ExpeditionDetailAdapter.ViewModel> {
    private Context context;
    private List<Lesson> lessonList;
    private LessonListLayoutBinding binding;
    private AppDatabase database;
    private ExpeditionDetailViewModel viewModel;

    private int downloadPosition = -1;
    private Boolean isMyExpedition;
    private Boolean isPendingDownload = false;



    public ExpeditionDetailAdapter(Context context, AppDatabase database, List<Lesson> lessonList,ExpeditionDetailViewModel viewModel, Boolean isMyExpedition) {
        this.context = context;
        this.database= database;
        this.lessonList = lessonList;
        this.viewModel = viewModel;
        this.isMyExpedition = isMyExpedition;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = LessonListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int itemPosition) {
        Lesson lesson = lessonList.get(itemPosition);
        holder.bind(lesson,itemPosition,isMyExpedition);
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

        public void bind(Lesson lesson, int itemPosition , Boolean isMyExpedition){

            if (itemPosition == downloadPosition) {
                binding.downloadStatusTextView.setVisibility(View.VISIBLE);
                binding.subtitleTextView.setText("Downloading...");
                downloadLesson(lesson, binding.downloadStatusTextView, binding.subtitleTextView);
            }

            binding.titleTextView.setText(lesson.getTitle());
            binding.subtitleTextView.setText(lesson.getSubtitle());

            if(isMyExpedition){
                File file = new File(FileStore.getCacheFolder(context).getPath()+"/"+lesson.getId() + ".png");
                Glide.with(context)
                        .load(file)
                        .centerCrop()
                        .placeholder(R.drawable.ic_application_icon)
                        .into(binding.logoImage);
            }else{
                Glide.with(context)
                        .load(lesson.getThumb())
                        .centerCrop()
                        .placeholder(R.drawable.ic_application_icon)
                        .into(binding.logoImage);
            }

            if(lesson.getClock()){
                binding.clockImageView.setVisibility(View.VISIBLE);
                binding.subtitleTextView.setText("Pending...");
                binding.clockOverlyImageView.setVisibility(View.VISIBLE);
            }else{
                binding.clockImageView.setVisibility(View.GONE);
                binding.clockOverlyImageView.setVisibility(View.GONE);
            }

            if(isMyExpedition){
                binding.subtitleTextView.setVisibility(View.VISIBLE);
            }else {
                binding.subtitleTextView.setVisibility(View.GONE);
            }

            if(database.lessonDao().isExists(lesson.getId()) && database.lessonDao().getStatus(lesson.getId()) ==1 ){
                binding.broadcastLinearLayout.setVisibility(View.VISIBLE);
            }else{
                binding.broadcastLinearLayout.setVisibility(View.GONE);
            }


//            binding.broadcastLinearLayout.setOnClickListener(view -> {
//                Bundle bundle = new Bundle();
//                Intent intent = new Intent(context, LessonActivity.class);
//                bundle.putInt("expedition_id",lesson.getExpeditionId());
//                bundle.putInt("lesson_id",lesson.getId());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            });
        }
    }

    public void setDownload(int position){
        this.downloadPosition = position;
        if(downloadPosition <lessonList.size()){
            lessonList.get(downloadPosition).setClock(false);
            notifyItemChanged(downloadPosition);
        }
    }

    public void setPreDownload(int position){
        for (int i = 0 ; i<lessonList.size() ; i++){
            lessonList.get(i).setClock(true);
        }
        notifyDataSetChanged();
        setDownload(position);
    }

    public void downloadLesson(Lesson lesson, AppCompatTextView percentageTextView, AppCompatTextView messageTextView) {
        int status = database.lessonDao().getStatus(lesson.getId());
        if (status == 0) {
            viewModel.FileDownload(lesson.getId(), 0, 0, lesson.getImage(), "" + lesson.getId() + ".png", percentageTextView, new DownloadListener() {
                @Override
                public void onDownloadComplete(int status, int DownloadId) {
                    if(status == 1){
                        DownloadCompleteManager(percentageTextView, messageTextView);
                    }
                    database.lessonDao().downloadStatus(DownloadId, status, lesson.getId());
                }
            });
        }else{
            DownloadCompleteManager(percentageTextView, messageTextView);
        }
    }

    private void DownloadCompleteManager(AppCompatTextView percentageTextView, AppCompatTextView messageTextView) {
            percentageTextView.setVisibility(View.GONE);
            messageTextView.setText(lessonList.get(downloadPosition).getSubtitle());
            downloadPosition++;
            notifyItemChanged(downloadPosition-1);
            setDownload(downloadPosition);
    }
}
