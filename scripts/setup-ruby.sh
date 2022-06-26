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


# gem の確認
if ! type "gem" > /dev/null; then
    echo '`gem` not found. Install Ruby'
    exit 1
fi


# Bundler のセットアップ
if ! type "gem list bundle" > /dev/null; then
    echo '`bundle` not found. Install bundler'
    gem install bundler
fi


# Gemfile から依存関係を復元
bundle config set path 'vendor/bundle'
bundle install
