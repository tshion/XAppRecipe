# Android コードの配置
deploy-android:
	@make deploy-web
	cd webapp; npx cap sync android
	@echo finish $@.

# iOS コードの配置
deploy-ios:
	cd ios/App; $(MAKE) generate-project
	@make deploy-web
	cd webapp; export CAPACITOR_COCOAPODS_PATH="$(PWD)/scripts/bin/pod-wrapper"; npx cap sync ios
	cd ios/App; $(PWD)/scripts/bin/pod-wrapper install # FIXME: cap sync で何故かpod install が行われないので追記した(2022/06/19)
	cd ios/App; $(MAKE) format-ruby
	cd ios/App; mint run LicensePlist --output-path App/Settings.bundle
	@echo finish $@.

# WEB コードの配置
deploy-web:
	cd webapp; npx ionic build $(ARGS);
	@echo finish $@.

# iOS 開発環境の初期セットアップ
init-ios:
	sh scripts/setup-ruby.sh
	cd ios/App; $(MAKE) init
	@echo finish $@.

# WEB 開発環境の初期セットアップ
init-web:
	cd webapp; npm ci
	@echo finish $@.

# Android コードを対応IDE で開く
open-android:
	cd webapp; npx cap open android
	@echo finish $@.

# iOS コードを対応IDE で開く
open-ios:
	cd webapp; npx cap open ios
	@echo finish $@.
