package work.shion.xapprecipe.pages.tutorial

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesTutorialBinding

/**
 * チュートリアル
 */
class MainFragment : Fragment(R.layout.pages_tutorial) {

    private var binding: PagesTutorialBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        binding?.pagesTutorialNext?.setOnClickListener {
            activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
                ?.navigate(NavEntrypointDirections.navactToTop())
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
