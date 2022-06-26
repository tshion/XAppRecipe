#!/usr/bin/env sh

# シェルの確認
if [ "$SHELL" = '/bin/bash' ] || [ "$SHELL" = '/usr/local/bin/bash' ]; then
    SHELL_RESOURCE=~/.bashrc
elif [ "$SHELL" = '/bin/zsh' ] || [ "$SHELL" = '/usr/local/bin/zsh' ]; then
    SHELL_RESOURCE=~/.zshrc
else
    echo "Unsupported shell: ${SHELL}"
    exit 1
fi


# 必須コマンドの確認
if ! type "mint" > /dev/null; then
    echo '`mint` not found.'
    exit 1
fi


# Mintfile からツール依存関係を復元
mint bootstrap
