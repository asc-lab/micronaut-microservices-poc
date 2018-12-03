helm install --name elk --values values.yaml stable/elastic-stack
kubectl expose deployment elk-elasticsearch-client --type=ClusterIP --name=elasticsearch-client
