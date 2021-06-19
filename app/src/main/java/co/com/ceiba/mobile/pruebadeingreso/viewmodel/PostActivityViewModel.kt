package co.com.ceiba.mobile.pruebadeingreso.viewmodel

import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.db.model.Post
import co.com.ceiba.mobile.pruebadeingreso.repository.PostActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class PostActivityViewModel @Inject constructor(private val postActivityRepository: PostActivityRepository) :
        ViewModel() {

    fun getPostByUser(id: Int, callbackError: (String) -> Unit, callbackSuccess: (ArrayList<Post>) -> Unit) {
        postActivityRepository.getPostByUser(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Response<ArrayList<Post>>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onError(e: Throwable) {
                        e.message?.let { callbackError(it) }
                    }

                    override fun onSuccess(posts: Response<ArrayList<Post>>) {
                        callbackSuccess(posts.body()!!)
                    }
                })
    }
}
