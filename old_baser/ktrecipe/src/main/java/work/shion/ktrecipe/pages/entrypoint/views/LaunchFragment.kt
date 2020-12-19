package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import work.shion.ktrecipe.R


/**
 * 起動時の表示用Fragment
 */
class LaunchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.entrypoint_fragment_launch, container, false)
    }
}
