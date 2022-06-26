package com.github.tshion.xapprecipe

import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import work.shion.xapprecipe.EntryPointActivity

/**
 * XAppRecipe 用のCapacitor プラグイン
 */
@CapacitorPlugin(name = "XApp")
class XAppPlugin : Plugin() {

    @PluginMethod()
    fun launch(call: PluginCall) {
        bridge?.executeOnMainThread {
            bridge?.activity
                ?.also { EntryPointActivity.launch(it) }
            call.resolve()
        }
    }
}
