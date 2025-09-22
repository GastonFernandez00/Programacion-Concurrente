#!/usr/bin/env bash

#set -ex;

echo ""

copy_file()
{
    cp $1 $2
    if [ $? -eq 1 ]; then
        echo "FILE $1 NOT FOUND."
        echo -e "MUST BE RUN FROM THE ROOT DIRECTORY.\n"
        exit 1
    fi
}

# Prepare Hooks
copy_file .extras/hooks/prepare-commit-msg .git/hooks/
copy_file .extras/hooks/pre-commit .git/hooks/
copy_file .extras/hooks/commit-msg .git/hooks/

