# 開発環境のセットアップ
init:
	sh scripts/setup-command.sh
	@make setup-xcode

# Xcode プロジェクトの生成
setup-xcode:
	mint run LicensePlist --output-path App/Settings.bundle
	mint run xcodegen generate
	bundle exec pod install

# Xcode テストの実行
tests:
	bundle exec fastlane tests
