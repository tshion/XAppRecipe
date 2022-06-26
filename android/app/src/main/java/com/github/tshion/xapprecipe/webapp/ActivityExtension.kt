package com.github.tshion.xapprecipe.webapp

import android.app.Activity

fun Activity?.getProvider() = (this?.application as? MainApplication)?.provider
