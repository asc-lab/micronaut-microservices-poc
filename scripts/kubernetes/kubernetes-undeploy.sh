BASE=`dirname $0`

SERVICES=`find ${BASE}/../../*/k8s/*service.yml`
for SRV in ${SERVICES} ;
do
    kubectl delete -f ${SRV}
done

CONFIGS=`find ${BASE}/../../infra/k8s/*-config.yml`
for CONFIG in ${CONFIGS} ;
do
    kubectl delete -f ${CONFIG}
done

DEPLOYMENTS=`find ${BASE}/../../*/k8s/*deployment.yml`
for DEPLOYMENT in ${DEPLOYMENTS} ;
do
    kubectl delete -f ${DEPLOYMENT}
done

#kubectl delete -f ${BASE}/../../infra/k8s/jsreport-config.yml
#kubectl delete -f ${BASE}/../../infra/k8s/kafka-config.yml
