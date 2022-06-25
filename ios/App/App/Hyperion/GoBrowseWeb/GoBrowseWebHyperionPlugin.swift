import HyperionCore

/**
 WEB 表示画面へ遷移するためのHyperion プラグイン
 */
class GoBrowseWebPlugin: NSObject, HYPPlugin {
    static func createPluginModule(_ pluginExtension: HYPPluginExtensionProtocol) -> HYPPluginModuleProtocol {
        return GoBrowseWebPluginModule(with: pluginExtension)
    }

    static func pluginVersion() -> String {
        return "1.0.0"
    }
}
