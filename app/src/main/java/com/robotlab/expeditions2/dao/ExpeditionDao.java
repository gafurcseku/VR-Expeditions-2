package com.robotlab.expeditions2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robotlab.expeditions2.model.Expedition;

import java.util.List;

@Dao
public interface ExpeditionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Expedition> expeditions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Expedition expeditions);


    @Query("SELECT * FROM Expedition")
    public List<Expedition> getAllExpedition();

    @Query("SELECT EXISTS(SELECT * FROM Expedition WHERE Expedition._id =:Id)")
    Boolean isExists(int Id);

}
