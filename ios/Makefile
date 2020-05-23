.PHONY: default
default: setup-xcode


# Xcode プロジェクトのパス
PATH_XCODE_PROJECT := App


# 開発環境のセットアップ
init: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) init

# Xcode プロジェクトの生成
setup-xcode: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) setup-xcode

# Xcode テストの実行
tests: $(PATH_XCODE_PROJECT)
	cd $<; $(MAKE) tests
