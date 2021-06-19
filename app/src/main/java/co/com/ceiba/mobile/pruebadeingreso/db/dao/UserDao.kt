package co.com.ceiba.mobile.pruebadeingreso.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.db.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<User>

    @Query("DELETE FROM User WHERE id=:id")
    suspend fun deleteUserById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: User?): Long
}