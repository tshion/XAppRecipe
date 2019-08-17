package work.shion.javarecipe.pages.entrypoint.contracts;


import android.view.MenuItem;

/**
 * タブの挙動定義
 */
public interface TabPresenterContract {

    /**
     * NavigationItem 選択時
     */
    boolean onNavigationItemSelected(MenuItem selected);
}
