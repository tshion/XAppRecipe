import Capacitor

@objc(XAppRecipePlugin)
public class XAppRecipePlugin: CAPPlugin {
    @objc func launch(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.success([
            "value": value,
        ])
    }
}
