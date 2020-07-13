make clean
make SGX=1 DEBUG=1
#./pal_loader bash.manifest -c "/bin/bash -l ./scripts/spark-2.4.3-bin-hadoop2.7/sbin/spark-daemon.sh start org.apache.spark.deploy.master.Master 1 --host localhost --port 7077 --webui-port 8080"
#./pal_loader bash.manifest -c "/bin/bash -l ./scripts/spark-2.4.3-bin-hadoop2.7/bin/spark-class org.apache.spark.deploy.master.Master --host localhost --port 7077 --webui-port 8080"
./pal_loader bash.manifest -c "java -cp /home/qiyuan/working/graphene/Examples/spark/scripts/spark-2.4.3-bin-hadoop2.7/conf/:/home/qiyuan/working/graphene/Examples/spark/scripts/spark-2.4.3-bin-hadoop2.7/jars/\* -Xmx1g org.apache.spark.deploy.master.Master --host localhost --port 7077 --webui-port 8080"
#./pal_loader bash.manifest -c "cd scripts && bash test_spark.sh"
