sed -i -E 's|kafkaserver||g' /etc/hosts
sed -i -E 's|^(127.0.0.1.*)$|\1 kafkaserver|' /etc/hosts

iptables -A INPUT -p tcp --dport 80 -j ACCEPT
iptables -A INPUT -p tcp --dport 8081 -j ACCEPT
iptables -A INPUT -p tcp --dport 8090 -j ACCEPT
