.PHONY: default
default: setup-xcode


# Xcode プロジェクトのパス
PATH_XCODE_PROJECT := App


# 開発環境のセットアップ
init:
	@make setup-command
	@make setup-xcode

# 依存コマンドのセットアップ
setup-command:
	sh setup-command.sh

# Xcode プロジェクトの生成
setup-xcode: $(PATH_XCODE_PROJECT)
	xcodegen generate --spec $^/project.yml
