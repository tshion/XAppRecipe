package work.shion.ktrecipe.models.jewelsavior

import android.net.Uri
import android.text.TextUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import work.shion.baser.square.attachStetho
import work.shion.ktrecipe.BuildConfig
import work.shion.ktrecipe.models.jewelsavior.entities.*
import work.shion.ktrecipe.models.jewelsavior.entities.GetCategoryIndexItemResponse
import work.shion.ktrecipe.models.jewelsavior.entities.GetCategoryItemResponse
import work.shion.ktrecipe.models.jewelsavior.entities.GetSpeechItemResponse

/**
 * JewelSavior を扱うWebAPI
 */
class JewelSaviorApi(
) {
    private val api: Endpoint by lazy {
        val baseClient = OkHttpClient.Builder()
                .attachStetho()
                .build()

        Retrofit.Builder()
                .baseUrl(BuildConfig.URL_JewelSaviorAPI)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(baseClient)
                .build()
                .create(Endpoint::class.java)
    }


    /**
     * カテゴリー情報の取得
     */
    fun getCategoryDetail(index: Int) = api.fetchCategoryDetail(index)
            .flattenAsFlowable { response -> response }
            .map { item: GetCategoryItemResponse ->
                val base = Uri.parse(BuildConfig.URL_JewelSaviorAPI)
                        .buildUpon()
                        .appendPath("image")
                        .build()
                val cardUrl = if (!TextUtils.isEmpty(item.imageId)) base.buildUpon().appendPath("card").appendPath("${item.imageId}.webp").toString() else null
                val iconUrl = if (!TextUtils.isEmpty(item.imageId)) base.buildUpon().appendPath("icon").appendPath("${item.imageId}.webp").toString() else null

                CategoryEntity(
                        cardUrl = cardUrl,
                        categoryIndex = index,
                        charaId = item.charaId,
                        charaName = item.charaName,
                        charaNameRead = item.charaNameRead,
                        iconUrl = iconUrl
                )
            }
            .reduce(arrayListOf<CategoryEntity>(), { list, item -> list.apply { add(item) } })

    /**
     * カテゴリー一覧情報の取得
     */
    fun getCategoryIndex() = api.fetchCategoryIndex()
            .flattenAsFlowable { response -> response }
            .map { item: GetCategoryIndexItemResponse ->
                CategoryIndexEntity(
                        index = item.index,
                        title = item.title
                )
            }
            .reduce(arrayListOf<CategoryIndexEntity>(), { list, item -> list.apply { add(item) } })

    /**
     * 台詞情報の取得
     */
    fun getSpeech(charaId: String) = api.fetchSpeech(charaId)
            .flattenAsFlowable { response -> response }
            .map { item: GetSpeechItemResponse ->
                SpeechEntity(
                        charaId = item.charaId,
                        charaName = item.charaName,
                        text = item.text,
                        typeNote = item.typeNote
                )
            }
            .reduce(arrayListOf<SpeechEntity>(), { list, item -> list.apply { add(item) } })
}
