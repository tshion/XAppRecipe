import Capacitor
import SwiftUI

struct BridgeVCWrapper: UIViewControllerRepresentable {
    typealias UIViewControllerType = CAPBridgeViewController

    func makeUIViewController(context: Context) -> CAPBridgeViewController {
        CAPBridgeViewController()
    }

    func updateUIViewController(_ uiViewController: CAPBridgeViewController, context: Context) {}
}

struct BridgeVCWrapper_Previews: PreviewProvider {
    static var previews: some View {
        BridgeVCWrapper()
    }
}
