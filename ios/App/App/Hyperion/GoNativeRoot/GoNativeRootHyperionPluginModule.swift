import HyperionCore

class GoNativeRootHyperionPluginModule: GoViewControllerHyperionPluginModule {
    required init(with ext: HYPPluginExtensionProtocol) {
        super.init(with: ext) {
            NativeRootViewController.newInstance()
        }
    }

    override func pluginMenuItemTitle() -> String {
        return "Native 実装のルートへ遷移"
    }
}
