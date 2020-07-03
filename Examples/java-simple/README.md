# Java example

This directory contains an example for running Java in Graphene, including
the Makefile and a template for generating the manifest. The application is
tested on Ubuntu 16.04 and Ubuntu 18.04, with both normal Linux and SGX
platforms. The tested versions of Java is openjdk-11.

## Installing prerequisites

For generating the manifest and running the test scripts, please run the following
command to install the required utility packages (Ubuntu-specific):

```bash
    sudo apt-get install openjdk-11-jdk
```

```bash
    javac Hello.java
```
```bash
    javac TestFloat.java
```

```bash
    javac CopyMemory.java
    javac CopyMemory.java --add-exports java.base/sun.nio.ch=ALL-UNNAMED
```


## Generating the manifest

### Building for Linux

Run `make` (non-debug) or `make DEBUG=1` (debug) in the directory.

### Building for SGX

Run `make SGX=1` (non-debug) or `make SGX=1 DEBUG=1` (debug) in the directory.

## Run Java with Graphene

Here's an example of running Python scripts under Graphene:

Without SGX:

```bash
./pal_loader java.manifest.sgx -XX:MaxHeapSize=28m -XX:CompressedClassSpaceSize=22m Hello
```

With SGX:

```bash
SGX=1 ./pal_loader java.manifest.sgx -XX:MaxHeapSize=28m -XX:CompressedClassSpaceSize=22m Hello
```

You can also manually run included tests:

```bash
make test
```
