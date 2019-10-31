package shehab.task.com.marvelcharacters.di.builder


import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shehab.task.com.marvelcharacters.di.qualifier.ViewModelKey
import shehab.task.com.marvelcharacters.ui.fragment.home.HomeFragmentViewModel

@Module
abstract class AppViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindMyOrdersViewModel(myOrdersViewModel: HomeFragmentViewModel): ViewModel
}
