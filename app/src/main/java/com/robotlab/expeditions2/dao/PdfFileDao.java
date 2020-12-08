package com.robotlab.expeditions2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robotlab.expeditions2.model.PdfFile;

import java.util.List;

@Dao
public interface PdfFileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<PdfFile> pdfFiles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PdfFile pdfFile);

    @Query("SELECT * FROM PdfFile WHERE PdfFile.pdfId ==:id")
    PdfFile getPdfFile(int id);

    @Query("UPDATE PdfFile SET downloadId =:downloadId , status =:status WHERE PdfFile.pdfId ==:id ")
    void downloadStatus(int downloadId, Boolean status, int id);

}
