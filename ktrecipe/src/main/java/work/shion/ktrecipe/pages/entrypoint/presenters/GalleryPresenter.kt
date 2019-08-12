package work.shion.ktrecipe.pages.entrypoint.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import work.shion.ktrecipe.models.jewelsavior.JewelSaviorApi
import work.shion.ktrecipe.pages.entrypoint.contracts.GalleryPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.GalleryViewContract
import work.shion.ktrecipe.pages.entrypoint.viewmodels.GalleryIndexItemViewModel
import java.lang.ref.WeakReference

/**
 * 画像項目の挙動実装
 */
class GalleryPresenter(
        private val api: JewelSaviorApi,
        private val viewer: WeakReference<GalleryViewContract>
) : GalleryPresenterContract {

    private var getCategoryDetailTask: Disposable? = null


    /**
     * 画像詳細画面の呼び出し
     * @param 詳細画像のURL
     */
    override fun callDetail(url: String?) {
        viewer.get()?.goDetail(url)
    }

    override fun killTask() {
        getCategoryDetailTask?.dispose()
        getCategoryDetailTask = null
    }

    override fun refreshAdapterData() {
        viewer.get()?.showProgress()

        getCategoryDetailTask = api.getCategoryDetail(0)
                .subscribeOn(Schedulers.computation())
                .map { list ->
                    list.map { item ->
                        GalleryIndexItemViewModel().also { vm ->
                            vm.cardUrl.set(item.cardUrl)
                            vm.iconUrl.set(item.iconUrl)
                            vm.title.set(item.charaName)
                        }
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    viewer.get()?.replaceAdapterData(result)
                }
    }
}
