package work.shion.javarecipe.pages.entrypoint.presenters;

import android.view.MenuItem;

import java.lang.ref.WeakReference;

import work.shion.javarecipe.R;
import work.shion.javarecipe.pages.entrypoint.contracts.TabPresenterContract;
import work.shion.javarecipe.pages.entrypoint.contracts.TabViewContract;


/**
 * タブの挙動実装
 */
public class TabPresenter implements TabPresenterContract {

    private final WeakReference<TabViewContract> viewer;


    public TabPresenter(
            WeakReference<TabViewContract> viewer
    ) {
        this.viewer = viewer;
    }


    /**
     * NavigationItem 選択時
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem selected) {
        TabViewContract viewer = this.viewer.get();
        if (viewer == null) {
            return false;
        }

        switch (selected.getItemId()) {
            case R.id.entrypoint_menu_tab_1st:
                viewer.goTab1st();
                return true;
            case R.id.entrypoint_menu_tab_2nd:
                viewer.goTab2nd();
                return true;
            case R.id.entrypoint_menu_tab_web:
                viewer.goWebPage();
                return true;
            default:
                return false;
        }
    }
}
