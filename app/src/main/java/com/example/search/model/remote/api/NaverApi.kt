package com.example.search.model.remote.api

import com.example.search.model.vo.dto.NaverMovieResponse
import io.reactivex.Single
import retrofit2.http.*

// PATH: URL부분 중 일부의 경로가 필요에 따라 동적으로 바인딩 되어야 하는 경우
// @Field: 서버에 데이터를 보낼때 Request 데이터를 하나씩 지정해서 보내려면 사용한다
//ContentType을 form-encoded로 지정하여 데이터를 전송해야 하므로 @FormUrlEncoded 어노테이션을 지정해줘야 한다. (안하면 오류 발생)
//Call<JsonArray>의 경우는 해당 코드를 추적하여 들여다 보면
// Retrofit이 Call<T> 형태의 Generic Type을 매개변수로 받는 Callback Interface인 것을 알 수 있습니다.
interface NaverApi {

    //Getting a resource
    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Single<NaverMovieResponse>


}