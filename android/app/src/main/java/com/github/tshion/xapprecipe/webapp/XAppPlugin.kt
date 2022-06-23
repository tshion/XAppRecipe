package com.github.tshion.xapprecipe.webapp

import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod

/**
 * XAppRecipe 用のCapacitor プラグイン
 */
@CapacitorPlugin(name = "XApp")
class XAppPlugin : Plugin() {

    @PluginMethod()
    fun launch(call: PluginCall) {
        bridge?.executeOnMainThread {
            // bridge?.activity
            //     ?.also { EntryPointActivity.launch(it) }
            call.success()
        }
    }
}
