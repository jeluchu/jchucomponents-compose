/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ktx.context

import android.Manifest
import android.app.NotificationManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.jeluchu.jchucomponents.ktx.constants.INTENT_TYPE_IMG_PNG
import com.jeluchu.jchucomponents.ktx.packageutils.buildIsQAndUp

/** ---- PERMISSIONS --------------------------------------------------------------------------- **/

fun Context.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

inline val Context.checkPermisionStorage: Boolean
    get() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_DENIED


/** ---- CLIPBOARD ----------------------------------------------------------------------------- **/

fun Context.addToClipboard(str: CharSequence?) {
    com.jeluchu.jchucomponents.ktx.coroutines.noCrash {
        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", str)
        clipboard.setPrimaryClip(clip)
    }
}


/** ---- PRIVATE METHODS ----------------------------------------------------------------------- **/

val Context.layoutInflater
    get() = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)

inline val Context.notificationManager: NotificationManager
    get() = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

/** ---- SAVE IMAGES --------------------------------------------------------------------------- **/

fun Context.saveBitmap(
    bitmap: Bitmap,
    filename: String = System.currentTimeMillis().toString()
): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
        put(MediaStore.MediaColumns.MIME_TYPE, INTENT_TYPE_IMG_PNG)
        if (buildIsQAndUp) put(
            MediaStore.MediaColumns.RELATIVE_PATH,
            Environment.DIRECTORY_PICTURES
        )
    }

    with(contentResolver) {
        val imageUri: Uri? = insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        return imageUri?.also { uri ->
            openOutputStream(uri)?.let { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.close()
            }
        }
    }
}



fun Context.openPlaystoreSubscriptions(
    productId: String,
    packageName: String
) {
    runCatching {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/account/subscriptions?sku=$productId&package=$packageName")
            )
        )
    }.getOrElse {
        it.printStackTrace()
    }
}

fun <T> Context.getJsonDataFromAsset(fileName: String, typeClass: Class<T>): T? =
    kotlin.runCatching {
        Gson().fromJson(
            this.assets.open(fileName).bufferedReader().use { it.readText() },
            typeClass
        )
    }.onFailure { error -> Log.e("ERROR:", error.message.orEmpty()) }.getOrNull()


fun Context.isSimCardReady(): Boolean {
    val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return telephonyManager.simState == TelephonyManager.SIM_STATE_READY
}

fun Context.isPackageInstalled(packageName: String): Boolean =
    runCatching {
        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        true
    }.getOrElse {
        false
    }