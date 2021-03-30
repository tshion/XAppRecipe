import Capacitor

@objc(XAppRecipePlugin)
public class XAppRecipePlugin: CAPPlugin {
    @objc func launch(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            guard let nextPage = R.storyboard.nativeRoot.instantiateInitialViewController() else {
                return
            }

            nextPage.modalPresentationStyle = .fullScreen
            self.bridge?.viewController?.present(nextPage, animated: true, completion: nil)
            call.resolve()
        }
    }
}
