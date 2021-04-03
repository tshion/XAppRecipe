import HyperionCore

/**
 標準のビデオ撮影機能の呼び出しをするHyperion プラグイン
 */
class LaunchTakeVideoNormalHyperionPlugin: NSObject, HYPPlugin {
    static func createPluginModule(_ pluginExtension: HYPPluginExtensionProtocol) -> HYPPluginModuleProtocol {
        return LaunchTakeVideoNormalHyperionPluginModule(with: pluginExtension)
    }

    static func pluginVersion() -> String {
        return "1.0.0"
    }
}
