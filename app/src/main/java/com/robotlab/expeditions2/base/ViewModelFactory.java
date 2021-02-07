package com.robotlab.expeditions2.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.robotlab.expeditions2.activity.MyExpedition.MyExpeditionViewModel;
import com.robotlab.expeditions2.activity.categorie.CategoriesViewModel;
import com.robotlab.expeditions2.activity.deviceList.StudentFragmentViewModel;
import com.robotlab.expeditions2.activity.expedition.ExpeditionViewModel;
import com.robotlab.expeditions2.activity.expeditionDetails.ExpeditionDetailViewModel;
import com.robotlab.expeditions2.activity.main.MainViewModel;
import com.robotlab.expeditions2.database.AppDatabase;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private AppDatabase database;

    public ViewModelFactory(Context context, AppDatabase database) {
        this.context = context;
        this.database = database;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(context);
        }else if(modelClass.isAssignableFrom(CategoriesViewModel.class)){
           return (T) new CategoriesViewModel(context);
        }else if(modelClass.isAssignableFrom(ExpeditionViewModel.class)){
           return (T) new ExpeditionViewModel(context,database);
       }else if(modelClass.isAssignableFrom(MyExpeditionViewModel.class)){
           return (T) new MyExpeditionViewModel(context,database);
       }else if(modelClass.isAssignableFrom(StudentFragmentViewModel.class)){
           return (T) new StudentFragmentViewModel(context);
       }else if(modelClass.isAssignableFrom(ExpeditionDetailViewModel.class)){
           return (T) new ExpeditionDetailViewModel(context,database);
       } else {
           return super.create(modelClass);
        }
    }
}
