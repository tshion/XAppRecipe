package work.shion.xapprecipe.pages.capture_image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PageCaptureImageBinding

/**
 * 画像撮影
 */
class MainFragment : Fragment(R.layout.page_capture_image) {

    private var binding: PageCaptureImageBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PageCaptureImageBinding.bind(view)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
