/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.utils.validators

import android.annotation.SuppressLint
import android.text.TextUtils
import androidx.core.util.PatternsCompat

/**
 *
 * Author: @Jeluchu
 *
 * Class to validate texts, for example you can validate
 * if it is a phone, an e-mail, an IP or a valid url
 *
 */

@SuppressLint("RestrictedApi")
object Validators {

    val String.isValidEmail: Boolean
        get() =
            !TextUtils.isEmpty(this) && PatternsCompat.AUTOLINK_EMAIL_ADDRESS.matcher(this)
                .matches()

    val String.isValidUrl: Boolean
        get() =
            !TextUtils.isEmpty(this) && PatternsCompat.AUTOLINK_WEB_URL.matcher(this).matches()

    val String.isValidIpAddress: Boolean
        get() =
            !TextUtils.isEmpty(this) && PatternsCompat.IP_ADDRESS.matcher(this).matches()

    val String.isEmail: Boolean
        get() = matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex())

    val String.isPhone: Boolean
        get() =
            matches("^1([34578])\\d{9}\$".toRegex())

    val String.isNumeric: Boolean
        get() =
            matches("^[0-9]+$".toRegex())

    val String.isAlphanumeric
        get() =
            matches("^[a-zA-Z0-9]*$".toRegex())

    val String.isAlphabetic
        get() =
            matches("^[a-zA-Z]*$".toRegex())

    fun String.isLocal() =
        !isEmptyString() && (startsWith("http://") || startsWith("https://"))

    fun CharSequence.isEmptyString(): Boolean =
        this.isEmpty() || this.toString().equals("null", true)
}