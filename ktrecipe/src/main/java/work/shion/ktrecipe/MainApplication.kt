package work.shion.ktrecipe

import android.app.Application
import work.shion.ktrecipe.models.jewelsavior.JewelSaviorApi

/**
 * アプリ状態の管理
 */
open class MainApplication : Application() {

    /**
     * JewelSavior を扱うWebAPI
     */
    val apiJewelSavior = JewelSaviorApi()
}
