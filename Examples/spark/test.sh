#!/bin/bash

make clean
make SGX=1
make test
