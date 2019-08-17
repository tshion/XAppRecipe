package work.shion.baser.timber

import timber.log.Timber

/**
 * Timber の機能付与
 * @see <a href="https://github.com/JakeWharton/timber">Timber</a>
 */
interface ITimberAttacher {

    /**
     * 本番用のTimber 設定の取得
     */
    fun getProductTree(): Timber.Tree

    /**
     * Timber のセットアップ
     * @param isDevelopment 開発設定かどうか
     */
    fun setupTimber(
        isDevelopment: Boolean
    ) {
        Timber.plant(
            if (isDevelopment) {
                Timber.DebugTree()
            } else {
                getProductTree()
            }
        )
    }
}
