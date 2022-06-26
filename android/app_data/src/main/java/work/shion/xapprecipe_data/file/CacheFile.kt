package com.github.tshion.xapprecipe.webapp_data.file

import android.content.Context
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.ref.WeakReference

class CacheFile(
    appContext: WeakReference<Context>,
) : CacheFileContract {

    private val directory = appContext.get()?.let {
        ContextCompat.getExternalCacheDirs(it)
    }?.firstOrNull()


    override suspend fun load(name: String) = withContext(Dispatchers.IO) {
        directory?.let { File(it, name) }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun save(name: String, stream: InputStream) {
        withContext(Dispatchers.IO) {
            val outputStream = load(name)
                ?.let { FileOutputStream(it, false) }
                ?: return@withContext
            stream.use { it.copyTo(outputStream) }
        }
    }
}
