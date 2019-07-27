package work.shion.javarecipe;


import work.shion.strictmode.IStrictModeAttacher;

/**
 * デバッグ用アプリ状態の管理
 */
public class DebugApplication extends MainApplication
        implements IStrictModeAttacher {

    @Override
    public void onCreate() {
        super.onCreate();

        // StrictMode の有効化
        setupThreadPolicy();
        setupVmPolicy();
    }


    /**
     * ThreadPolicy のセットアップ
     */
    @Override
    public void setupThreadPolicy() {
        IStrictModeAttacher.DefaultImpls.setupThreadPolicy(this);
    }

    /**
     * VmPolicy のセットアップ
     */
    @Override
    public void setupVmPolicy() {
        IStrictModeAttacher.DefaultImpls.setupVmPolicy(this);
    }
}
