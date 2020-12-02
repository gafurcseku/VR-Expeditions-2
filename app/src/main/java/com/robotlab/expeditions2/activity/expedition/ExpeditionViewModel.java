package com.robotlab.expeditions2.activity.expedition;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.model.Expedition;

import java.util.ArrayList;
import java.util.List;

public class ExpeditionViewModel extends ViewModel {
    private Context context;

    public MutableLiveData<List<Expedition>> expeditionLiveData;

    public ExpeditionViewModel(Context context) {
        this.context = context;
    }

    public void getExpeditions(){
        expeditionLiveData = new MutableLiveData<>();
        expeditionLiveData.postValue(getDummyData());
    }

    private List<Expedition> getDummyData(){
        List<Expedition> expeditionList = new ArrayList<>();
        for (int i = 0 ; i < 30 ; i++){
            expeditionList.add(new Expedition(0,"https://cdn.slashgear.com/wp-content/uploads/2020/05/deadly-wallpaper-1280x720.jpg","Ancient Maya","The Maya civilization was a Mesoamerican civilization developed by the Maya …","11 lessons","6-10 grade","Civilization"));
        }
        return expeditionList;
    }
}
