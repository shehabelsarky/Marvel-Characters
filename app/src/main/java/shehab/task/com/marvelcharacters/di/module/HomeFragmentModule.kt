package shehab.task.com.marvelcharacters.di.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import shehab.task.com.marvelcharacters.di.scopes.ReposScope

@Module
class HomeFragmentModule {

    @ReposScope
    @Provides
    fun providesLayoutManager(context: Context) : LinearLayoutManager {
        return LinearLayoutManager(context)
    }


}