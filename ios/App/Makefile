# 開発環境のセットアップ
init:
	sh scripts/setup-command.sh
	@make setup-xcode

# Xcode プロジェクトの生成
setup-xcode:
	mint run xcodegen generate
	bundle exec pod install
	mint run LicensePlist --output-path App/Settings.bundle

# Xcode テストの実行
tests:
	bundle exec fastlane tests
