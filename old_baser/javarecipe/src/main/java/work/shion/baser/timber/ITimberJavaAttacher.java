package work.shion.baser.timber;


/**
 * Timber の機能付与のJava ラッパー
 *
 * @see <a href="https://github.com/JakeWharton/timber">Timber</a>
 */
public interface ITimberJavaAttacher extends ITimberAttacher {

    /**
     * Timber のセットアップ
     *
     * @param isDevelopment 開発設定かどうか
     */
    @Override
    default void setupTimber(boolean isDevelopment) {
        ITimberAttacher.DefaultImpls.setupTimber(this, isDevelopment);
    }
}
