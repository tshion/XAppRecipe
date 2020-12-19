package work.shion.baser.stetho;

import android.content.Context;

import org.jetbrains.annotations.NotNull;


/**
 * Stetho の機能付与のJava ラッパー
 *
 * @see <a href="https://github.com/facebook/stetho">facebook/stetho</a>
 */
public interface IStethoJavaAttacher extends IStethoAttacher {

    /**
     * Stetho の組み込み
     *
     * @param appContext アプリケーションコンテキスト
     */
    @Override
    default void attachStetho(@NotNull Context appContext) {
        IStethoAttacher.DefaultImpls.attachStetho(this, appContext);
    }
}
