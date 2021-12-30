.PHONY: default
default: setup-xcode


# Xcode プロジェクトのパス
PATH_XCODE_PROJECT := App


# iOS ビルド
build: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) build

# 中間ファイル等の一括削除
clear:
	sh ../scripts/clear-directories.sh "." "Pods"
	sh ../scripts/clear-directories.sh "App" "App.xcodeproj"
	sh ../scripts/clear-directories.sh "App" "App.xcworkspace"
	rm -f App/R.generated.swift
	sh ../scripts/clear-directories.sh "App/App" "public"
	rm -f App/App/capacitor.config.json
	sh ../scripts/clear-directories.sh "App/fastlane" "test_output"
	rm -f App/fastlane/report.xml

# 開発環境のセットアップ
init: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) init

# Xcode プロジェクトの生成
setup-xcode: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) setup-xcode

# Xcode テストの実行
tests: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) tests
