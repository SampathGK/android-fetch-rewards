package com.example.fetchrewardsexercise.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fetchrewardsexercise.data.Item;
/*
Database for storing 'item' table
 */
@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
