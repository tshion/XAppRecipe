package work.shion.xapprecipe.pages.pdf_viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import work.shion.xapprecipe.databinding.PagesPdfViewerBinding

/**
 * PDF 表示
 */
class MainFragment : Fragment() {

    private var binding: PagesPdfViewerBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesPdfViewerBinding.inflate(inflater, container, false)
        return binding?.root
    }
}
