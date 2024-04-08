/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ktx.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

val gson: Gson by lazy { GsonBuilder().disableHtmlEscaping().create() }

/** Convert a String to an Object **/
inline fun <reified T> String.toObject(): T {
    val type = typeToken<T>()
    return gson.fromJson(this, type)
}

inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

/** Convert a Map to an Object **/
inline fun <reified T> Map<String, Any>.toObject(): T = convert()

/** Convert an object to a Map **/
fun <T> T.toMap(): Map<String, Any> = convert()

/** Convert an object of type T to type R **/
inline fun <T, reified R> T.convert(): R = gson.toJson(this).toObject()

/**
 * Helps to get Map, List, Set or other generic type from Json using Gson.
 */
inline fun <reified T> Gson.fromJson(json: String?): T? = try {
    fromJson<T>(json, object : TypeToken<T>() {}.type)
} catch (e: Exception) {
    e.printStackTrace()
    null
}

inline fun <reified T : Any> Gson.fromJsonList(json: String?): List<T>? = try {
    fromJson<List<T>>(json, object : TypeToken<List<T>>() {}.type)
} catch (e: Exception) {
    e.printStackTrace()
    null
}