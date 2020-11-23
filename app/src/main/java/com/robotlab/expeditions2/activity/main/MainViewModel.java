package com.robotlab.expeditions2.activity.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.databinding.ActivityMainBinding;

public class MainViewModel extends ViewModel {
    private Context context;

    public MainViewModel(Context context) {
        this.context = context;
    }

    public void setBrowserClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
    }

    public void setExpeditionClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
    }

    public void setStudentClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
    }
}
