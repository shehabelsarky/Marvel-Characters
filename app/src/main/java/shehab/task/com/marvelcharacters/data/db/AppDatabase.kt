package shehab.task.com.marvelcharacters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import shehab.task.com.marvelcharacters.data.model.characters.Results


@Database(entities = [Results::class] , version = AppDatabase.VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    companion object {
        const val DB_NAME = "MARVEL_CHARACTERS"
        const val VERSION = 2
    }

    abstract fun repoDao(): RepoDao
}