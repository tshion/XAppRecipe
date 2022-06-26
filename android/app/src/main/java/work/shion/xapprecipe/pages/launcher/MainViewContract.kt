package work.shion.xapprecipe.pages.launcher

interface MainViewContract {

    /**
     * 初回起動者向けフローへ遷移
     */
    fun goBeginner()

    /**
     * 複数回起動者向けフローへ遷移
     */
    fun goLoyalty()

    /**
     * 起動失敗ダイアログの表示
     */
    fun showLaunchErrorDialog()
}
