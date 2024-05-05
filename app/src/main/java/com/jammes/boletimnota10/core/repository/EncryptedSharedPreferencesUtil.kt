package com.jammes.boletimnota10.core.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.EncryptedSharedPreferences

object EncryptedSharedPreferencesUtil {
    private const val SHARED_PREF_NAME = "encrypted_shared_pref"

    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            SHARED_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveSessionToken(context: Context, sessionToken: String) {
        val sharedPreferences = getEncryptedSharedPreferences(context)
        sharedPreferences.edit().putString("session_token", sessionToken).apply()
    }

    fun getSessionToken(context: Context): String? {
        val sharedPreferences = getEncryptedSharedPreferences(context)
        return sharedPreferences.getString("session_token", null)
    }

    fun clearSessionToken(context: Context) {
        val sharedPreferences = getEncryptedSharedPreferences(context)
        sharedPreferences.edit().remove("session_token").apply()
    }
}
