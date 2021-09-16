package work.shion.xapprecipe.pages.capture_video

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PageCaptureVideoBinding

/**
 * ビデオ撮影
 */
class MainFragment : Fragment(R.layout.page_capture_video) {

    private var binding: PageCaptureVideoBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PageCaptureVideoBinding.bind(view)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
