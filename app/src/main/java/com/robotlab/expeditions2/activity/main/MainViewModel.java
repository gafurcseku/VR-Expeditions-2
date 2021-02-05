package com.robotlab.expeditions2.activity.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.databinding.ActivityMainBinding;
import com.robotlab.expeditions2.model.Validation;

public class MainViewModel extends ViewModel {

    private Context context;

    public MainViewModel(Context context) {
        this.context = context;
    }


    public void setBrowserClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
       binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.settingLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);


        binding.browseTextViewt.setTextColor(Color.parseColor("#ffffff"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#000000"));
        binding.studentTextView.setTextColor(Color.parseColor("#000000"));
        binding.settingTextView.setTextColor(Color.parseColor("#000000"));

        Typeface typeface = ResourcesCompat.getFont(context, R.font.poppins_bold);
        binding.browseTextViewt.setTypeface(typeface);

        Typeface typefaceOther = ResourcesCompat.getFont(context, R.font.poppins_regular);
        binding.expeditionTextView.setTypeface(typefaceOther);
        binding.studentTextView.setTypeface(typefaceOther);
        binding.settingTextView.setTypeface(typefaceOther);
    }

    public void setExpeditionClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.settingLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);

        binding.browseTextViewt.setTextColor(Color.parseColor("#000000"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#ffffff"));
        binding.studentTextView.setTextColor(Color.parseColor("#000000"));
        binding.settingTextView.setTextColor(Color.parseColor("#000000"));

        Typeface typeface = ResourcesCompat.getFont(context, R.font.poppins_bold);
        binding.expeditionTextView.setTypeface(typeface);

        Typeface typefaceOther = ResourcesCompat.getFont(context, R.font.poppins_regular);
        binding.browseTextViewt.setTypeface(typefaceOther);
        binding.studentTextView.setTypeface(typefaceOther);
        binding.settingTextView.setTypeface(typefaceOther);
    }

    public void setStudentClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);
        binding.settingLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);

        binding.browseTextViewt.setTextColor(Color.parseColor("#000000"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#000000"));
        binding.studentTextView.setTextColor(Color.parseColor("#ffffff"));
        binding.settingTextView.setTextColor(Color.parseColor("#000000"));

        Typeface typeface = ResourcesCompat.getFont(context, R.font.poppins_bold);
        binding.studentTextView.setTypeface(typeface);

        Typeface typefaceOther = ResourcesCompat.getFont(context, R.font.poppins_regular);
        binding.browseTextViewt.setTypeface(typefaceOther);
        binding.expeditionTextView.setTypeface(typefaceOther);
        binding.settingTextView.setTypeface(typefaceOther);
    }


    public void setSettingClick(ActivityMainBinding binding){
        binding.browseLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.expeditionLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.studentLinearLayout.setBackgroundResource(R.drawable.main_menu_normal);
        binding.settingLinearLayout.setBackgroundResource(R.drawable.main_menu_selected);

        binding.browseTextViewt.setTextColor(Color.parseColor("#000000"));
        binding.expeditionTextView.setTextColor(Color.parseColor("#000000"));
        binding.studentTextView.setTextColor(Color.parseColor("#000000"));
        binding.settingTextView.setTextColor(Color.parseColor("#ffffff"));

        Typeface typeface = ResourcesCompat.getFont(context, R.font.poppins_bold);
        binding.settingTextView.setTypeface(typeface);

        Typeface typefaceOther = ResourcesCompat.getFont(context, R.font.poppins_regular);
        binding.studentTextView.setTypeface(typefaceOther);
        binding.browseTextViewt.setTypeface(typefaceOther);
        binding.expeditionTextView.setTypeface(typefaceOther);
    }

    private final MutableLiveData<Validation> searchLiveData = new MutableLiveData<Validation>();

    public void searchText(String text){
        if(text.isEmpty()){
            searchLiveData.setValue(new Validation(false,context.getResources().getString(R.string.seachEmpty)));
        }else{
            searchLiveData.setValue(new Validation(true,null,text));
        }
    }

    public MutableLiveData<Validation> getSearchLiveData(){
        return searchLiveData;
    }

}
