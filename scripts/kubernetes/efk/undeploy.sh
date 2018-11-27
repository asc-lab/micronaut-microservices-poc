helm delete --purge elk
kubectl delete service elk-kibana-ext
kubectl delete services elk-elasticsearch-client
