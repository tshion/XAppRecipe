#!/bin/sh

for target in `\find $1 -maxdepth 2 -name $2 -type d`;
do
    rm -f -r $target
done
