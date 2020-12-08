package work.shion.xapprecipe.hyperion

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import work.shion.xapprecipe.R
import work.shion.xapprecipe.templates.launch_error_dialog.LaunchErrorDialog

@AutoService(Plugin::class)
class ShowLaunchErrorDialog : Plugin() {
    override fun createPluginModule() = object : PluginModule() {

        override fun createPluginView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ) = layoutInflater.inflate(R.layout.hyperion_menu, parent, false).apply {
            findViewById<TextView>(R.id.hyperion_menu_title)?.setText(name)

            setOnClickListener {
                val activity = extension.activity
                if (activity !is AppCompatActivity) {
                    return@setOnClickListener
                }

                LaunchErrorDialog.newInstance(
                    1000,
                    null
                ).show(activity.supportFragmentManager, LaunchErrorDialog.TAG)
            }
        }

        override fun getName() = R.string.hyperion_show_launch_error_dialog_title
    }
}
