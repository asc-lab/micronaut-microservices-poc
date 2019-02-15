iptables -A INPUT -p tcp --dport 80 -j ACCEPT
iptables -A INPUT -p tcp --dport 8081 -j ACCEPT
iptables -A INPUT -p tcp --dport 8090 -j ACCEPT

curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"name": "POLICY", "recipe" : "chrome-pdf", "engine": "handlebars", "content": "POLICY"}' \
    localhost:5488/odata/templates