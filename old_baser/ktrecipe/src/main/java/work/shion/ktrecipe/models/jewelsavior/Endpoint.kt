package work.shion.ktrecipe.models.jewelsavior

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import work.shion.ktrecipe.models.jewelsavior.entities.GetCategoryIndexItemResponse
import work.shion.ktrecipe.models.jewelsavior.entities.GetCategoryItemResponse
import work.shion.ktrecipe.models.jewelsavior.entities.GetSpeechItemResponse

/**
 * エンドポイント
 */
internal interface Endpoint {

    /**
     * カテゴリー情報の取得
     * @param index カテゴリーの指定情報
     */
    @GET("category/{index}")
    fun fetchCategoryDetail(
            @Path("index") index: Int
    ): Single<List<GetCategoryItemResponse>>

    /**
     * カテゴリー一覧情報の取得
     */
    @GET("category")
    fun fetchCategoryIndex(
    ): Single<List<GetCategoryIndexItemResponse>>

    /**
     * セリフ情報の取得
     * @param charaId キャラクターID
     */
    @GET("speech/{chara_id}")
    fun fetchSpeech(
            @Path("chara_id") charaId: String
    ): Single<List<GetSpeechItemResponse>>
}
