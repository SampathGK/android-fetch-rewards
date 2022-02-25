package com.example.fetchrewardsexercise.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.fetchrewardsexercise.data.Item;
import java.util.LinkedList;
import java.util.List;

/*
Contract interface for query methods on the database containing 'item' table.
 */
@Dao
public interface ItemDao {

    @Query("SELECT * FROM item WHERE name IS NOT NULL AND name <> '' ORDER BY listID, nameKey")
    List<Item> getAll();

    @Query("SELECT COUNT(*) FROM item")
    int count();

    @Query("DELETE FROM item")
    void deleteAll();

    @Insert
    void insertAll(List<Item> items);

    @Delete
    void delete(Item user);
}
