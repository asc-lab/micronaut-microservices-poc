BASE=`dirname $0`

CONTROLLER_NAME=ingress-nginx-ingress-controller
CONTROLLER_IP=`kubectl -o json get services ${CONTROLLER_NAME} | jq -r .status.loadBalancer.ingress[0].ip`

[ "${CONTROLLER_IP}" == "null" ] && {
    echo "Ingress Controller IP not known!"
    exit 1
}

export DOMAIN=${CONTROLLER_IP}.nip.io

INGRESSES=`find ${BASE}/../../../*/k8s/*ingress.yml`
for INGRESS in ${INGRESSES} ;
do
    envsubst < $INGRESS '${DOMAIN}' | kubectl apply -f -
done

envsubst < ${BASE}/../efk/ingress.yml '${DOMAIN}' | kubectl apply -f -
envsubst < ${BASE}/../prometheus/ingress.yml '${DOMAIN}' | kubectl apply -n monitoring -f -

echo "HTTP endpoint: http://${DOMAIN}"
