package work.shion.xapprecipe.pages.top

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavTopDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesTopBinding

/**
 * トップ
 */
class MainFragment : Fragment(R.layout.pages_top) {

    private var binding: PagesTopBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        // ドロワー
        binding?.pagesTopDrawer?.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.pages_top_drawer_licenses -> {
                    MainFragmentDirections.navactToLicense()
                }
                else -> null
            }?.also { direction ->
                activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
                    ?.navigate(direction)
            }
            return@setNavigationItemSelectedListener true
        }

        // フッター
        binding?.pagesTopFooter?.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.pages_top_footer_home -> NavTopDirections.navactTopToHome()
                R.id.pages_top_footer_2,
                R.id.pages_top_footer_3,
                R.id.pages_top_footer_4,
                R.id.pages_top_footer_5 -> NavTopDirections.navactTopToSample()
                else -> null
            }?.also { direction ->
                activity?.let { Navigation.findNavController(it, R.id.pages_top_entrypoint) }
                    ?.navigate(direction)
            }
            return@setOnNavigationItemSelectedListener true
        }

        // ヘッダー
        binding?.pagesTopHeader?.setOnClickListener {
            activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
                ?.navigate(MainFragmentDirections.navactToNotice())
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
