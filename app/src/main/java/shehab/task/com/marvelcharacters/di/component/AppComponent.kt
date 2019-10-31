package shehab.task.com.marvelcharacters.di.component

import android.app.Application
import shehab.task.com.marvelcharacters.di.module.ContextModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import shehab.task.com.marvelcharacters.core.AppController
import shehab.task.com.marvelcharacters.di.builder.ActivityBuilder
import shehab.task.com.marvelcharacters.di.builder.ViewModelFactoryBuilder
import shehab.task.com.marvelcharacters.di.module.DatabaseModule
import shehab.task.com.marvelcharacters.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        ContextModule::class,
        ViewModelFactoryBuilder::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<AppController> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}