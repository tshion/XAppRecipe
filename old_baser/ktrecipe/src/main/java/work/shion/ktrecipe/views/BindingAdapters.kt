package work.shion.ktrecipe.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import work.shion.ktrecipe.R

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(url: String?) {
        Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(this)
    }
}
