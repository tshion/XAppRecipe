import MaterialComponents.MaterialBottomNavigation
import UIKit

class TopViewController: UIViewController {
    static func newInstance() -> TopViewController {
        return TopViewController(nib: R.nib.topViewController)
    }

    @IBOutlet weak var footer: MDCBottomNavigationBar!
    @IBOutlet weak var root: UIView!

    override func viewDidLoad() {
        super.viewDidLoad()

        let size = CGSize(width: 24, height: 24)
        let homeItem = UITabBarItem(
            title: "Home",
            image: R.image.icon_iphone()?.resize(size: size),
            tag: 0
        )

        let messagesItem = UITabBarItem(
            title: "Messages",
            image: R.image.icon_iphone()?.resize(size: size),
            tag: 0
        )
        messagesItem.badgeValue = "8"

        let favoritesItem = UITabBarItem(
            title: "Favorites",
            image: R.image.icon_iphone()?.resize(size: size),
            tag: 0
        )
        favoritesItem.badgeValue = ""

        footer.alignment = MDCBottomNavigationBarAlignment.justifiedAdjacentTitles
        footer.backgroundColor = R.color.app_main()
        footer.elevation = ShadowElevation.none
        footer.items = [homeItem, messagesItem, favoritesItem]
        footer.selectedItem = messagesItem
        footer.selectedItemTintColor = UIColor.white
        footer.titleVisibility = MDCBottomNavigationBarTitleVisibility.always
    }
}
