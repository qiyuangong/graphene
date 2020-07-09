# Use one of the following commands to build the manifest for Python3:
#
# - make                Building for Linux
# - make DEBUG=1        Building for Linux (with Graphene debug output)
# - make SGX=1          Building for SGX
# - make SGX=1 DEBUG=1  Building for SGX (with Graphene debug output)
#
# Use `make clean` to remove Graphene-generated files.

# Constants

# Relative path to Graphene root
GRAPHENEDIR ?= ../..

ifeq ($(DEBUG),1)
GRAPHENEDEBUG = inline
else
GRAPHENEDEBUG = none
endif

.PHONY: all
all: java.manifest pal_loader
ifeq ($(SGX),1)
all: java.manifest.sgx java.token java.sig
endif

java.manifest: java.manifest.template
	sed -e 's|$$(GRAPHENEDIR)|'"$(GRAPHENEDIR)"'|g' \
		-e 's|$$(GRAPHENEDEBUG)|'"$(GRAPHENEDEBUG)"'|g' \
		$< > $@

# Generating the SGX-specific manifest (java.manifest.sgx), the enclave signature,
# and the token for enclave initialization.

java.manifest.sgx: java.manifest
	$(GRAPHENEDIR)/Pal/src/host/Linux-SGX/signer/pal-sgx-sign \
		-libpal $(GRAPHENEDIR)/Runtime/libpal-Linux-SGX.so \
		-key $(GRAPHENEDIR)/Pal/src/host/Linux-SGX/signer/enclave-key.pem \
		-manifest $< -output $@

java.sig: java.manifest.sgx

java.token: java.sig
	$(GRAPHENEDIR)/Pal/src/host/Linux-SGX/signer/pal-sgx-get-token -output $@ -sig $<

# Extra executables
pal_loader:
	ln -s $(GRAPHENEDIR)/Runtime/pal_loader $@

.PHONY: clean
clean:
	$(RM) *.class *.manifest *.manifest.sgx *.token *.sig pal_loader OUTPUT*

.PHONY: distclean
distclean: clean

.PHONY: test
test:
	# TorchNet OK
	#./pal_loader SGX java.manifest.sgx -XX:MaxHeapSize=8g -cp benchmark-0.2.0-SNAPSHOT-jar-with-dependencies.jar com.intel.analytics.zoo.benchmark.inference.TorchNetPerf -m traced_resnet50_model.pt --iteration 1 --batchSize 4
	# OpenVINO OK
	#./pal_loader java.manifest.sgx -Xmx6g -XX:MaxPermSize=1g -cp benchmark-0.2.0-SNAPSHOT-jar-with-dependencies.jar com.intel.analytics.zoo.benchmark.inference.OpenVINOPerf -m resnet_v1_50_i8.xml --iteration 1 --batchSize 4
	# OpenVINO int8 OK
	#./pal_loader SGX java.manifest.sgx -XX:MaxHeapSize=6g -cp benchmark-0.2.0-SNAPSHOT-jar-with-dependencies.jar com.intel.analytics.zoo.benchmark.inference.OpenVINOPerf -m resnet_v1_50_i8.xml --iteration 5 --batchSize 4
	# TODO BigDL MKLDNN
	./pal_loader SGX java.manifest.sgx -XX:MaxHeapSize=8g -Dbigdl.engineType=mkldnn -Dbigdl.mklNumThreads=10 -cp benchmark-0.2.0-SNAPSHOT-jar-with-dependencies.jar com.intel.analytics.zoo.benchmark.inference.BigDLPerf -m bigdl-res50-imagenet.model --iteration 1 --batchSize 4
	# TODO BigDL Blas
	#./pal_loader SGX java.manifest.sgx -Xmx6g -XX:MaxPermSize=1g -cp benchmark-0.2.0-SNAPSHOT-jar-with-dependencies.jar com.intel.analytics.zoo.benchmark.inference.BigDLBLASPerf -m analytics-zoo_resnet-50-int8_imagenet_0.5.0.model --iteration 1 --batchSize 4
	# TODO TFNet 
	#./pal_loader SGX java.manifest.sgx -Xmx8g -XX:MaxPermSize=1g -cp benchmark-0.2.0-SNAPSHOT-jar-with-dependencies.jar com.intel.analytics.zoo.benchmark.inference.TFNetPerf -m tf_res50 --iteration 1 --batchSize 1
	# TensorFlow Java
	#./pal_loader SGX java.manifest.sgx -XX:MaxHeapSize=7g -cp test-tensorflow-1.0-SNAPSHOT-jar-with-dependencies.jar HelloTensorFlow
