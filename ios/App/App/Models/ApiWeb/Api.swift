import Alamofire

class Api {
    func getHtml(
        path: String
    ) {
        AF.request(path).response { response in
            debugPrint(response)
        }
    }
}
