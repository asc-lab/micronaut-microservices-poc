BASE=`dirname $0`

wait-for-deployment() {
    DEPLOYMENT=$1
    REPLICAS=0
    until [ $(kubectl get deployments -o json |jq -c ".items[] | select(.status.readyReplicas == 1 and .metadata.name == \"${DEPLOYMENT}\")" | jq .metadata.name | wc -l) -gt 0 ]; do
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
wait-for-deployment zookeeper
# workaround for missing zk readiness probe
sleep 5

DEPLOYMENTS=`find ${BASE}/../../infra/k8s/*deployment.yml`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl apply -f ${DEPLOYMENT}
done

echo "waiting for kafka-manager to start"
wait-for-deployment kafka-manager
echo "waiting for kafka-server to start"
wait-for-deployment kafka-server

${BASE}/kafka-create-cluster.sh

DEPLOYMENTS=`find ${BASE}/../../*/k8s/*deployment.yml | grep -v /infra/k8s/`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl apply -f ${DEPLOYMENT}
done

echo "waiting for jsreport to start"
wait-for-deployment jsreport
${BASE}/jsreport-deploy-template.sh

WEB=`kubectl -o json get services web-vue | jq -r .spec.clusterIP`

echo "HTTP endpoint: http://${WEB}"