package com.github.tshion.xapprecipe_core.entities

import java.util.*

/**
 * やること情報
 *
 * @param id 識別番号
 * @param isFinish 完了したかどうか
 * @param title やることの項目名
 * @param updateDate 更新日
 */
data class ToDoTaskEntity(
    val id: String?,
    val isFinish: Boolean,
    val title: String,
    val updateDate: Date,
)
