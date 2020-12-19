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
            .map { response ->
                val baseUrl = Uri.parse(BuildConfig.URL_JewelSaviorAPI)
                val cardUrl = baseUrl.buildUpon().appendPath("image").appendPath("card").build()
                val iconUrl = baseUrl.buildUpon().appendPath("image").appendPath("icon").build()

                response.map { item: GetCategoryItemResponse ->
                    val hasImageId = !TextUtils.isEmpty(item.imageId)
                    val imageName = "${item.imageId}.webp"

                    CategoryEntity(
                            cardUrl = if (hasImageId) cardUrl.buildUpon().appendPath(imageName).toString() else null,
                            categoryIndex = index,
                            charaId = item.charaId,
                            charaName = item.charaName,
                            charaNameRead = item.charaNameRead,
                            iconUrl = if (hasImageId) iconUrl.buildUpon().appendPath(imageName).toString() else null
                    )
                }
            }

    /**
     * カテゴリー一覧情報の取得
     */
    fun getCategoryIndex() = api.fetchCategoryIndex()
            .map { response ->
                response.map { item: GetCategoryIndexItemResponse ->
                    CategoryIndexEntity(
                            index = item.index,
                            title = item.title
                    )
                }
            }

    /**
     * 台詞情報の取得
     */
    fun getSpeech(charaId: String) = api.fetchSpeech(charaId)
            .map { response ->
                response.map { item: GetSpeechItemResponse ->
                    SpeechEntity(
                            charaId = item.charaId,
                            charaName = item.charaName,
                            text = item.text,
                            typeNote = item.typeNote
                    )
                }
            }
}
