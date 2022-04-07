package br.ifsc.edu.crud.utils

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

object ImageUtil {

    fun getRealPathFromURI(contentResolver: ContentResolver, contentUri: Uri): String? {
        var path: String? = null
        val proj = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor: Cursor? = contentResolver.query(contentUri, proj, null, null, null)
        if (cursor?.moveToFirst() == true) {
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            path = cursor.getString(column_index)
        }
        cursor?.close()
        return path
    }
}