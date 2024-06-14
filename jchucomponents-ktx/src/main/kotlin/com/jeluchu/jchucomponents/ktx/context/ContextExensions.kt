/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ktx.context

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.Browser
import android.provider.MediaStore
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import com.google.gson.Gson
import com.jeluchu.jchucomponents.ktx.R
import com.jeluchu.jchucomponents.ktx.constants.INTENT_TYPE_IMG_PNG
import com.jeluchu.jchucomponents.ktx.context.broadcast.ShareBroadcastReceiver
import com.jeluchu.jchucomponents.ktx.packageutils.buildIsMarshmallowAndUp
import com.jeluchu.jchucomponents.ktx.packageutils.buildIsNougatAndUp
import com.jeluchu.jchucomponents.ktx.packageutils.buildIsQAndUp
import java.io.File
import java.util.Locale

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


private fun intentView(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url))

/**
 *
 * [Context] Extension to get whether Talkback
 * is activated or not
 *
 */
val Context.isTalkBackEnabled: Boolean
    get() {
        val am: AccessibilityManager? = getSystemService()
        val isAccessibilityEnabled: Boolean = am?.isEnabled ?: false
        val isExploreByTouchEnabled: Boolean = am?.isTouchExplorationEnabled ?: false
        return isAccessibilityEnabled && isExploreByTouchEnabled
    }

/**
 *
 * [Context] Extension to obtain Locale data from
 * the device and thus be able to obtain for example the country
 *
 * @see android.content.res.Configuration
 *
 */
@Suppress("DEPRECATION")
val Context.locale: Locale
    get() = if (buildIsNougatAndUp) resources.configuration.locales[0]
    else resources.configuration.locale

/**
 *
 * [Context] Extension to display a Toast with a short duration
 * period in which a customized toast will be displayed
 *
 * @param message [String] the text you want to display as the [Toast] message
 *
 * @see android.widget.Toast
 * @see android.widget.Toast.LENGTH_SHORT
 *
 */
fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 *
 * [Context] Extension to display a Toast with a long duration
 * period in which a customized toast will be displayed
 *
 * @param message [String] the text you want to display as the [Toast] message
 *
 * @see android.widget.Toast
 * @see android.widget.Toast.LENGTH_LONG
 *
 */
fun Context.longToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

/**
 *
 * [Context] Extension to open any url from Custom Chrome Tab or alternative WebView
 *
 * @param url [String] will be the web page we want to open
 * @param colorBar [Int] the color that will be displayed on the Web Tab, by default is
 * [R.color.browserActionsBgGrey]
 *
 */
@SuppressLint("UnspecifiedImmutableFlag")
fun Context.openInCustomTab(
    url: String,
    @ColorRes colorBar: Int = R.color.browserActionsBgGrey
) {

    runCatching {

        val share: PendingIntent = if (buildIsMarshmallowAndUp)
            PendingIntent.getBroadcast(
                this, 0, Intent(
                    this,
                    ShareBroadcastReceiver::class.java
                ), PendingIntent.FLAG_IMMUTABLE
            ) else
            PendingIntent.getBroadcast(
                this, 0, Intent(
                    this,
                    ShareBroadcastReceiver::class.java
                ), PendingIntent.FLAG_UPDATE_CURRENT
            )

        val customTabColorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(this, colorBar))
            .build()

        CustomTabsIntent.Builder().apply {
            setDefaultColorSchemeParams(customTabColorSchemeParams)
            setShowTitle(true)
            setActionButton(
                BitmapFactory.decodeResource(resources, R.drawable.ic_btn_share),
                "Compartir",
                share,
                true
            )
        }.build().launchUrl(this, Uri.parse(url))

    }.getOrElse {

        val intent = intentView(url).apply {
            putExtra(Browser.EXTRA_CREATE_NEW_TAB, true)
            putExtra(Browser.EXTRA_APPLICATION_ID, packageName)
        }

        startActivity(intent)

    }

}

/**
 *
 * [Context] Extension to delete all cache data generated
 * by the application during use
 *
 */
fun Context.deleteCache() {
    runCatching {
        val dir: File = this.cacheDir
        deleteDir(dir)
    }.getOrElse {
        it.printStackTrace()
    }
}

private fun deleteDir(dir: File?): Boolean {
    return if (dir != null && dir.isDirectory) {
        val children = dir.list()
        if (children != null) {
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) return false
            }
        }
        dir.delete()
    } else if (dir != null && dir.isFile) dir.delete()
    else false
}