package com.example.buyshared.core

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class Function() {
    public fun getRealPathFromURI(contentUri: Uri, context: Context): String? {
        var cursor: Cursor? = null
        val column_index: Int
        var ruta: String? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
            if (cursor != null) {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                cursor.moveToFirst()
                Log.v("BuySharedLog",cursor.count.toString())
                Log.v("BuySharedLog",cursor.getColumnName(0))
                ruta = cursor.getString(column_index)
            }
            ruta
        } finally {
//            cursor?.close()
            ruta
        }
    }
}