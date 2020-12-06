package com.robotlab.expeditions2.utility;

import com.robotlab.expeditions2.model.Category;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<Category> getCategoryDummyData(){
        List<Category> categories = new ArrayList<>();
        for (int i = 0 ; i< 20 ; i++){
            if(i==0){
                categories.add(new Category(i,"ALL",true));
            }else if(i==1){
                categories.add(new Category(i,"Algebra I",false));
            }else if(i==2){
                categories.add(new Category(i,"Algebra II",false));
            }else if(i==3){
                categories.add(new Category(i,"Ancient World",false));
            }else if(i==4){
                categories.add(new Category(i,"Coding",false));
            }else if(i==4){
                categories.add(new Category(i,"Computer Science",false));
            }else{
                categories.add(new Category(i,"Geography",false));
            }
        }
        return categories;
    }


    public static List<Expedition> getExpedition(){
        List<Expedition> expeditionList = new ArrayList<>();
        for (int i = 1 ; i < 60 ; i++){
            if(i<26){
                expeditionList.add(new Expedition(i,"http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+i+".jpg","Ancient Maya","The Maya civilization was a Mesoamerican civilization developed by the Maya …",i/2,i+" lessons","6-"+i+" grade","Civilization"));
            }else{
                expeditionList.add(new Expedition(i,"https://www.hydrauliekmorreels.com/new_site/public/styles/Images/wallpapers/"+(i-26)+".jpg","Ancient Maya","The Maya civilization was a Mesoamerican civilization developed by the Maya …",i/3,i+" lessons","6-"+i+" grade","Civilization"));
            }
        }
        return expeditionList;
    }

    public static List<Lesson> getLesson(){
        List<Lesson> lessonList = new ArrayList<>();
        for (int i= 1 ; i<26;i++){
            lessonList.add(new Lesson(i,"Etymology","Ready to broadcast","http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+i+".jpg"));
        }
        return  lessonList;
    }


    public static List<LessonImage> getImages(){
        List<LessonImage> lessonImages = new ArrayList<>();
        for (int i= 0 ; i<25;i++){
            lessonImages.add(new LessonImage(i,"https://www.hydrauliekmorreels.com/new_site/public/styles/Images/wallpapers/"+i+".jpg",i/2));
        }
        return  lessonImages;
    }

}
