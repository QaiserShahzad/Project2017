package com.example.qaiserpasha.project2017.SQLite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Qaiser on 1/22/2018.
 */
@Dao
public interface ClothItemDAO {
    @Insert
    void Insert(ClothItem... items);

    @Query("Select * from ClothItem;")
    List<ClothItem> getAllItemFromBox();
    @Query("Delete from ClothItem where clotheItemID=:id")
    void deleteAll(int id);
}
