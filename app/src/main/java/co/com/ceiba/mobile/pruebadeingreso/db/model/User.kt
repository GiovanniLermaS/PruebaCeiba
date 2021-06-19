package co.com.ceiba.mobile.pruebadeingreso.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class User(
        @PrimaryKey @SerializedName("id") var id: Int?,
        @SerializedName("name") var name: String?,
        @SerializedName("username") var username: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("phone") var phone: String?
) : Serializable