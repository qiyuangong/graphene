make clean
make SGX=1
SGX=1 ./pal_loader bash.manifest -c "cd scripts && bash test.sh"
