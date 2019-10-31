package shehab.task.com.marvelcharacters.data.network


import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import shehab.task.com.marvelcharacters.data.model.characters.CharacterResponse
import shehab.task.com.marvelcharacters.utils.LIMIT
import shehab.task.com.marvelcharacters.utils.OFFSET

interface ApiEndpointInterface {

    @GET(ApiUrls.REPO)
    fun getCharacters(
        @Query(LIMIT) linmit: Int,
        @Query(OFFSET) pageIndex: Int
    ): Observable<Response<CharacterResponse>>

}
