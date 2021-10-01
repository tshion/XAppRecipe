package work.shion.xapprecipe.pages.pdf_viewer

import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.pagesPdfViewerImage?.setOnTouchListener { view, motionEvent ->
            val width = view?.width?.toFloat() ?: 0f
            val pointEnd = (width * 3 / 4)..width
            val pointStart = 0f..(width * 1 / 4)

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (pointEnd.contains(motionEvent.x)) {
                        viewModel.loadNext()
                        true
                    } else if (pointStart.contains(motionEvent.x)) {
                        viewModel.loadPrev()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
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


    override fun reflectPdf(page: PdfRenderer.Page) {
        val view = binding?.pagesPdfViewerImage ?: return

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
