package com.robotlab.expeditions2.activity.expeditionDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.databinding.LessonListLayoutBinding;
import com.robotlab.expeditions2.download.DownloadListener;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;

import java.util.List;

public class ExpeditionDetailAdapter extends RecyclerView.Adapter<ExpeditionDetailAdapter.ViewModel> {
    private Context context;
    private List<Lesson> lessonList;
    private LessonListLayoutBinding binding;
    private AppDatabase database;

    public ExpeditionDetailAdapter(Context context, AppDatabase database, List<Lesson> lessonList) {
        this.context = context;
        this.database= database;
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
                    .load(lesson.getThumb())
                    .centerCrop()
                    .placeholder(R.drawable.ic_application_icon)
                    .into(binding.logoImage);

            if(database.lessonDao().isExists(lesson.getId())){
                binding.broadcastTextView.setVisibility(View.VISIBLE);
            }else{
                binding.broadcastTextView.setVisibility(View.GONE);
            }
        }
    }

    public void downloadLesson(ExpeditionDetailViewModel viewModel){
        for (Lesson lesson : lessonList){
            List<LessonImage> lessonImageList = database.lessonImageDao().getLessonImageByLessonId(lesson.getId());
            for (LessonImage lessonImage :lessonImageList){
                LessonImage aLessonImage = database.lessonImageDao().getLessonImage(lessonImage.getId());
                if(aLessonImage.getStatus() == 0){
                    viewModel.FileDownload(aLessonImage.getId(),aLessonImage.getUrl(), ""+aLessonImage.getId()+".png", null, null, new DownloadListener() {
                        @Override
                        public void onDownloadComplete(int status, int DownloadId) {
                            database.lessonImageDao().downloadStatus(DownloadId,status,aLessonImage.getId());
                        }
                    });
                }
            }
        }
    }
}
