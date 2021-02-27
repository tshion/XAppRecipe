import Foundation
import HyperionCore

class ShowNativeRootHyperionPlugin: NSObject, HYPPlugin {
    static func createPluginModule(_ pluginExtension: HYPPluginExtensionProtocol) -> HYPPluginModuleProtocol {
        return ShowNativeRootHyperionPluginModule(with: pluginExtension)
    }

    static func pluginVersion() -> String {
        return "1.0.0"
    }
}
