package work.shion.xapprecipe

import android.app.Activity

fun Activity?.getProvider() = (this?.application as? MainApplication)?.provider
