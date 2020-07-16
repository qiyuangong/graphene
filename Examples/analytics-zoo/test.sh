#!/bin/bash

make clean
make SGX=1 -j20
#make SGX=1 DEBUG=1
make test
#make test 2>&1 | tee debug.log
