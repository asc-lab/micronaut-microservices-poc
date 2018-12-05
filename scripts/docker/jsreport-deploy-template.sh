JSREPORT_HOST='jsreport'
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"name": "POLICY", "recipe" : "chrome-pdf", "engine": "handlebars", "content": "POLICY"}' \
    http://${JSREPORT_HOST}/odata/templates
