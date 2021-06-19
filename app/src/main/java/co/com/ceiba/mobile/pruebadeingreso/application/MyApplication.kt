package co.com.ceiba.mobile.pruebadeingreso.application

import android.app.Application
import co.com.ceiba.mobile.pruebadeingreso.di.component.ApplicationComponent
import co.com.ceiba.mobile.pruebadeingreso.di.component.DaggerApplicationComponent
import co.com.ceiba.mobile.pruebadeingreso.di.module.ApplicationModule
import co.com.ceiba.mobile.pruebadeingreso.di.module.DatabaseModule
import co.com.ceiba.mobile.pruebadeingreso.di.module.RetrofitModule

class MyApplication : Application() {

    var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .retrofitModule(RetrofitModule(this))
                .databaseModule(DatabaseModule(this))
                .build()
        mApplicationComponent?.inject(this)
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}