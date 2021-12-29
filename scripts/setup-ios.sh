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


# Mint のセットアップ
if ! type "mint" > /dev/null; then
    echo '`mint` not found. Install Mint'
    git clone https://github.com/yonaskolb/Mint.git
    make -C Mint
    rm -rf Mint
fi

# Mintfile から依存関係を復元
mint bootstrap
