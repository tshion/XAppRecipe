/**
 * 数学関連ユーティリティー
 */
export class MathUtil {

  /**
   * 0 から引数max までのランダムな整数の返却
   *
   * @param max 上限値
   */
  public static getRandomInt(max: number): number {
    return Math.floor(Math.random() * max);
  }
}
