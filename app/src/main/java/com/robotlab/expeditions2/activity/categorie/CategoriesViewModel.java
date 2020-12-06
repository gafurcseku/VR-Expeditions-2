package com.robotlab.expeditions2.activity.categorie;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.model.Category;
import com.robotlab.expeditions2.utility.DummyData;

import java.util.ArrayList;
import java.util.List;

public class CategoriesViewModel extends ViewModel {
    private Context context;
    public MutableLiveData<List<Category>> categoryLiveData;

    public CategoriesViewModel(Context context) {
        this.context = context;
    }

    public void getCategory(){
        categoryLiveData = new MutableLiveData<>();
        categoryLiveData.postValue(DummyData.getCategoryDummyData());
    }

}
