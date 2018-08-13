CLUSTER_NAME=kafka-cluster
curl \
  -vs -o /dev/null \
  -d "name=${CLUSTER_NAME}&zkHosts=kafkaserver%3A2181&kafkaVersion=0.9.0.1&jmxUser=&jmxPass=&pollConsumers=true&tuning.brokerViewUpdatePeriodSeconds=30&tuning.clusterManagerThreadPoolSize=2&tuning.clusterManagerThreadPoolQueueSize=100&tuning.kafkaCommandThreadPoolSize=2&tuning.kafkaCommandThreadPoolQueueSize=100&tuning.logkafkaCommandThreadPoolSize=2&tuning.logkafkaCommandThreadPoolQueueSize=100&tuning.logkafkaUpdatePeriodSeconds=30&tuning.partitionOffsetCacheTimeoutSecs=5&tuning.brokerViewThreadPoolSize=3&tuning.brokerViewThreadPoolQueueSize=1000&tuning.offsetCacheThreadPoolSize=3&tuning.offsetCacheThreadPoolQueueSize=1000&tuning.kafkaAdminClientThreadPoolSize=3&tuning.kafkaAdminClientThreadPoolQueueSize=1000" \
  -X POST \
  http://localhost:9000/clusters 2>&1

