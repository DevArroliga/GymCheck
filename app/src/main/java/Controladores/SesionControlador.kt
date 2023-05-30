package Controladores

import android.content.Context
import android.content.SharedPreferences

class SesionControlador private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPref", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_CEDULA = "cedula"

        @Volatile
        private var instance: SesionControlador? = null

        fun getInstance(context: Context): SesionControlador {
            return instance ?: synchronized(this) {
                instance ?: SesionControlador(context.applicationContext).also { instance = it }
            }
        }
    }

    fun saveUserInfo(username: String, cedula: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_CEDULA, cedula)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains(KEY_USERNAME) && sharedPreferences.contains(KEY_CEDULA)
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    fun getCedula(): String? {
        return sharedPreferences.getString(KEY_CEDULA, null)
    }

    fun clearSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}