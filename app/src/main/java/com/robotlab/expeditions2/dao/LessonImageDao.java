package com.robotlab.expeditions2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robotlab.expeditions2.model.LessonImage;

import java.util.List;

@Dao
public interface LessonImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<LessonImage> lessonImages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LessonImage lessonImages);

    @Query("UPDATE LessonImage SET downloadId =:downloadId , status =:status WHERE LessonImage.Id ==:id ")
    void downloadStatus(int downloadId, int status, int id);
}
