.PHONY: default
default: setup-xcode


# Xcode プロジェクトのパス
PATH_XCODE_PROJECT := App


# 開発環境のセットアップ
init:
	sh scripts/setup-command.sh
	@make setup-xcode

# Xcode プロジェクトの生成
setup-xcode: $(PATH_XCODE_PROJECT)
	mint run xcodegen generate --spec $^/project.yml
	bundle exec pod install --project-directory=$^
	mint run LicensePlist --output-path App/App/Settings.bundle --pods-path App/Pods

# Xcode テストの実行
tests:
	bundle exec fastlane tests
