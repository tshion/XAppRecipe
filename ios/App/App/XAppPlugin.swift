import Capacitor

/// XAppRecipe 用のCapacitor プラグイン
@objc(XAppPlugin)
public class XAppPlugin: CAPPlugin {

    /// ネイティブ側の画面起動
    @objc func launch(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            // guard let nextPage = R.storyboard.nativeRoot.instantiateInitialViewController() else {
            //     return
            // }

            // nextPage.modalPresentationStyle = .fullScreen
            // self.bridge?.viewController?.present(nextPage, animated: true, completion: nil)
            call.resolve()
        }
    }
}
