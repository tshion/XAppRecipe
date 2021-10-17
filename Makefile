.PHONY: default
default: setup


# 中間ファイル等を一括で削除する
clear:
	rm -f -r "webui/loader"
	sh scripts/clear-directories.sh "." "dist"
	sh scripts/clear-directories.sh "." "node_modules"
	sh scripts/clear-directories.sh "." "www"
	@echo finish $@.


# 開発環境の整備
setup:
	@make setup-js

# 開発環境の整備(JS 関連)
setup-js:
	npm ci
	npx lerna bootstrap
