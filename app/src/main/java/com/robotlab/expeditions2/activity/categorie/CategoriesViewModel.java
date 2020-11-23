package com.robotlab.expeditions2.activity.categorie;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.model.Category;

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
        categoryLiveData.postValue(getCategoryDummyData());
    }

    private List<Category> getCategoryDummyData(){
        List<Category> categories = new ArrayList<>();
        for (int i = 0 ; i< 20 ; i++){
            if(i==0){
                categories.add(new Category(1,"Ancient World",true));
            }else{
                categories.add(new Category(1,"Ancient World",false));
            }
        }

        return categories;
    }
}
