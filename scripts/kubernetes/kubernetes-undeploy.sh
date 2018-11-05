BASE=`dirname $0`

SERVICES=`find ${BASE}/../../*/k8s/*service.yml`
for SRV in ${SERVICES} ;
do
    kubectl delete -f ${SRV}
done

DEPLOYMENTS=`find ${BASE}/../../*/k8s/*deployment.yml`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl delete -f ${DEPLOYMENT}
done