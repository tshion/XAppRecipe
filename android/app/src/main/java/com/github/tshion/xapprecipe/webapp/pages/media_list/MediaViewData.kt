package com.github.tshion.xapprecipe.webapp.pages.media_list

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size

data class MediaViewData(
    val contentUri: Uri,
    val id: Long,
    val title: String,
) {

    fun thumbnail(
        appContext: Context,
        size: Size,
    ) = runCatching {
        val resolver = appContext.contentResolver ?: return@runCatching null
        if (Build.VERSION_CODES.Q <= Build.VERSION.SDK_INT) {
            resolver.loadThumbnail(
                contentUri,
                size,
                null
            )
        } else {
            MediaStore.Images.Thumbnails.getThumbnail(
                resolver,
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                null
            )
        }.let { BitmapDrawable(appContext.resources, it) }
    }.getOrNull()
}
