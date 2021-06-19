package co.com.ceiba.mobile.pruebadeingreso.di.module

import android.content.Context
import androidx.room.Room
import co.com.ceiba.mobile.pruebadeingreso.db.AppDatabase
import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext
import co.com.ceiba.mobile.pruebadeingreso.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(@ApplicationContext val context: Context) {
    @DatabaseInfo
    private val dbName = "db_app"

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                dbName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return dbName
    }
}