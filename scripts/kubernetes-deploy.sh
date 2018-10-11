BASE=`dirname $0`

#required by elasticsearch
#sysctl -w vm.max_map_count=262144

SERVICES=`find ${BASE}/../*/k8s/*service.yml`
for SRV in ${SERVICES} ;
do
    kubectl create -f ${SRV}
done

DEPLOYMENTS=`find ${BASE}/../infra/k8s/*deployment.yml`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl create -f ${DEPLOYMENT}
done

echo "waiting for infra to start"
sleep 10

${BASE}/kafka-create-cluster.sh

DEPLOYMENTS=`find ${BASE}/../*/k8s/*deployment.yml | grep -v /infra/k8s/`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl create -f ${DEPLOYMENT}
done

WEB=`kubectl -o json get services web-vue | jq -r .spec.clusterIP`

echo "HTTP endpoint: http://${WEB}"