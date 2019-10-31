package shehab.task.com.marvelcharacters.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import shehab.task.com.marvelcharacters.data.db.AppDatabase
import shehab.task.com.marvelcharacters.data.model.characters.CharacterResponse
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.data.network.ApiDisposable
import shehab.task.com.marvelcharacters.data.network.ApiEndpointInterface
import shehab.task.com.marvelcharacters.data.network.ApiError

const val LIMIT = 10
class AppRepositoryImp(
    var networkInterface: ApiEndpointInterface,
    val database: AppDatabase
) : AppRepository {


    private val TAG: String = AppRepositoryImp::class.java.simpleName
    private val disposables = CompositeDisposable()
    override var reposMutableLiveData: LiveData<List<Results>> = database.repoDao().getAllCharacters()

    override fun getCharacters(
        offset: Int,
        success: (Response<CharacterResponse>) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {

        return networkInterface.getCharacters(LIMIT,offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<Response<CharacterResponse>>(
                    {
                        success(it)
                        val reposResponse = it.body() as CharacterResponse
                        (0 until reposResponse.data.results.size).forEach { i ->
                            disposables.add(insertRepo(reposResponse.data.results[i]))
                        }
                    },
                    {
                        failure(it)
                    }
                )
            )

    }


    override fun insertRepo(character: Results): Disposable {
        return Observable
            .fromCallable { database.repoDao().insertCharacter(character) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "repo is added: subscribe: $it")
            }
    }


}