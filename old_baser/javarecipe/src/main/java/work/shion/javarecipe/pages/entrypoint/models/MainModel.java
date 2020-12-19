package work.shion.javarecipe.pages.entrypoint.models;

import java.util.Date;
import java.util.Random;


/**
 * メインコンテンツ用のビジネスロジック実装
 */
public class MainModel {

    /**
     * 乱数の取得
     */
    public int getRandom(
    ) {
        long seed = (new Date()).getTime();
        return getRandom(seed);
    }

    /**
     * 乱数の取得
     *
     * @param seed 乱数生成時の初期値
     */
    public int getRandom(
            long seed
    ) {
        return new Random(seed).nextInt(100);
    }
}
