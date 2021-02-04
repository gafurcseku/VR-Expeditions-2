package com.robotlab.expeditions2.activity.lesson;



import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import com.bumptech.glide.Glide;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.base.BaseActivity;
import com.robotlab.expeditions2.databinding.ActivityLessonBinding;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.utility.FileStore;

import java.io.File;
import java.util.List;


public class LessonActivity extends BaseActivity implements View.OnClickListener {
    private ActivityLessonBinding binding;
    private int index = 0 ;
    private List<Lesson> lessonList;
    private Handler handler;
    private Boolean isPlay = false;
    private int expeditionId;
    private int lessonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLessonBinding.inflate(getLayoutInflater());
        binding.backLinearLayout.setOnClickListener(this);
        binding.previousLinearLayout.setOnClickListener(this);
        binding.nextLinearLayout.setOnClickListener(this);
        binding.playPause.setOnClickListener(this);
        handler = new Handler() ;
        setContentView(binding.getRoot());

        if(intent.getExtras()!= null){
            expeditionId = intent.getIntExtra("expedition_id",0);
            lessonId = intent.getIntExtra("lesson_id",0);
            lessonList = database.lessonDao().getLessonByExpeditionId(expeditionId);
            for (int i =0 ; i < lessonList.size() ; i++) {
                if(lessonList.get(i).getId() == lessonId){
                    index = i ;
                    showImage(lessonList.get(index).getId());
                    break;
                }

            }
        }
    }


    private void showImage(int index){
        File file = new File(FileStore.getCacheFolder(context).getPath()+"/"+index + ".png");
        Glide.with(context)
                .load(file)
                .centerCrop()
                .placeholder(R.drawable.ic_application_icon)
                .into(binding.playPause);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLinearLayout:
                finish();
                break;
            case R.id.previousLinearLayout:

                if(index >= 0){
                    index --;
                    showImage(lessonList.get(index).getId());
                }
                break;
            case R.id.nextLinearLayout:
                if(index <= lessonList.size()){
                    index++;
                    showImage(lessonList.get(index).getId());
                }
                break;
            case R.id.playPause:
                if(isPlay){
                    isPlay = false;
                    handler.removeCallbacks(runnable);
                    binding.playPause.setImageResource(R.drawable.ic_button_play);
                }else{
                    isPlay = true;
                    handler.postDelayed(runnable, 0);
                    binding.playPause.setImageResource(R.drawable.ic_button_pause);
                }
                break;
        }
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(index <= lessonList.size()){
                index++;
                showImage(lessonList.get(index).getId());
            }
            handler.postDelayed(this, 5000);
        }
    };
}