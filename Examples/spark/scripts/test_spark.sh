set -x
java -cp '/home/qiyuan/working/graphene/Examples/spark/scripts/spark-2.4.3-bin-hadoop2.7/conf/:/home/qiyuan/working/graphene/Examples/spark/scripts/spark-2.4.3-bin-hadoop2.7/jars/*' -Xmx1g org.apache.spark.deploy.master.Master --host localhost --port 7077 --webui-port 8080
#ctest
