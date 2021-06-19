package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.api.ApiInterface
import co.com.ceiba.mobile.pruebadeingreso.db.model.User
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getUsers(): Single<Response<ArrayList<User>>> {
        return apiInterface.getUsers()
    }
}