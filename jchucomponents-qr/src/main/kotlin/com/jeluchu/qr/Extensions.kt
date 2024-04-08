package com.jeluchu.qr

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import com.jeluchu.qr.qrcode.QRCodeWriter
import com.jeluchu.qr.qrcode.decoder.ErrorCorrectionLevel

fun Context.generateQr(
    @DrawableRes icon: Int,
    key: String?,
    width: Int? = 768,
    height: Int? = 768
): Bitmap? {

    val qrCode: Bitmap? = null

    runCatching {
        val hints = HashMap<EncodeHintType, Any?>()
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M
        hints[EncodeHintType.MARGIN] = 0
        val writer = QRCodeWriter()
        return writer.encode(key, width ?: 768, height ?: 768, hints, qrCode, this, icon)
    }.getOrElse {
        it.localizedMessage
        return null
    }
}


fun Activity.generateQr(
    @DrawableRes icon: Int,
    key: String?,
    width: Int? = 768,
    height: Int? = 768
): Bitmap? {

    val qrCode: Bitmap? = null

    runCatching {
        val hints = HashMap<EncodeHintType, Any?>()
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M
        hints[EncodeHintType.MARGIN] = 0
        val writer = QRCodeWriter()
        return writer.encode(key, width ?: 768, height ?: 768, hints, qrCode, this, icon)
    }.getOrElse {
        it.localizedMessage
        return null
    }
}

internal fun String.Companion.empty() = ""