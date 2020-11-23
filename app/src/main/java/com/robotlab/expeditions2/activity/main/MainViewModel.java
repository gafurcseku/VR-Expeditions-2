package com.robotlab.expeditions2.activity.main;

import android.content.Context;
import android.graphics.Color;

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

        binding.browseTextViewt.setTextColor(Color.parseColor("#ffffff"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#000000"));
        binding.studentTextView.setTextColor(Color.parseColor("#000000"));
    }

    public void setExpeditionClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);

        binding.browseTextViewt.setTextColor(Color.parseColor("#000000"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#ffffff"));
        binding.studentTextView.setTextColor(Color.parseColor("#000000"));
    }

    public void setStudentClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);

        binding.browseTextViewt.setTextColor(Color.parseColor("#000000"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#000000"));
        binding.studentTextView.setTextColor(Color.parseColor("#ffffff"));
    }
}
