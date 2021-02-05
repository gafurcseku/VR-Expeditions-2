package com.robotlab.expeditions2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.robotlab.expeditions2.dao.ExpeditionDao;
import com.robotlab.expeditions2.dao.LessonDao;
import com.robotlab.expeditions2.dao.LessonImageDao;
import com.robotlab.expeditions2.dao.PdfFileDao;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.LessonImage;
import com.robotlab.expeditions2.model.PdfFile;
import com.robotlab.expeditions2.utility.DummyLesson;
import com.robotlab.expeditions2.utility.DummyLessonDao;

@Database(entities = {Expedition.class, PdfFile.class, Lesson.class, LessonImage.class , DummyLesson.class }, version = 1 , exportSchema = false )
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExpeditionDao expeditionDao();
    public abstract PdfFileDao pdfFileDao();
    public abstract LessonDao lessonDao();
    public abstract LessonImageDao lessonImageDao();
    public abstract DummyLessonDao dummyLessonDao();
}