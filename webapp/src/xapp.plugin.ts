import { registerPlugin } from '@capacitor/core';


/**
 * XAppRecipe 用のCapacitor プラグイン
 */
export interface XAppPlugin {

  /** ネイティブ側の画面起動 */
  launch(): Promise<never>;
}


const xApp = registerPlugin<XAppPlugin>('XApp');


export default xApp;
