package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.api.ApiInterface
import co.com.ceiba.mobile.pruebadeingreso.db.model.Post
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class PostActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getPostByUser(id:Int): Single<Response<ArrayList<Post>>> {
        return apiInterface.getPostByUser(id)
    }
}