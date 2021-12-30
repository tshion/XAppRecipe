.PHONY: default
default: setup


# WEB リソースのビルド
build-web:
	npx lerna run build --scope=@xapprecipe/mainapp
	@echo finish $@.


# 中間ファイル等を一括で削除する
clear:
	rm -f -r "webui/loader"
	sh scripts/clear-directories.sh "ios" "App.xcodeproj"
	sh scripts/clear-directories.sh "ios" "App.xcworkspace"
	sh scripts/clear-directories.sh "." "capacitor-cordova-android-plugins"
	sh scripts/clear-directories.sh "." "capacitor-cordova-ios-plugins"
	sh scripts/clear-directories.sh "." "dist"
	sh scripts/clear-directories.sh "." "node_modules"
	sh scripts/clear-directories.sh "ios" "Pods"
	sh scripts/clear-directories.sh "android/app/src/main" "public"
	sh scripts/clear-directories.sh "ios/App" "public"
	sh scripts/clear-directories.sh "." "vendor"
	sh scripts/clear-directories.sh "." "www"
	@echo finish $@.


# WEB リソースをAndroid 側へ配置する
deploy-android:
	@make build-web
	npx cap sync android --deployment
	@echo finish $@.

# WEB リソースをiOS 側へ配置する
deploy-ios:
	@make build-web
	npx cap copy ios
	-npx cap update ios --deployment # エラー無視
	cd ios; $(MAKE) setup-xcode
	@echo finish $@.


# 開発環境の整備
setup:
	@make setup-js

# 開発環境の整備(iOS 関連)
setup-ios:
	@make setup
	@make setup-ruby
	sh scripts/setup-ios.sh
	cd ios; $(MAKE) init
	@make deploy-ios
	@echo finish $@.

# 開発環境の整備(JS 関連)
setup-js:
	npm ci
	npx lerna bootstrap
	@echo finish $@.

# 開発環境の整備(Ruby 関連)
setup-ruby:
	sh scripts/setup-ruby.sh
	@echo finish $@.
