package work.shion.xapprecipe.hyperion

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.github.tshion.xapprecipe.NavEntrypointDevDirections
import com.github.tshion.xapprecipe.R
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class ShowBiometricAuth : Plugin() {
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

                activity.let { Navigation.findNavController(it, R.id.entrypoint) }
                    .navigate(NavEntrypointDevDirections.navactToLoginBiometric())
            }
        }

        override fun getName() = R.string.hyperion_show_biometric_auth_title
    }
}
