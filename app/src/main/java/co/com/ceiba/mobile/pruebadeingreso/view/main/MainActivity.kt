package co.com.ceiba.mobile.pruebadeingreso.view.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import co.com.ceiba.mobile.pruebadeingreso.BR
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.application.MyApplication
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.db.AppDatabase
import co.com.ceiba.mobile.pruebadeingreso.db.Executor
import co.com.ceiba.mobile.pruebadeingreso.db.model.User
import co.com.ceiba.mobile.pruebadeingreso.utils.USER
import co.com.ceiba.mobile.pruebadeingreso.utils.ViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.view.main.adapters.MainRecyclerViewAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.main.interfaces.OnClickPost
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostActivity
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnClickPost, TextWatcher {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null

    private var recyclerViewAdapter = MainRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        initViewModel()
        consumeServiceUsers()
        editTextSearch.addTextChangedListener(this)
    }

    override fun goPostActivity(user: User) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(USER, user)
        startActivity(intent)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        recyclerViewAdapter.filter.filter(s)
    }

    override fun afterTextChanged(s: Editable?) {}

    fun getAdapter(): MainRecyclerViewAdapter {
        return recyclerViewAdapter
    }

    private fun setAdapterData(users: ArrayList<User>) {
        recyclerViewAdapter.setUsersData(users)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun setDataBinding() {
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.mainActivity, this)
        activityMainBinding.executePendingBindings()
    }

    private fun initViewModel() {
        (applicationContext as MyApplication).getComponent()?.inject(this)

        mainActivityViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    private fun consumeServiceUsers() {
        mainActivityViewModel?.getUsers({ errorMessage ->
            pbMainActivity.visibility = View.GONE
            errorMessage.let { Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT) }
        }, { users ->
            setAdapterData(users)
            for (user in users)
                lifecycleScope.launch {
                    if (appDatabase?.userDao()?.getUserById(user.id!!) == null) {
                        Executor.iOThread(Runnable { appDatabase?.userDao()?.setUser(user) })
                    }
                }
            pbMainActivity.visibility = View.GONE
        })
    }
}