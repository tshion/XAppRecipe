package work.shion.xapprecipe.pages.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavTopDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesTopBinding

/**
 * トップ
 */
class MainFragment : Fragment() {

    private var binding: PagesTopBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PagesTopBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // ドロワーメニュー
        binding?.pagesTopMenu?.setNavigationItemSelectedListener { menu ->
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
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
