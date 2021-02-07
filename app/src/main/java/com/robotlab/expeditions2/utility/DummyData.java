package com.robotlab.expeditions2.utility;

import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.model.Category;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;
import com.robotlab.expeditions2.model.PdfFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    public static List<Expedition> getExpedition(AppDatabase database){
        List<Expedition> expeditionList = new ArrayList<>();
        for (int i = 1 ; i < 60 ; i++){
            if(i<26){
                expeditionList.add(new Expedition(i,"http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+i+".jpg","Ancient Maya-"+i,"The Maya civilization was a Mesoamerican civilization developed by the Maya …",i/2,database.dummyLessonDao().getLessonCountByExpedition(i),"6-"+i+" grade","Civilization"));
            }else{
                expeditionList.add(new Expedition(i,"https://www.hydrauliekmorreels.com/new_site/public/styles/Images/wallpapers/"+(i-26)+".jpg","Ancient Maya-"+i,"The Maya civilization was a Mesoamerican civilization developed by the Maya …",i/3,database.dummyLessonDao().getLessonCountByExpedition(i),"6-"+i+" grade","Civilization"));
            }
        }
        return expeditionList;
    }

    public static List<Lesson> getLesson(int expeditionId, AppDatabase database){
        List<Lesson> lessonList = new ArrayList<>();

        List<DummyLesson> dummyLessonList = database.dummyLessonDao().getDummyLessonByExpeditionId(expeditionId);
        for (DummyLesson dummyLesson:dummyLessonList){
            lessonList.add(new Lesson(dummyLesson.getId(),dummyLesson.getTitle(),dummyLesson.getSubtitle(),dummyLesson.getThumb(),dummyLesson.getImage(),dummyLesson.getExpeditionId()));
        }
        return  lessonList;
    }

    public static int getRandom(){
        int random = new Random().nextInt((24 - 1) + 1) + 1;
        return  random;
    }


    public static void saveDummyLesson (AppDatabase database){
        if(database.dummyLessonDao().Count()==0){
            List<DummyLesson> lessonList;
            for (int i = 1 ; i < 60 ; i++){
                lessonList = new ArrayList<>();
                int max ;
                if(i>25){
                    max = getRandom();
                }else{
                    max = i;
                }
                for (int j= 1 ; j<=max; j++){
                    lessonList.add(new DummyLesson(0,"Etymology","Ready to broadcast","http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+j+".jpg","http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+j+".jpg",i));
                }
                database.dummyLessonDao().insertDummy(lessonList);
            }
        }
    }


//    public static List<LessonImage> getImages(){
//        List<LessonImage> lessonImages = new ArrayList<>();
//        for (int i= 0 ; i<25;i++){
//            lessonImages.add(new LessonImage(i,"https://www.hydrauliekmorreels.com/new_site/public/styles/Images/wallpapers/"+i+".jpg",(i/2)));
//        }
//        return  lessonImages;
//    }

    public static List<PdfFile> getPdfList(){
        List<PdfFile> pdfFiles = new ArrayList<>();
        for (int i =1 ; i < 60 ; i++){
            pdfFiles.add(new PdfFile(i,"http://www.africau.edu/images/default/sample.pdf","Student Handout Files-"+i+" file","The_forest_of_the_world-"+i+".pdf file",(i*5.6)+"KB",i));
        }
        return pdfFiles;
    }

    public static List<LessonImage> getLessonImages(int Id){
        List<LessonImage> lessonImages = new ArrayList<>();
        for (int i =1 ; i<6 ; i++){
            lessonImages.add(new LessonImage("http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+i+".jpg",Id));
        }
        return lessonImages;
    }

}
