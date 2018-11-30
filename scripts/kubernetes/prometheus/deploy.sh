helm repo add coreos https://s3-eu-west-1.amazonaws.com/coreos-charts/stable/
helm install coreos/prometheus-operator --name prometheus-operator --namespace monitoring
helm install coreos/kube-prometheus --name kube-prometheus --set global.rbacEnable=true --namespace monitoring
kubectl expose deployment kube-prometheus-grafana -n monitoring --type=LoadBalancer --name=kube-prometheus-grafana-ext --port 80 --target-port 3000
