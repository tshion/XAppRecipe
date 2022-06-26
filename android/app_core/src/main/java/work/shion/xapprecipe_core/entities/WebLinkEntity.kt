package com.github.tshion.xapprecipe.webapp_core.entities

import java.time.LocalDateTime

/**
 * WEB リンク情報
 * @param appendDate 追加日
 * @param description 概要
 * @param id 識別番号
 * @param imagePath 画像パス
 * @param title タイトル
 * @param webPath WEB リンク先
 */
data class WebLinkEntity(
    val appendDate: LocalDateTime,
    val description: String?,
    val id: String,
    val imagePath: String?,
    val title: String?,
    val webPath: String,
)
