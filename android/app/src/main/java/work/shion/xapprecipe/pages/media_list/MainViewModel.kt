package work.shion.xapprecipe.pages.media_list

import android.app.Application
import android.content.ContentUris
import android.provider.MediaStore
import androidx.core.content.ContentResolverCompat
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

class MainViewModel(
    application: Application,
    private val viewer: WeakReference<MainViewContract>,
) : AndroidViewModel(application), MainActionContract {

    /**
     * メディア情報の読み込み
     */
    override fun loadMedia() {
        try {
            viewer.get()?.reflectLoading(true)

            val query = ContentResolverCompat.query(
                getApplication<Application>().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                ),
                null,
                null,
                null,
                null,
            )

            val result = arrayListOf<MediaViewData>()
            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    MediaViewData(
                        contentUri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            id
                        ),
                        id = id,
                        title = cursor.getString(nameColumn)
                    ).also { result.add(it) }
                }
            }

            viewer.get()?.reflectList(result)
        } catch (ex: Throwable) {

        } finally {
            viewer.get()?.reflectLoading(false)
        }
    }

    override fun tapItem(target: MediaViewData) {
        viewer.get()?.launchMediaApp(target.contentUri)
    }
}
