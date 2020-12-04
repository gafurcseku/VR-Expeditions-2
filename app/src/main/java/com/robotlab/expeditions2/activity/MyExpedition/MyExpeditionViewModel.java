package com.robotlab.expeditions2.activity.MyExpedition;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.model.Expedition;

import java.util.ArrayList;
import java.util.List;

public class MyExpeditionViewModel extends ViewModel {
    private Context context;
    private AppDatabase database;

    public MutableLiveData<List<Expedition>> expeditionLiveData;

    public MyExpeditionViewModel(Context context,AppDatabase database) {
        this.context = context;
        this.database=database;
    }

    public void getExpeditions(){
        expeditionLiveData = new MutableLiveData<>();
        expeditionLiveData.postValue(database.expeditionDao().getAllExpedition());
    }
}
