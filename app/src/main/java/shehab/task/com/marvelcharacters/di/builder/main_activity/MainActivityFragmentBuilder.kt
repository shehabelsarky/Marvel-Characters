package shehab.task.com.marvelcharacters.di.builder.main_activity

import shehab.task.com.marvelcharacters.di.module.HomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import shehab.task.com.marvelcharacters.ui.fragment.home.HomeFragment
import shehab.task.com.marvelcharacters.di.scopes.ReposScope
import shehab.task.com.marvelcharacters.ui.fragment.details.DetailsFragment
import shehab.task.com.marvelcharacters.ui.fragment.search.SearchFragment


@Module
abstract class MainActivityFragmentBuilder{

    @ReposScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragment(): HomeFragment

    @ReposScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideDetailsFragment(): DetailsFragment

    @ReposScope
    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment

}