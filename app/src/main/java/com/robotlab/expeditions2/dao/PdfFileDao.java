package com.robotlab.expeditions2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.robotlab.expeditions2.model.PdfFile;

import java.util.List;

@Dao
public interface PdfFileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<PdfFile> pdfFiles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PdfFile pdfFile);


}
