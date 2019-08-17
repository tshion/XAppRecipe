package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import work.shion.ktrecipe.R


/**
 * タブコンテンツその１用のFragment
 */
class Tab1stPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.entrypoint_fragment_tab_1st, container, false)
    }
}
