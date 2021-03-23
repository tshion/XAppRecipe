import MaterialComponents.MaterialBottomNavigation
import UIKit

class TopViewController: UIViewController {
    @IBOutlet weak var tab: MDCBottomNavigationBar!

    static func newInstance() -> TopViewController {
        return TopViewController(nibName: "TopViewController", bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        let homeItem = UITabBarItem(
            title: "Home",
            image: UIImage(named: "ic_home"),
            tag: 0
        )

        let messagesItem = UITabBarItem(
            title: "Messages",
            image: UIImage(named: "ic_email"),
            tag: 0
        )
        messagesItem.badgeValue = "8"

        let favoritesItem = UITabBarItem(
            title: "Favorites",
            image: UIImage(named: "ic_favorite"),
            tag: 0
        )
        favoritesItem.badgeValue = ""

        let readerItem = UITabBarItem(
            title: "Reader",
            image: UIImage(named: "ic_reader"),
            tag: 0
        )
        readerItem.badgeValue = "88"

        let birthdayItem = UITabBarItem(
            title: "ic_birthday",
            image: UIImage(named: "ic_cake"),
            tag: 0
        )
        birthdayItem.badgeValue = "888+"

        tab.alignment = MDCBottomNavigationBarAlignment.justifiedAdjacentTitles
        tab.backgroundColor = UIColor.orange
        tab.items = [homeItem, messagesItem, favoritesItem, readerItem, birthdayItem]
        tab.selectedItem = messagesItem
        tab.titleVisibility = MDCBottomNavigationBarTitleVisibility.selected
    }
}
