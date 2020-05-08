package work.shion.xapprecipe

import com.getcapacitor.NativePlugin
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod

/**
 * XAppRecipe アプリ用のCapacitor プラグイン
 */
@NativePlugin()
class XAppRecipePlugin : Plugin() {

  @PluginMethod()
  fun launch(call: PluginCall) {
    bridge?.executeOnMainThread {
      bridge?.activity
        ?.also { NativeRootActivity.launch(it) }
      call.success()
    }
  }
}
