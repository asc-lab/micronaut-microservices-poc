KAFKAMANAGER=`kubectl -o json get services kafka-manager | jq -r .spec.clusterIP`
CLUSTER_NAME=kafka-cluster
KAFKASERVER_HOST=kafkaserver
KAFKASERVER_PORT=2181
curl \
  -vs -o /dev/null \
  -d "name=${CLUSTER_NAME}&zkHosts=${KAFKASERVER_HOST}%3A${KAFKASERVER_PORT}&kafkaVersion=0.9.0.1&jmxUser=&jmxPass=&pollConsumers=true&tuning.brokerViewUpdatePeriodSeconds=30&tuning.clusterManagerThreadPoolSize=2&tuning.clusterManagerThreadPoolQueueSize=100&tuning.kafkaCommandThreadPoolSize=2&tuning.kafkaCommandThreadPoolQueueSize=100&tuning.logkafkaCommandThreadPoolSize=2&tuning.logkafkaCommandThreadPoolQueueSize=100&tuning.logkafkaUpdatePeriodSeconds=30&tuning.partitionOffsetCacheTimeoutSecs=5&tuning.brokerViewThreadPoolSize=3&tuning.brokerViewThreadPoolQueueSize=1000&tuning.offsetCacheThreadPoolSize=3&tuning.offsetCacheThreadPoolQueueSize=1000&tuning.kafkaAdminClientThreadPoolSize=3&tuning.kafkaAdminClientThreadPoolQueueSize=1000" \
  -X POST \
  http://${KAFKAMANAGER}/clusters 2>&1

