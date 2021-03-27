import Capacitor

@objc(XAppRecipePlugin)
public class XAppRecipePlugin: CAPPlugin {
    @objc func launch(_ call: CAPPluginCall) {
        let storyboard = UIStoryboard(name: "NativeRoot", bundle: nil)
        DispatchQueue.main.async {
            guard let nextPage = storyboard.instantiateViewController(withIdentifier: "NativeRootViewController") as? NativeRootViewController else {
                return
            }

            nextPage.modalPresentationStyle = .fullScreen
            self.bridge?.viewController?.present(nextPage, animated: true, completion: nil)
            call.resolve()
        }
    }
}
