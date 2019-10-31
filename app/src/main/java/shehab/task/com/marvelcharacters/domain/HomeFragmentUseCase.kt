package shehab.task.com.marvelcharacters.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import shehab.task.com.marvelcharacters.data.model.characters.CharacterResponse
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.data.repository.AppRepository
import javax.inject.Inject

class HomeFragmentUseCase @Inject constructor(private var homeFragmentRepositoryImp: AppRepository) {

    val TAG = HomeFragmentUseCase::class.java.simpleName
    var errorMessage : Int? = null
    var _charactersOnlineLiveData = MutableLiveData<List<Results>>()
    var charactersOnlineLiveData : LiveData<List<Results>> = _charactersOnlineLiveData


    fun getCharacters(offset: Int) : Disposable {
        return homeFragmentRepositoryImp.getCharacters(offset,{
            Log.d(TAG, "Success Response of getting repos")
            val charactersResponse = it.body() as CharacterResponse

            _charactersOnlineLiveData.value = charactersResponse.data.results
        },{

            errorMessage = it.getApiErrorMessage(it.status)
            Log.e(TAG, "getCharacters.error() called with $it")
        })
    }

    fun getCharactersLiveData() : LiveData<List<Results>> = homeFragmentRepositoryImp.reposMutableLiveData


}