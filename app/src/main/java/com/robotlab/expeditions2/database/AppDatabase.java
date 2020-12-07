package com.robotlab.expeditions2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.robotlab.expeditions2.dao.ExpeditionDao;
import com.robotlab.expeditions2.dao.PdfFileDao;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.PdfFile;

@Database(entities = {Expedition.class, PdfFile.class}, version = 1 , exportSchema = false )
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExpeditionDao expeditionDao();
    public abstract PdfFileDao pdfFileDao();
}