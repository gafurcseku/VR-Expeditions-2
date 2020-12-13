package com.robotlab.expeditions2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robotlab.expeditions2.model.Lesson;
import java.util.List;

@Dao
public interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Lesson> lessons);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Lesson lesson);

    @Query("SELECT * FROM Lesson WHERE Lesson.expeditionId =:expeditionId")
    List<Lesson> getLessonByExpeditionId(int expeditionId);

//    @Query("UPDATE PdfFile SET downloadId =:downloadId , status =:status WHERE PdfFile.pdfId ==:id ")
//    void downloadStatus(int downloadId, int status, int id);
}
