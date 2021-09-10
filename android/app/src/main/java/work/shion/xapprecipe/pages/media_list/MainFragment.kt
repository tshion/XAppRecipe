package work.shion.xapprecipe.pages.media_list

import android.os.Bundle
import android.view.View
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
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
