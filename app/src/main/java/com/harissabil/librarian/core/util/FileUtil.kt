package com.harissabil.librarian.core.util

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

val timeStamp: String = SimpleDateFormat(
    "dd-MMM-yyyy",
    Locale.US
).format(System.currentTimeMillis())

fun createTempFile(image: Bitmap, context: Context): String? {
    var savedImagePath: String? = null
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    var success = true
    if (storageDir != null) {
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
    }
    if (success) {
        val imageFile = File.createTempFile(timeStamp, ".jpg", storageDir)
        imageFile.deleteOnExit()
        savedImagePath = imageFile.absolutePath

        if (imageFile.exists()) {
            imageFile.delete()
        }

        try {
            val fOut: OutputStream = FileOutputStream(imageFile)
            image.compress(Bitmap.CompressFormat.JPEG, 75, fOut)
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    Log.i("IMAGE_PATH", savedImagePath.toString())
    return savedImagePath
}