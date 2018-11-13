FROM nginx
MAINTAINER ASC-LAB

ENV \
    GATEWAY_URL=${GATEWAY_URL:-http://localhost:8081} \
    AUTH_URL=${AUTH_URL:-http://localhost:8090} \
    CHAT_SERVICE_URL=${CHAT_SERVICE_URL:-http://localhost:8787}

COPY ./dist /usr/share/nginx/html

COPY ./nginx-app.conf /etc/nginx/conf.d/default.tmpl

COPY entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]
