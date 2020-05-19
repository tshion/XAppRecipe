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

# Xcode テストの実行
tests:
	bundle exec fastlane tests
