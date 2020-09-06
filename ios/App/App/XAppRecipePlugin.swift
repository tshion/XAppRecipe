import Capacitor

@objc(XAppRecipePlugin)
public class XAppRecipePlugin: CAPPlugin {
    @objc func launch(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""

        let storyboard = UIStoryboard(name: "NativeRoot", bundle: nil)
        if let nextPage = storyboard.instantiateViewController(withIdentifier: "NativeRootViewController") as? NativeRootViewController {
            DispatchQueue.main.async {
                self.bridge.viewController.present(nextPage, animated: true, completion: nil)
            }
        }

        call.success([
            "value": value,
        ])
    }
}
