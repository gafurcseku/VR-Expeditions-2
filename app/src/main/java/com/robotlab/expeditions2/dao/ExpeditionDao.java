package com.robotlab.expeditions2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.robotlab.expeditions2.model.Expedition;

import java.util.List;

@Dao
public interface ExpeditionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Expedition> expeditions);
}
