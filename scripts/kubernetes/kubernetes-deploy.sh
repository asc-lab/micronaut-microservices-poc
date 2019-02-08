BASE=`dirname $0`

wait-for() {
    RESOURCE_TYPE=$1
    RESOURCE_NAME=$2
    REPLICAS=0
    until [ $(kubectl get ${RESOURCE_TYPE} -o json |jq -c ".items[] | select(.status.readyReplicas == 1 and .metadata.name == \"${RESOURCE_NAME}\")" | jq .metadata.name | wc -l) -gt 0 ]; do
      echo waiting
      sleep 2
    done
}

SERVICES=`find ${BASE}/../../*/k8s/*service.yml`
for SRV in ${SERVICES} ;
do
    kubectl apply -f ${SRV}
done

kubectl apply -f ${BASE}/../../infra/k8s/zookeeper-deployment.yml
echo "waiting for zookeeper to start"
wait-for statefulset zookeeper
# workaround for missing zk readiness probe
sleep 5

DEPLOYMENTS=`find ${BASE}/../../infra/k8s/*deployment.yml`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl apply -f ${DEPLOYMENT}
done

echo "waiting for kafka-manager to start"
wait-for deployment kafka-manager
echo "waiting for kafka-server to start"
wait-for statefulset kafka-server

CONFIGS=`find ${BASE}/../../infra/k8s/*-config.yml`
for CONFIG in ${CONFIGS} ;
do
    kubectl apply -f ${CONFIG}
done

DEPLOYMENTS=`find ${BASE}/../../*/k8s/*deployment.yml | grep -v /infra/k8s/`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl apply -f ${DEPLOYMENT}
done
