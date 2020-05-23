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


# Bundler の確認
if ! type "bundle" > /dev/null; then
    echo "Require Bundler"
    exit 1
fi

# Gemfile から依存関係を復元
bundle config set path 'vendor/bundle'
bundle install


# Mint のセットアップ
if ! type "mint" > /dev/null; then
    echo '`mint` not found. Install Mint'
    git clone https://github.com/yonaskolb/Mint.git
    make -C Mint
    rm -rf Mint
fi

# Mintfile から依存関係を復元
mint bootstrap
