package com.robotlab.expeditions2.activity.expedition;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.utility.DummyData;

import java.util.List;
import java.util.stream.Collectors;

public class ExpeditionViewModel extends ViewModel {
    private Context context;

    public MutableLiveData<List<Expedition>> expeditionLiveData;

    public ExpeditionViewModel(Context context) {
        this.context = context;
    }

    public void getExpeditions(int categoryId){
        expeditionLiveData = new MutableLiveData<>();
        List expeditions = DummyData.getExpedition().stream().filter(e -> e.getCategory() == categoryId).collect(Collectors.toList());
        expeditionLiveData.postValue(expeditions);
    }

}
