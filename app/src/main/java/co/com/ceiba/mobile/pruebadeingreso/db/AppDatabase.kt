package co.com.ceiba.mobile.pruebadeingreso.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.db.model.User

@Database(
        entities = [User::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}