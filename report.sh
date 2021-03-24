#!/usr/bin/env bash

filterTestNames() {
  grep 'test[A-Z]' | grep -v testClasses | sed 's/test//'
}

if [ -z "$1" ]; then
  ./gradlew tasks --all | filterTestNames
else
  open "build/reports/tests/test${1}/index.html"
fi
