package shehab.task.com.marvelcharacters.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import shehab.task.com.marvelcharacters.data.db.AppDatabase
import shehab.task.com.marvelcharacters.data.db.RepoDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideArticleDao(appDatabase: AppDatabase): RepoDao {
        return appDatabase.repoDao()
    }
}
