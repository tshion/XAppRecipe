package work.shion.xapprecipe.pages.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import work.shion.xapprecipe.databinding.PagesTutorialPageBinding

/**
 * チュートリアルの各ページ
 */
class PageFragment : Fragment() {

    companion object {
        private const val ARGS_IMAGE_PATH = "ARGS_IMAGE_PATH"


        @JvmStatic
        fun newInstance(imagePath: String?) = PageFragment().apply {
            arguments = bundleOf(
                ARGS_IMAGE_PATH to imagePath
            )
        }
    }


    private var binding: PagesTutorialPageBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PagesTutorialPageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARGS_IMAGE_PATH)?.also {
            Picasso.get()
                .load(it)
                .into(binding?.pagesTutorialPageImage)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
