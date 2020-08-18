package com.seanpetersgichia.roomwordsample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static final int Number_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(Number_OF_THREADS);
    private static volatile WordRoomDatabase INSTANCE;


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                Word word = new Word("Quarantine");
                dao.insert(word);
                word = new Word("Kenya");
                dao.insert(word);
            });
        }

    };

    static synchronized WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();
}
