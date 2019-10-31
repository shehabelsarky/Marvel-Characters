package shehab.task.com.marvelcharacters.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import retrofit2.Response
import shehab.task.com.marvelcharacters.data.model.characters.CharacterResponse
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.data.network.ApiError

interface AppRepository {

    var reposMutableLiveData: LiveData<List<Results>>

    fun getCharacters(
        offset: Int,
        success: (Response<CharacterResponse>) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun insertRepo(character: Results): Disposable

}