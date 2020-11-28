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
                categories.add(new Category(1,"ALL",true));
            }else if(i==1){
                categories.add(new Category(1,"Algebra I",false));
            }else if(i==2){
                categories.add(new Category(1,"Algebra II",false));
            }else if(i==3){
                categories.add(new Category(1,"Ancient World",false));
            }else if(i==4){
                categories.add(new Category(1,"Coding",false));
            }else if(i==4){
                categories.add(new Category(1,"Computer Science",false));
            }else{
                categories.add(new Category(1,"Geography",false));
            }
        }

        return categories;
    }
}
