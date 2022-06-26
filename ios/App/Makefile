.PHONY: default
default: generate-project

# iOS ビルド
build:
	bundle exec fastlane build

# Ruby コードの整形
format-ruby:
	bundle exec standardrb --fix

# プロジェクトファイルの生成
generate-project:
	mint run xcodegen generate

# iOS 開発環境の初期セットアップ
init:
	sh scripts/bootstrap.sh

# Xcode テストの実行
run-test:
	bundle exec fastlane tests
