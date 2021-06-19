package co.com.ceiba.mobile.pruebadeingreso.view.post

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import co.com.ceiba.mobile.pruebadeingreso.BR
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.application.MyApplication
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.db.model.Post
import co.com.ceiba.mobile.pruebadeingreso.db.model.User
import co.com.ceiba.mobile.pruebadeingreso.utils.USER
import co.com.ceiba.mobile.pruebadeingreso.utils.ViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.view.post.adapters.PostRecyclerViewAdapter
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : AppCompatActivity() {

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var postActivityViewModel: PostActivityViewModel? = null

    private var recyclerViewAdapter = PostRecyclerViewAdapter()

    val user by lazy { intent.getSerializableExtra(USER) as User }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setDataBinding()
        initViewModel()
        consumeServicePosts()
    }

    fun getAdapter(): PostRecyclerViewAdapter {
        return recyclerViewAdapter
    }

    private fun setAdapterData(posts: ArrayList<Post>) {
        recyclerViewAdapter.setPostsData(posts)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun setDataBinding() {
        val activityPostBinding = DataBindingUtil.setContentView<ActivityPostBinding>(this, R.layout.activity_post)
        activityPostBinding.setVariable(BR.postActivity, this)
        activityPostBinding.executePendingBindings()
    }

    private fun initViewModel() {
        (applicationContext as MyApplication).getComponent()?.inject(this)

        postActivityViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(PostActivityViewModel::class.java)
    }

    private fun consumeServicePosts() {
        postActivityViewModel?.getPostByUser(user.id!!, { errorMessage ->
            llProgress.visibility = View.GONE
            errorMessage.let { Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT) }
        }, { posts ->
            setAdapterData(posts)
            llProgress.visibility = View.GONE
        })
    }
}