package work.shion.xapprecipe

import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod

/**
 * XAppRecipe アプリ用のCapacitor プラグイン
 */
@CapacitorPlugin(name = "XAppRecipe")
class XAppRecipePlugin : Plugin() {

    @PluginMethod()
    fun launch(call: PluginCall) {
        bridge?.executeOnMainThread {
            bridge?.activity
                ?.also { EntryPointActivity.launch(it) }
            call.success()
        }
    }
}
