package work.shion.xapprecipe.pages.media_list

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContentResolverCompat
import androidx.fragment.app.Fragment
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PageMediaListBinding

/**
 * メディアコンテンツ一覧の表示
 */
class MainFragment : Fragment(R.layout.page_media_list) {

    private var binding: PageMediaListBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageMediaListBinding.bind(view)

        val query = ContentResolverCompat.query(
            requireContext().contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Images.Media._ID,
            ),
            null,
            null,
            null,
            null,
        )

        val count = query.count
        print(count)

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                print(contentUri)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
