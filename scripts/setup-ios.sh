#!/bin/sh

# シェルの確認
if [ "$SHELL" = '/bin/bash' ] || [ "$SHELL" = '/usr/local/bin/bash' ]; then
    SHELL_RESOURCE=~/.bashrc
elif [ "$SHELL" = '/bin/zsh' ] || [ "$SHELL" = '/usr/local/bin/zsh' ]; then
    SHELL_RESOURCE=~/.zshrc
else
    echo "Unsupported shell: ${SHELL}"
    exit 1
fi


# Homebrew の確認
if ! type "brew" > /dev/null; then
    echo '`brew` not found. Install Homebrew'
    exit 1
fi


# Mint のセットアップ
if ! type "mint" > /dev/null; then
    echo '`mint` not found. Install Mint'
    brew install mint
fi
