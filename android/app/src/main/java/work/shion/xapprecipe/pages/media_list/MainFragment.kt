package work.shion.xapprecipe.pages.media_list

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import permissions.dispatcher.ktx.constructPermissionsRequest
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PageMediaListBinding
import java.lang.ref.WeakReference

/**
 * メディアコンテンツ一覧の表示
 */
class MainFragment : Fragment(R.layout.page_media_list), MainViewContract {

    private val adapter = MainAdapter(
        diffCallback = MainAdapterDiffs()
    )
    private var binding: PageMediaListBinding? = null
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            application = requireActivity().application,
            viewer = WeakReference(this),
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageMediaListBinding.bind(view)

        binding?.pageMediaListContents?.adapter = adapter
        binding?.pageMediaListHeader?.setupBackListener {
            activity?.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()

        constructPermissionsRequest(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            onNeverAskAgain = {
                Toast.makeText(requireContext(), "never", Toast.LENGTH_SHORT).show()
            },
            onPermissionDenied = {
                Toast.makeText(requireContext(), "denied", Toast.LENGTH_SHORT).show()
            },
            onShowRationale = {
                it.proceed()
            }
        ) {
            viewModel.loadMedia()
        }.launch()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    /**
     * ローディング状態の反映
     */
    override fun reflectLoading(shouldShow: Boolean) {
        // TODO("Not yet implemented")
    }

    /**
     * リスト表示への反映
     */
    override fun reflectList(data: List<MediaViewData>) {
        adapter.displayData = data
    }
}
