package com.seanpetersgichia.roomwordsample;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {


}
