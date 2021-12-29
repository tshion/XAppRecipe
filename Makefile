.PHONY: default
default: setup


# WEB リソースのビルド
build-web:
	npx lerna run build --scope=@xapprecipe/mainapp
	@echo finish $@.


# 中間ファイル等を一括で削除する
clear:
	rm -f -r "webui/loader"
	sh scripts/clear-directories.sh "." "dist"
	sh scripts/clear-directories.sh "." "node_modules"
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
	bundle config set path 'vendor/bundle'
	bundle install
	cd ios; $(MAKE) init
	-npx cap update ios --deployment # エラー無視
	cd ios/App; bundle exec pod install
	@echo finish $@.


# 開発環境の整備
setup:
	@make setup-js

# 開発環境の整備(JS 関連)
setup-js:
	npm ci
	npx lerna bootstrap
