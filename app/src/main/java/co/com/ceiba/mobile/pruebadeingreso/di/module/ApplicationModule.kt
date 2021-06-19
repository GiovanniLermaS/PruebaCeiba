package co.com.ceiba.mobile.pruebadeingreso.di.module

import android.app.Application
import android.content.Context
import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(@ApplicationContext val application: Application) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }
}