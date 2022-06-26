# Let's contributing!
Android プロジェクトのみのローカルルールについて記述していきます。
共通事項は[ルートのCONTRIBUTING.md](../CONTRIBUTING.md) をご覧ください。

## 心得
### このプロジェクトのみかも(他は知らん)
* Android OS が提供しているメソッドではなく、[Android Jetpack] で提供されているメソッドを使うこと
* いくつか開発作業時に役立つ設定をしているので、適宜使ってあげてください
    * Chrome Remote Debug すると通信内容とか見れるかも
    * 端末を振る or OS 通知欄をタップするとHyperion が起動するので、レイアウトの確認などに使うと良いかも
    * メモリリーク検知ライブラリLeak Canary に警告されたら連絡してください
* 極力Kotlin で記述していくこと



## レビュー観点
基本的に期待動作をしていて、かつ下記の観点に該当しなければ、
ルールオブスリーの名の下に細かいレビューを保留し、どんどん受け入れていきます(願望)

* Android Developer に掲載されている[アプリの中核品質] に準拠しているかどうか
* [Androidアプリのセキュア設計・セキュアコーディングガイド] に準拠しているかどうか
* メソッドの誤用をしていないかどうか<br />
→ Android OS 自身のアップデートがiOS に比べて難しいため、その差異を吸収するための工夫をしていかないと保守が辛くなるため



## 参考資料
### Android 関連
* [Android Jetpack]
* [Androidアプリのセキュア設計・セキュアコーディングガイド]
* [アプリの中核品質]



[Android Jetpack]: https://developer.android.com/jetpack?hl=ja
[Androidアプリのセキュア設計・セキュアコーディングガイド]: https://www.jssec.org/dl/android_securecoding/index.html
[アプリの中核品質]: https://developer.android.com/docs/quality-guidelines/core-app-quality?hl=ja
