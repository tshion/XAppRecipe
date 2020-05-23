# XAppRecipe(iOS)
## Quick Start
* ```bash``` or ```zsh```
* ```bundle``` (by [Bundler])
* ```git```
* ```ionic``` (by [IonicCLI])
* ```make```

If you can use above commands, please run these commands.

1. ```ionic build```
2. ```ionic capacitor sync```
3. ```make init```
4. ```ionic capacitor open ios```


## Commands
### Features
If you want to know the detail, please check [Makefile].

Want to do | Commands
--- | ---
First setup | ```make init```
Run tests | ```make tests```
Setup Xcode projects | ```make``` or ```make setup-xcode```

### Tool Manager & Settings
I use [Mint] and [RubyGems].
Defined a ```make``` command to put these managers.

#### [Mint]
* [LicensePlist]
* [Mintfile](./Mintfile)
* [SwiftFormat]
* [SwiftLint]
* [XcodeGen]
  * [project.yml](./App/project.yml)

#### [RubyGems]
* [CocoaPods]
  * [Podfile](./App/Podfile)
* [fastlane]
  * [Fastfile](./fastlane/Fastfile)
* [Gemfile](./Gemfile)

#### Others
* [Makefile][Makefile]
* [Prerequire command settings](./scripts/setup-command.sh)


[Bundler]: https://bundler.io/
[CocoaPods]: https://cocoapods.org/
[fastlane]: https://fastlane.tools/
[IonicCLI]: https://ionicframework.com/
[LicensePlist]: https://github.com/mono0926/LicensePlist
[Makefile]: ./Makefile
[Mint]: https://github.com/yonaskolb/Mint
[Node]: https://nodejs.org/
[RubyGems]: https://rubygems.org/
[SwiftFormat]: https://github.com/nicklockwood/SwiftFormat
[SwiftLint]: https://github.com/realm/SwiftLint
[XcodeGen]: https://github.com/yonaskolb/XcodeGen
