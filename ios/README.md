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


## Memo
### Commands
If you want to know the detail, please check [Makefile].

Want to do | Commands | Note
--- | --- | ---
Build Project | ```make build``` | This command doesn't work
First setup | ```make init``` |  |
Run tests | ```make tests``` |  |
Setup Xcode projects | ```make``` or ```make setup-xcode``` |  |

### Targets
Target | Description
--- | ---
App | Main target
AppPreview | Xcode Previews settings for App
AppTests | Unit test for App

### Tool Manager & Settings
I use [Mint] and [RubyGems].
Defined a ```make``` command to put these managers.

#### [Mint] ([Mintfile](./App/Mintfile))
* [LicensePlist]
* [SwiftFormat]
* [SwiftLint]
* [XcodeGen] ([project.yml](./App/project.yml))

#### [RubyGems] ([Gemfile](./App/Gemfile))
* [CocoaPods] ([Podfile](./App/Podfile))
  * [Capacitor](https://cocoapods.org/pods/Capacitor)
  * [CapacitorCordova](https://cocoapods.org/pods/CapacitorCordova)
  * [Hyperion-iOS](https://github.com/willowtreeapps/Hyperion-iOS)
* [fastlane] ([Fastfile](./App/fastlane/Fastfile))

#### Others
* [Makefile][Makefile]
  * [App/Makefile](./App/Makefile)
* [Prerequire command settings](./App/scripts/setup-command.sh)


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
