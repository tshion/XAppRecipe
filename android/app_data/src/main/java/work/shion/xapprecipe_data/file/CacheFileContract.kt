package work.shion.xapprecipe_data.file

import java.io.File
import java.io.InputStream

interface CacheFileContract {

    suspend fun load(
        name: String,
    ): File?

    suspend fun save(
        name: String,
        stream: InputStream,
    )
}
