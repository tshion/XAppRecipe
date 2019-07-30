package work.shion.baser.strictmode;


/**
 * StrictMode の機能付与のJava ラッパー
 *
 * @see <a href="https://developer.android.com/reference/android/os/StrictMode">StrictMode</a>
 */
public interface IStrictModeJavaAttacher extends IStrictModeAttacher {

    /**
     * ThreadPolicy のセットアップ
     */
    @Override
    default void setupThreadPolicy() {
        IStrictModeAttacher.DefaultImpls.setupThreadPolicy(this);
    }

    /**
     * VmPolicy のセットアップ
     */
    @Override
    default void setupVmPolicy() {
        IStrictModeAttacher.DefaultImpls.setupVmPolicy(this);
    }
}
