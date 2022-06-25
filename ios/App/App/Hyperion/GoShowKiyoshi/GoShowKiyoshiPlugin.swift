import HyperionCore

/**
 ズンドコ節リストへ遷移するためのHyperion プラグイン
 */
class GoShowKiyoshiPlugin: NSObject, HYPPlugin {
    static func createPluginModule(_ pluginExtension: HYPPluginExtensionProtocol) -> HYPPluginModuleProtocol {
        return GoShowKiyoshiPluginModule(with: pluginExtension)
    }

    static func pluginVersion() -> String {
        return "1.0.0"
    }
}
