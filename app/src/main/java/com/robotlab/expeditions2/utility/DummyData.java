package com.robotlab.expeditions2.utility;

import com.robotlab.expeditions2.model.Category;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;
import com.robotlab.expeditions2.model.PdfFile;

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

    public static List<Lesson> getLesson(int number){
        int loop = number * 3 ;
        if(loop > 12){
            loop = 12;
        }
        List<Lesson> lessonList = new ArrayList<>();
        for (int i= 1 ; i<loop; i++){
            lessonList.add(new Lesson(i,"Etymology","Ready to broadcast","http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+i+".jpg",number));
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

    public static List<PdfFile> getPdfList(){
        List<PdfFile> pdfFiles = new ArrayList<>();
        for (int i =1 ; i < 20 ; i++){
            pdfFiles.add(new PdfFile(i,"http://www.africau.edu/images/default/sample.pdf","This is a small demonstration--"+i+" file","This is a small demonstration "+i+".pdf file",(i*5.6)+"KB",i));
        }
        return pdfFiles;
    }

    public static List<LessonImage> getLessonImages(int Id){
        List<LessonImage> lessonImages = new ArrayList<>();
        for (int i =1 ; i<6 ; i++){
            lessonImages.add(new LessonImage((i*Id),"http://auditoriumpalma.com/skin/default/congresos/images/bg/bg_"+i+".jpg",Id));
        }
        return lessonImages;
    }

}
