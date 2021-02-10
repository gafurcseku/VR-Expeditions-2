package com.robotlab.expeditions2.activity.MyExpedition;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.utility.DummyData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyExpeditionViewModel extends ViewModel {
    private Context context;
    private AppDatabase database;

    public MutableLiveData<List<Expedition>> expeditionLiveData;

    public MyExpeditionViewModel(Context context,AppDatabase database) {
        this.context = context;
        this.database=database;
        expeditionLiveData = new MutableLiveData<>();
    }

    /**
     * This function use to get all save expedition from database
     *
     * @param categoryId A int, it accept select category id
    */

    public void getExpeditions(int categoryId){

        List expeditions = null;
        if(categoryId == 0){
            expeditions = database.expeditionDao().getAllExpedition();
        }else {
            expeditions = database.expeditionDao().getAllExpedition().stream().filter(e -> e.getCategory() == categoryId).collect(Collectors.toList());
        }
        expeditionLiveData.setValue(expeditions);
    }

    public String getCategoryName(int categoryId){
        return  DummyData.getCategoryDummyData().get(categoryId).getName();
    }
}
