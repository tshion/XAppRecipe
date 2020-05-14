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
    echo "Require Homebrew"
    exit 1
fi


# CocoaPods のインストール
if ! type "pod" > /dev/null; then
    echo '`pod` not found. Install CocoaPods'
    brew install cocoapods
else
    echo 'Skip installing CocoaPods'
fi
# Fastlane のセットアップ
if ! type "fastlane" > /dev/null; then
    echo '`fastlane` not found. Install fastlane'
    brew install fastlane
else
    echo "Skip installing Fastlane"
fi
# XcodeGen のインストール
if ! type "xcodegen" > /dev/null; then
    echo '`xcodegen` not found. Install XcodeGen'
    brew install xcodegen
else
    echo 'Skip installing XcodeGen'
fi
