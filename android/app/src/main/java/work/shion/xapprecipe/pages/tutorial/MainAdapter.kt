package work.shion.xapprecipe.pages.tutorial

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

internal class MainAdapter(fragment: MainFragment) : FragmentStateAdapter(fragment) {

    var displayData: List<String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun createFragment(position: Int): Fragment {
        return PageFragment.newInstance(
            imagePath = displayData?.getOrNull(position)
        )
    }


    override fun getItemCount() = displayData?.count() ?: 0
}
