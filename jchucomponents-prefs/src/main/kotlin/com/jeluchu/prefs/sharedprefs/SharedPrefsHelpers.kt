/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.prefs.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsHelpers {
    fun getInt(key: String, defaultValue: Int): Int =
        if (isKeyExists(key)) mSharedPreferences!!.getInt(key, defaultValue) else defaultValue

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        if (isKeyExists(key)) mSharedPreferences!!.getBoolean(key, defaultValue) else defaultValue

    fun getFloat(key: String, defaultValue: Float): Float =
        if (isKeyExists(key)) mSharedPreferences!!.getFloat(key, defaultValue) else defaultValue

    fun getLong(key: String, defaultValue: Long): Long =
        if (isKeyExists(key)) mSharedPreferences!!.getLong(key, defaultValue) else defaultValue

    fun getString(key: String, defaultValue: String?): String? =
        if (isKeyExists(key)) mSharedPreferences!!.getString(key, defaultValue) else defaultValue

    fun saveInt(key: String?, value: Int) {
        val editor = mSharedPreferences!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveBoolean(key: String?, value: Boolean) {
        val editor = mSharedPreferences!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun saveFloat(key: String?, value: Float) {
        val editor = mSharedPreferences!!.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun saveLong(key: String?, value: Long) {
        val editor = mSharedPreferences!!.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun saveString(key: String?, value: String?) {
        val editor = mSharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun <T> saveObject(key: String?, `object`: T) {
        val objectString = Gson().toJson(`object`)
        val editor = mSharedPreferences!!.edit()
        editor.putString(key, objectString)
        editor.apply()
    }

    fun <T> getObject(key: String, classType: Class<T>?): T? {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)
            if (objectString != null) {
                return Gson().fromJson(objectString, classType)
            }
        }
        return null
    }

    fun <T> saveObjectsList(key: String?, objectList: List<T>?) {
        val objectString = Gson().toJson(objectList)
        val editor = mSharedPreferences!!.edit()
        editor.putString(key, objectString)
        editor.apply()
    }

    fun <T> getObjectsList(key: String): List<T> {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)
            if (objectString != null) {
                val t: ArrayList<T> =
                    Gson().fromJson(objectString, object : TypeToken<List<T>?>() {}.type)
                val finalList: MutableList<T> = ArrayList()
                for (i in 0 until t.size) {
                    finalList.add(t[i])
                }
                return finalList
            }
        }
        return emptyList()
    }

    fun <T> setDataList(tag: String?, datalist: List<T>?) {
        if (datalist.isNullOrEmpty()) return
        val gson = Gson()
        val editor = mSharedPreferences!!.edit()

        val strJson = gson.toJson(datalist)
        editor.clear()
        editor.putString(tag, strJson)
        editor.apply()
    }

    fun <T> getDataList(tag: String?): List<T>? {
        var datalist: List<T> = ArrayList()
        val strJson: String = mSharedPreferences!!.getString(tag, null) ?: return datalist
        val gson = Gson()
        datalist = gson.fromJson(strJson, object : TypeToken<List<T>?>() {}.type)
        return datalist
    }

    fun clearSession() {
        val editor = mSharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }

    fun deleteValue(key: String): Boolean {
        if (isKeyExists(key)) {
            val editor = mSharedPreferences!!.edit()
            editor.remove(key)
            editor.apply()
            return true
        }
        return false
    }

    private fun isKeyExists(key: String): Boolean {
        val map = mSharedPreferences!!.all
        return if (map.containsKey(key)) {
            true
        } else {
            Log.e("SharedPreferences", "No element founded in sharedPrefs with the key $key")
            false
        }
    }

    companion object {
        var instance: SharedPrefsHelpers? = null
            get() {
                if (field == null) {
                    validateInitialization()
                    synchronized(SharedPrefsHelpers::class.java) {
                        if (field == null) {
                            field = SharedPrefsHelpers()
                        }
                    }
                }
                return field
            }
            private set
        private var mSharedPreferences: SharedPreferences? = null

        fun initSharedPrefs(context: Context) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        private fun validateInitialization() {
            if (mSharedPreferences == null) throw SharedPrefsExceptions("SharedPreferencesHelpers must be initialized inside your application class by calling SharedPreferencesHelpers.init(getApplicationContext)")
        }
    }
}