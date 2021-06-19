package co.com.ceiba.mobile.pruebadeingreso.di.component

import android.app.Application
import android.content.Context
import co.com.ceiba.mobile.pruebadeingreso.application.MyApplication
import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext
import co.com.ceiba.mobile.pruebadeingreso.di.DatabaseInfo
import co.com.ceiba.mobile.pruebadeingreso.di.module.ApplicationModule
import co.com.ceiba.mobile.pruebadeingreso.di.module.DatabaseModule
import co.com.ceiba.mobile.pruebadeingreso.di.module.RetrofitModule
import co.com.ceiba.mobile.pruebadeingreso.view.main.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun inject(myApplication: MyApplication?)
    fun inject(mainActivity: MainActivity?)
    fun inject(detailActivity: PostActivity?)

    @get:ApplicationContext
    val context: Context?
    val application: Application?

    @get:DatabaseInfo
    val databaseName: String?
}