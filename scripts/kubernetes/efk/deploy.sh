helm install --name elk --values values.yaml stable/elastic-stack
kubectl expose deployment elk-kibana --type=LoadBalancer --name=elk-kibana-ext --port 80 --target-port 5601
kubectl expose deployment elk-elasticsearch-client --type=ClusterIP --name=elasticsearch-client
