package com.robotlab.expeditions2.utility;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DummyLessonDao {

    /// For Dummy data generate
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDummy(List<DummyLesson> dummyLessons);

    @Query("SELECT * FROM DummyLesson WHERE DummyLesson.expeditionId =:expeditionId")
    List<DummyLesson> getDummyLessonByExpeditionId(int expeditionId);

    @Query("SELECT COUNT(*) FROM DummyLesson")
    int Count();

    @Query("SELECT COUNT(*) FROM DummyLesson WHERE DummyLesson.expeditionId =:expeditionId")
    int getLessonCountByExpedition(int expeditionId);
}
