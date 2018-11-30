helm delete --purge elk
kubectl delete service elk-kibana-ext
kubectl delete service elk-elasticsearch-client
