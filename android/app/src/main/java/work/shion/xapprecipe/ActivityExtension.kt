package work.shion.xapprecipe

import android.app.Activity

fun Activity?.getProvider() = (this?.application as? MainApplication)?.provider
fun Activity?.getDataProvider() = (this?.application as? MainApplication)?.dataProvider
