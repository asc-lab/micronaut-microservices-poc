get-service-host() {
    SERVICE=$1
    EXTERNAL_NAME=`kubectl -o json get services ${SERVICE} | jq -r .status.loadBalancer.ingress[0].hostname`
    EXTERNAL_IP=`kubectl -o json get services ${SERVICE} | jq -r .status.loadBalancer.ingress[0].ip`
    CLUSTER_IP=`kubectl -o json get services ${SERVICE} | jq -r .spec.clusterIP`
    if [ "${EXTERNAL_NAME}" != "null" ]
    then
        SERVICE_HOST=${EXTERNAL_NAME}
    elif [ "${EXTERNAL_IP}" != "null" ]
    then
        SERVICE_HOST=${EXTERNAL_IP}
    else
        SERVICE_HOST=${CLUSTER_IP}
    fi
    echo ${SERVICE_HOST}
}

JSREPORT_HOST=`get-service-host jsreport`

echo "JS Report template installation:"
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"name": "POLICY", "recipe" : "chrome-pdf", "engine": "handlebars", "content": "POLICY"}' \
    http://${JSREPORT_HOST}/odata/templates
