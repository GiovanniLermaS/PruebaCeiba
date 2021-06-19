package co.com.ceiba.mobile.pruebadeingreso.viewmodel

import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.db.model.User
import co.com.ceiba.mobile.pruebadeingreso.repository.MainActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mainActivityRepository: MainActivityRepository) : ViewModel() {

    fun getUsers(callbackError: (String) -> Unit, callbackSuccess: (ArrayList<User>) -> Unit) {
        mainActivityRepository.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Response<ArrayList<User>>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onError(e: Throwable) {
                        e.message?.let { callbackError(it) }
                    }

                    override fun onSuccess(users: Response<ArrayList<User>>) {
                        callbackSuccess(users.body()!!)
                    }
                })
    }
}
