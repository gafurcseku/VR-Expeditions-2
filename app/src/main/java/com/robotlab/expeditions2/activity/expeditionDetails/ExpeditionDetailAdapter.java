package com.robotlab.expeditions2.activity.expeditionDetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.databinding.LessonListLayoutBinding;
import com.robotlab.expeditions2.download.DownloadListener;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;

import java.util.ArrayList;
import java.util.List;

public class ExpeditionDetailAdapter extends RecyclerView.Adapter<ExpeditionDetailAdapter.ViewModel> {
    private Context context;
    private List<Lesson> lessonList;
    private LessonListLayoutBinding binding;
    private AppDatabase database;
    private ExpeditionDetailViewModel viewModel;
    private int position = 0;
    private int downloadPosition = -1;
    private List<LessonImage> lessonImageList;
    private Boolean isMyExpedition;


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
            Log.e("lesson",lesson.getId()+"");

            if(itemPosition == downloadPosition ){
                lessonImageList = database.lessonImageDao().getLessonImageByLessonId(lesson.getId());
                if(lessonImageList.size() > 0){
                    viewModel.totalCalculate = 0 ;
                    position = 0;
                    binding.downloadStatusTextView.setVisibility(View.VISIBLE);
                    binding.subtitleTextView.setText("Downloading...");
                    downloadLesson(lessonImageList.get(position),binding.downloadStatusTextView,binding.subtitleTextView);
                }
            }

            binding.titleTextView.setText(lesson.getTitle());
            binding.subtitleTextView.setText(lesson.getSubtitle());
            Glide.with(context)
                    .load(lesson.getThumb())
                    .centerCrop()
                    .placeholder(R.drawable.ic_application_icon)
                    .into(binding.logoImage);

            if(lesson.getClock()){
                binding.clockImageView.setVisibility(View.VISIBLE);
                binding.subtitleTextView.setText("Pending...");
            }else{
                binding.clockImageView.setVisibility(View.GONE);
            }

            if(database.lessonDao().isExists(lesson.getId()) && isMyExpedition){
                binding.broadcastLinearLayout.setVisibility(View.VISIBLE);
            }else{
                binding.broadcastLinearLayout.setVisibility(View.GONE);
            }
        }
    }

    public void setDownload(int position){
        this.downloadPosition = position;
        if(downloadPosition <lessonList.size()){
            lessonList.get(downloadPosition).setClock(false);
            database.lessonDao().downloadStatus(1,lessonList.get(downloadPosition).getId());
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

    public void downloadLesson(LessonImage lessonImage, AppCompatTextView percentageTextView, AppCompatTextView messageTextView) {
        if(position >= lessonImageList.size()){
            return;
        }
        LessonImage aLessonImage = database.lessonImageDao().getLessonImage(lessonImage.getId());
        if (aLessonImage.getStatus() == 0) {
            viewModel.FileDownload(aLessonImage.getId(), (position+1), lessonImageList.size(), aLessonImage.getUrl(), "" + aLessonImage.getId() + ".png", percentageTextView, new DownloadListener() {
                @Override
                public void onDownloadComplete(int status, int DownloadId) {
                    if(status == 1){
                        DownloadCompleteManager(percentageTextView, messageTextView);
                    }
                    database.lessonImageDao().downloadStatus(DownloadId, status, aLessonImage.getId());
                    messageTextView.setText("Downloading...");
                }
            });
        }else{
            DownloadCompleteManager(percentageTextView, messageTextView);
        }

    }

    private void DownloadCompleteManager(AppCompatTextView percentageTextView, AppCompatTextView messageTextView) {
        position++;
        if(position < lessonImageList.size()){
            downloadLesson(lessonImageList.get(position),percentageTextView,messageTextView);
        }else{
            percentageTextView.setVisibility(View.GONE);
            messageTextView.setText(lessonList.get(downloadPosition).getSubtitle());
            database.lessonDao().downloadStatus(1,lessonList.get(downloadPosition).getId());
            downloadPosition++;
            notifyItemChanged(downloadPosition-1);
            setDownload(downloadPosition);
        }
    }
}
