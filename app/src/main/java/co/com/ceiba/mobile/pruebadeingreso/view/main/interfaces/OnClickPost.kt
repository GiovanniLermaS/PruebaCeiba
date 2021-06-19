package co.com.ceiba.mobile.pruebadeingreso.view.main.interfaces

import co.com.ceiba.mobile.pruebadeingreso.db.model.User

interface OnClickPost {
    fun goPostActivity(user: User)
}