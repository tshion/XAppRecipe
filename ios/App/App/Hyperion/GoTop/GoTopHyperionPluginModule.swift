import HyperionCore

class GoTopPluginModule: GoViewControllerHyperionPluginModule {
    required init(with ext: HYPPluginExtensionProtocol) {
        super.init(with: ext) {
            TopViewController.newInstance()
        }
    }

    override func pluginMenuItemTitle() -> String {
        return "トップ画面へ遷移"
    }
}
