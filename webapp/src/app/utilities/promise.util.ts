/**
 * Promise 関連ユーティリティー
 */
export class PromiseUtil {

  /**
   * 指定した時間が経過するまで待機させるPromise の生成
   *
   * @param time 待機させたい時間[ms]
   */
  public static delay(time: number): Promise<undefined> {
    return new Promise(resolve => setTimeout(resolve, time));
  }
}
