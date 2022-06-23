# iOS コードの配置
deploy-ios:
	cd ios/App; $(MAKE) generate-project
	cd webapp; npm run build; export CAPACITOR_COCOAPODS_PATH="$(PWD)/scripts/bin/pod-wrapper"; npx cap sync ios
	cd ios/App; $(PWD)/scripts/bin/pod-wrapper install # FIXME: cap sync で何故かpod install が行われないので追記した(2022/06/19)
	cd ios/App; mint run LicensePlist --output-path App/Settings.bundle

# iOS 開発環境の初期セットアップ
init-ios:
	sh scripts/setup-ruby.sh
	cd ios/App; $(MAKE) init

# iOS コードを対応IDE で開く
open-ios:
	cd webapp; npx cap open ios
