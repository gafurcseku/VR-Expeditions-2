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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.lesson.LessonActivity;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.databinding.LessonListLayoutBinding;
import com.robotlab.expeditions2.download.DownloadListener;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;
import com.robotlab.expeditions2.utility.FileStore;

import java.io.File;
import java.lang.ref.WeakReference;
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
    private Boolean isDownloadStart = false;




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
        Log.i("onBindViewHolder",itemPosition+"");
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

            if (itemPosition == downloadPosition && isDownloadStart) {
                binding.downloadStatusTextView.setVisibility(View.VISIBLE);
                binding.subtitleTextView.setText("Downloading...");
                WeakReference<AppCompatTextView>  downloadStatusTextView = new WeakReference<>(binding.downloadStatusTextView);
                WeakReference<AppCompatTextView>  subtitleTextView = new WeakReference<>(binding.subtitleTextView);
                downloadLesson(lesson, downloadStatusTextView, subtitleTextView);
                isDownloadStart = false;
            }

            binding.titleTextView.setText(lesson.getTitle());
            binding.subtitleTextView.setText(lesson.getSubtitle());

            if(isMyExpedition){
                File file = new File(FileStore.getCacheFolder(context).getPath()+"/"+lesson.getId() + ".png");
                Glide.with(context)
                        .load(file)
                        .centerCrop()
                        .placeholder(R.drawable.empty_image_icon)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.logoImage);
            }else{
                Glide.with(context)
                        .load(lesson.getThumb())
                        .centerCrop()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.empty_image_icon)
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

            if(database.lessonDao().isExists(lesson.getId()) && database.lessonDao().getStatus(lesson.getId()) == 1 ){
                binding.broadcastLinearLayout.setVisibility(View.VISIBLE);
            }else{
                binding.broadcastLinearLayout.setVisibility(View.GONE);
            }


            binding.broadcastLinearLayout.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(context, LessonActivity.class);
                bundle.putInt("expedition_id",lesson.getExpeditionId());
                bundle.putInt("lesson_id",lesson.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }
    }

    public void setDownload(int position){
        this.downloadPosition = position;
        if(downloadPosition < lessonList.size()){
            isDownloadStart = true;
            lessonList.get(downloadPosition).setClock(false);
            notifyItemRangeChanged(downloadPosition-1,2);
        }else if(downloadPosition == lessonList.size()){
           // notifyItemChanged(downloadPosition-1);
            notifyDataSetChanged();
        }
    }

    public void setPreDownload(int position){
        for (int i = 0 ; i<lessonList.size() ; i++){
            lessonList.get(i).setClock(true);
        }
        notifyDataSetChanged();
        setDownload(position);
    }

    public void downloadLesson(Lesson lesson, WeakReference<AppCompatTextView> percentageTextView, WeakReference<AppCompatTextView> messageTextView) {
        int status = database.lessonDao().getStatus(lesson.getId());
        if (status == 0) {
            viewModel.FileDownload(lesson.getId(), 0, 0, lesson.getImage(), "" + lesson.getId() + ".png", percentageTextView.get(), new DownloadListener() {
                @Override
                public void onDownloadComplete(int status, int DownloadId) {
                    Log.i("Status",status+"-"+DownloadId);
                    if(status == 1 && DownloadId > 0){
                        DownloadCompleteManager(percentageTextView, messageTextView);
                        database.lessonDao().downloadStatus(DownloadId, status, lesson.getId());
                    }else if(status == 0  && DownloadId == 0){
                        DownloadCompleteManager(percentageTextView, messageTextView);
                    }
                }
            });
        }else{
            DownloadCompleteManager(percentageTextView, messageTextView);
        }
    }

    private void DownloadCompleteManager(WeakReference<AppCompatTextView> percentageTextView, WeakReference<AppCompatTextView> messageTextView) {
        if(lessonList.size()>downloadPosition){
            percentageTextView.get().setVisibility(View.GONE);
            messageTextView.get().setText(lessonList.get(downloadPosition).getSubtitle());
            downloadPosition++;
            setDownload(downloadPosition);
        }

    }
}
