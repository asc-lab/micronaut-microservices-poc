helm install stable/nginx-ingress \
    --name ingress \
    --set defaultBackend.enabled=false \
    --set controller.stats.enabled=true \
    --set controller.metrics.enabled=true
