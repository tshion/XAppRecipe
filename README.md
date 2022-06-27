# XAppRecipe
This is recipes of cross-platform mobile application.
I am using [Capacitor] which is a so-called hybrid application.

![architecture](https://www.plantuml.com/plantuml/svg/VP0z3i8m38NtdCBg14Clm82Ac2eX651XTXEBMWf9b9_s-hYbI9tmOidlUtwMcvGnvlAsW54Fd4dd4aafrDb7w195m0H5kdBU45GdPw9d0rVCF58rg_WThS2JUkZQNUiTwpru1sSCg7bU9zA3QLfCFIPQ7NoxRzUnJqfgpeJwk807k61nUe0y4608bhNmtgbPXBe4uTOh3f9dBOoCc-1Wouj_jF6J4rvY_h6g8MVaBny0 "architecture")



## How to setup
This is the table of contents.
I'd be grateful if you could read it necessary for the work.

* Setup web project
* Setup android capacitor project
* Setup ios capacitor project

For more development tips, check project's README.

### Setup web project
* [Node.js] (See [node-version](./.node-version) for the version.)

If you can use above, run these commands at project root.

1. ```cd webapp```
2. ```npm ci```

If you can use ```make```, run ```make init-web``` instead.

### Setup android capacitor project
* [Android Studio]

If you can use above and completed "Setup web project", run these commands at project root.

1. ```cd webapp```
2. ```npx ionic build```
3. ```npx cap sync android```
4. ```npx cap open android```

If you can use ```make```, run these instead.

1. ```make deploy-android```
2. ```make open-android```

### Setup ios capacitor project
* ```bash ``` or ```zsh```
* ```make```
* [Mint]
* Ruby (See [ruby-version](././ruby-version) for the version.)
    * ```bundle``` (by [RubyGems])
* [Xcode]

If you can use above and completed "Setup web project", run these commands at project root.

1. ```make init-ios``` (Setup ios develop environment)
2. ```make deploy-ios``` (Setup ios project)
3. ```make open-ios``` (Launch Xcode)



## Notes
### Coding rule
* CONTRIBUTING ([日本語](./CONTRIBUTING.md))
* [EditorConfig] (See [.editorconfig](./.editorconfig) for the version.)

### Project paths
Path | Description
--- | ---
[android/](./android) | android capacitor project root
[docs/](./docs/) | documents
[ios/](./ios) | ios capacitor project root
[webapp/](./webapp) | web project root

### Recommended tools
* [anyenv]
    * [nodenv]
    * [rbenv]
* [Visual Studio Code]
    * [EditorConfig for VS Code](https://marketplace.visualstudio.com/items?itemName=EditorConfig.EditorConfig)
    * [GitHub Pull Requests and Issues](https://marketplace.visualstudio.com/items?itemName=GitHub.vscode-pull-request-github)



## References
* [Android Studio]
* [anyenv]
* [Capacitor]
* [EditorConfig]
* [Ionic]
* [Mint]
* [Node.js]
* [nodenv]
* [rbenv]
* [Visual Studio Code]
* [Xcode]



[Android Studio]: https://developer.android.com/studio
[anyenv]: https://github.com/anyenv/anyenv
[Capacitor]: https://capacitor.ionicframework.com/
[EditorConfig]: https://editorconfig.org/
[Ionic]: https://ionicframework.com/
[Mint]: https://github.com/yonaskolb/Mint
[Node.js]: https://nodejs.org/
[nodenv]: https://github.com/nodenv/nodenv
[rbenv]: https://github.com/rbenv/rbenv
[RubyGems]: https://rubygems.org/
[Visual Studio Code]: https://code.visualstudio.com/
[Xcode]: https://developer.apple.com/documentation/xcode-release-notes
