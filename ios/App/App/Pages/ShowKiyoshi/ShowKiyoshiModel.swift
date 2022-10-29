class ShowKiyoshiModel {
    private static let beat1 = "ズン"
    private static let beat2 = "ズンドコ"
    private static let beat3 = "きよし"

    /**
     ズンドコ節の文字列生成
     */
    func generate(_ groupId: Int) -> String {
        var result = [String](repeating: ShowKiyoshiModel.beat1, count: Int.random(in: 1...3))
        result.insert("Group\(groupId): ", at: 0)
        if result.count == 3, Int.random(in: 0...1) == 1 {
            result.append(ShowKiyoshiModel.beat2)
            result.append(ShowKiyoshiModel.beat3)
        }
        return result.joined()
    }
}
