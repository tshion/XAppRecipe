package work.shion.javarecipe.pages.entrypoint.presenters;

import com.annimon.stream.Optional;

import java.lang.ref.WeakReference;

import work.shion.javarecipe.pages.entrypoint.contracts.TutorialPresenterContract;
import work.shion.javarecipe.pages.entrypoint.contracts.TutorialViewContract;


/**
 * チュートリアルの挙動実装
 */
public class TutorialPresenter implements TutorialPresenterContract {

    private final WeakReference<TutorialViewContract> viewer;


    public TutorialPresenter(
            WeakReference<TutorialViewContract> viewer
    ) {
        this.viewer = viewer;
    }


    /**
     * TabPage の呼び出し
     */
    @Override
    public void callTabPage() {
        Optional.ofNullable(viewer.get())
                .ifPresent(viewer -> viewer.goTabPage());
    }
}
