# XAppRecipe(iOS)
## Quick Start
* ```bash``` or ```zsh```
* ```brew``` (by [Homebrew])
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
Setup commands | ```make setup-commands```
Setup Xcode projects | ```make``` or ```make setup-xcode```

### Settings
* [CocoaPods settings](./App/Podfile)
* [Command settings][Makefile]
* [Prerequire command settings](./setup-command.sh)
* [Xcode project settings](./App/project.yml)


[Homebrew]: https://brew.sh/
[IonicCLI]: https://ionicframework.com/
[Makefile]: ./Makefile
[Node]: https://nodejs.org/
