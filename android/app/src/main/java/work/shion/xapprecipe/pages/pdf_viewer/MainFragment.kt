package work.shion.xapprecipe.pages.pdf_viewer

import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import work.shion.xapprecipe.databinding.PagesPdfViewerBinding
import work.shion.xapprecipe.getProvider
import java.lang.ref.WeakReference
import kotlin.math.ceil

/**
 * PDF 表示
 */
class MainFragment : Fragment(), MainViewContract {

    private var binding: PagesPdfViewerBinding? = null
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            showPdfUseCase = activity?.getProvider()!!.showPdfUseCase,
            viewer = WeakReference(this),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesPdfViewerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadPdf()
    }

    override fun onStop() {
        viewModel.cancelTasks()
        super.onStop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    override fun reflectPdf(pdf: PdfRenderer) {
        val view = binding?.pagesPdfViewerImage ?: return
        val page = pdf.openPage(0)

        var height = view.height
        var width = view.width

        val hRatio = height.toDouble() / page.height.toDouble()
        val wRatio = width.toDouble() / page.width.toDouble()
        if (wRatio <= hRatio) {
            height = ceil(page.height * wRatio).toInt()
        } else {
            width = ceil(page.width * hRatio).toInt()
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        page.render(
            bitmap,
            Rect(
                0,
                0,
                width,
                height,
            ),
            null,
            PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
        )
        view.setImageBitmap(bitmap)
    }
}
