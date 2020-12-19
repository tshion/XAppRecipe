package work.shion.javarecipe.pages.entrypoint.presenters;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.annimon.stream.Optional;

import java.lang.ref.WeakReference;

import work.shion.javarecipe.pages.entrypoint.contracts.MainPresenterContract;
import work.shion.javarecipe.pages.entrypoint.contracts.MainViewContract;
import work.shion.javarecipe.pages.entrypoint.models.MainModel;


/**
 * メインコンテンツの挙動実装
 */
public class MainPresenter implements MainPresenterContract {
    private final MainModel model;
    private final WeakReference<MainViewContract> viewer;


    public MainPresenter(
            MainModel model,
            WeakReference<MainViewContract> viewer
    ) {
        this.model = model;
        this.viewer = viewer;
    }


    /**
     * 最初に表示する画面の呼び出し
     */
    @Override
    public void callFirstView() {
        HandlerThread subThread = new HandlerThread("sub");
        subThread.start();
        new Handler(subThread.getLooper()).postDelayed(() -> {
            final int random = model.getRandom();
            Optional.ofNullable(viewer.get())
                    .ifPresent(viewer -> {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (random < 50) {
                                viewer.goTutorialPage();
                            } else {
                                viewer.goTabPage();
                            }
                        });
                    });
        }, 1500);
    }
}
