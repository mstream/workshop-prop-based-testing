#!/usr/bin/env bash

filterTestNames() {
  grep 'test[A-Z]' | grep -v testClasses | sed 's/test//'
}

if [ -z "$1" ]; then
  ./gradlew tasks --all | filterTestNames
else
  ./gradlew -i clean "test$1"
fi
