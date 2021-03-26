import HyperionCore

/**
 ズンドコ節リストへ遷移するためのHyperion プラグイン
 */
class GoTopPlugin: NSObject, HYPPlugin {
    static func createPluginModule(_ pluginExtension: HYPPluginExtensionProtocol) -> HYPPluginModuleProtocol {
        return GoTopPluginModule(with: pluginExtension)
    }

    static func pluginVersion() -> String {
        return "1.0.0"
    }
}
