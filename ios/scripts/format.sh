#!/bin/sh

mint run swiftformat "$SRCROOT/App"
mint run swiftformat "$SRCROOT/AppPreview"
mint run swiftformat "$SRCROOT/AppTests"
