# Baser
共通で使えそうなボイラーテンプレートを整備していくリポジトリ


## 開発言語について
このモジュールはKotlin を設定しています。
Android OS の対応範囲を考えて、kotlin-stdlib-jdk7 を利用しています。
今後もしJava 8 の標準API が使えるAndroid OS になった際は、JDK8 版の検討をしたいと思います。


## リソースの補足
### mipmap
リソース名 | 用途 | 備考
--- | --- | ---
ic_launcher | アプリ起動アイコン | 各プロジェクトで同名のリソースを配置して上書きしてください


## 利用ライブラリ
* [Stetho](https://github.com/facebook/stetho)
