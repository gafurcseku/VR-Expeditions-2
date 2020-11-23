package com.robotlab.expeditions2.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.robotlab.expeditions2.activity.categorie.CategoriesViewModel;
import com.robotlab.expeditions2.activity.expedition.ExpeditionViewModel;
import com.robotlab.expeditions2.activity.main.MainViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(context);
        }else if(modelClass.isAssignableFrom(CategoriesViewModel.class)){
           return (T) new CategoriesViewModel(context);
        }else if(modelClass.isAssignableFrom(ExpeditionViewModel.class)){
           return (T) new ExpeditionViewModel(context);
       }else {
           return super.create(modelClass);
        }
    }
}
