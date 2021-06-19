package co.com.ceiba.mobile.pruebadeingreso.api

import co.com.ceiba.mobile.pruebadeingreso.db.model.Post
import co.com.ceiba.mobile.pruebadeingreso.db.model.User
import co.com.ceiba.mobile.pruebadeingreso.di.rest.Endpoints.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(GET_USERS)
    fun getUsers(): Single<Response<ArrayList<User>>>

    @GET(GET_POSTS)
    fun getPosts(): Single<Response<ArrayList<Post>>>

    @GET(GET_POSTS)
    fun getPostByUser(
            @Query(GET_POST_USER) idUser: Int
    ): Single<Response<ArrayList<Post>>>
}