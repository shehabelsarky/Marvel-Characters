package shehab.task.com.marvelcharacters.ui.fragment.home

import androidx.lifecycle.LiveData
import shehab.task.com.marvelcharacters.domain.HomeFragmentUseCase
import shehab.task.com.marvelcharacters.ui.fragment.base.BaseViewModel
import shehab.task.com.marvelcharacters.utils.SnackbarMessage
import shehab.task.com.marvelcharacters.data.model.characters.Results
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
    private var homeFragmentUseCase: HomeFragmentUseCase
) : BaseViewModel() {

    private val mSnackbarText = SnackbarMessage()


    init {
        mSnackbarText.value = homeFragmentUseCase.errorMessage
    }

    fun getCharacters(offset: Int) {
        compositeDisposable.add(homeFragmentUseCase.getCharacters(offset))
    }

    fun getCharactersLiveData(): LiveData<List<Results>> = homeFragmentUseCase.getCharactersLiveData()
    fun getCharacterOnlineLiveData(): LiveData<List<Results>> = homeFragmentUseCase.charactersOnlineLiveData

    internal fun getSnackbarMessage(): SnackbarMessage {
        return mSnackbarText
    }
}