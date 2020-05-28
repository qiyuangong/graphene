make clean
cp ../gcc/*.sig .
cp ../gcc/*.sgx .
make PYTHONPATH=/usr PYTHONVERSION=python3.6 SGX=1
cp python.manifest.sgx python3.6.manifest.sgx
SGX=1 ./pal_loader python.manifest scripts/test-ray.py
