# Android Baser
Android 開発で自分がよく使うボイラーテンプレートを整備していくリポジトリ


## プロジェクト構成
下記のプロジェクトで構成されている。
それぞれにREADME があるので、そちらも確認してください。

プロジェクト名 | プロジェクトID | 概要
--- | --- | ---
Baser | [baser](./baser) | 共通で使えそうなボイラーテンプレート集
JavaRecipe | [javaRecipe](./javarecipe) | Java 実装レシピ
KotlinRecipe | [ktRecipe](./ktrecipe) | Kotlin 実装レシピ


## 開発環境の整備メモ
* ソースコード整形ルールは[AOSP の整形ルール](https://github.com/aosp-mirror/platform_development/blob/master/ide/intellij/codestyles/AndroidStyle.xml) を適用してください
    * [Android Contributors (AOSP) のJavaコードスタイルをAndroid Studioに設定する](https://qiita.com/kafumi/items/637439abaeed348550f0) を参考に設定してください
* 本番アプリ用署名情報は[singingProd.properties](./singingProd.properties) に記載してください
    * 署名ファイル自体はリポジトリに含めていないです。各案件で適切に管理し、初回セットアップ時に各自で整備してください


## 開発メモ
* gitignore の設定はVSCode 拡張機能[gitignore](https://marketplace.visualstudio.com/items?itemName=codezombiech.gitignore) をベースに設定した
    * Android
    * JetBrains
    * Linux
    * macOS
    * Visual Studio Code
    * Windows
    * プロジェクトでの設定
* バージョニングは[セマンティックバージョニング](https://semver.org/lang/ja) に沿ってつけてください
    * 各プロジェクトのbuild.config の上の方でバージョン指定できます
    * マイナー、リビジョンはそれぞれ0 <= version <= 99 の範囲で指定できます
