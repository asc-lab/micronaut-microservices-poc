KAFKA_MANAGER_EXTERNAL_IP=`kubectl -o json get services kafka-manager | jq -r .status.loadBalancer.ingress[0].ip`
KAFKA_MANAGER_CLUSTER_IP=`kubectl -o json get services kafka-manager | jq -r .spec.clusterIP`
if [ "$KAFKA_MANAGER_EXTERNAL_IP" != "null" ]
then
    KAFKA_MANAGER=$KAFKA_MANAGER_EXTERNAL_IP
else
    KAFKA_MANAGER=$KAFKA_MANAGER_CLUSTER_IP
fi
CLUSTER_NAME=kafka-cluster
ZK_HOST=zookeeper
ZK_PORT=2181
KAFKA_VERSION=1.1.0
curl \
  -vs -o /dev/null \
  -d "name=${CLUSTER_NAME}&zkHosts=${ZK_HOST}%3A${ZK_PORT}&kafkaVersion=${KAFKA_VERSION}&jmxUser=&jmxPass=&tuning.brokerViewUpdatePeriodSeconds=30&tuning.clusterManagerThreadPoolSize=2&tuning.clusterManagerThreadPoolQueueSize=100&tuning.kafkaCommandThreadPoolSize=2&tuning.kafkaCommandThreadPoolQueueSize=100&tuning.logkafkaCommandThreadPoolSize=2&tuning.logkafkaCommandThreadPoolQueueSize=100&tuning.logkafkaUpdatePeriodSeconds=30&tuning.partitionOffsetCacheTimeoutSecs=5&tuning.brokerViewThreadPoolSize=8&tuning.brokerViewThreadPoolQueueSize=1000&tuning.offsetCacheThreadPoolSize=8&tuning.offsetCacheThreadPoolQueueSize=1000&tuning.kafkaAdminClientThreadPoolSize=8&tuning.kafkaAdminClientThreadPoolQueueSize=1000&tuning.kafkaManagedOffsetMetadataCheckMillis=30000&tuning.kafkaManagedOffsetGroupCacheSize=1000000&tuning.kafkaManagedOffsetGroupExpireDays=7&securityProtocol=PLAINTEXT" \
  -X POST \
  http://${KAFKA_MANAGER}/clusters 2>&1

