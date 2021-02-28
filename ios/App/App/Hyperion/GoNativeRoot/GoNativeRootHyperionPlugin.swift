import Foundation
import HyperionCore

/**
 iOS Native トップ画面を表示するためのHyperion プラグイン
 */
class GoNativeRootHyperionPlugin: NSObject, HYPPlugin {
    static func createPluginModule(_ pluginExtension: HYPPluginExtensionProtocol) -> HYPPluginModuleProtocol {
        return GoNativeRootHyperionPluginModule(with: pluginExtension)
    }

    static func pluginVersion() -> String {
        return "1.0.0"
    }
}
