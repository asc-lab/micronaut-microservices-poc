call mvn clean install -f command-bus-api
if errorlevel 1 exit /B 1

call mvn clean install -f command-bus
if errorlevel 1 exit /B 1

call mvn clean install -f payment-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f policy-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f policy-search-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f pricing-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f product-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f documents-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f dashboard-api
if errorlevel 1 exit /B 1

call mvn clean install -f auth-service
if errorlevel 1 exit /B 1

call mvn clean install -f payment-service
if errorlevel 1 exit /B 1

call mvn clean install -f policy-service
if errorlevel 1 exit /B 1

call mvn clean install -f documents-service
if errorlevel 1 exit /B 1

call mvn clean install -f policy-search-service
if errorlevel 1 exit /B 1

call mvn clean install -f pricing-service
if errorlevel 1 exit /B 1

call mvn clean install -f product-service
if errorlevel 1 exit /B 1

call mvn clean install -f dashboard-service
if errorlevel 1 exit /B 1

call mvn clean install -f agent-portal-gateway
if errorlevel 1 exit /B 1

call mvn clean install -f chat-service
if errorlevel 1 exit /B 1

call yarn --cwd web-vue install
if errorlevel 1 exit /B 1
call yarn --cwd web-vue run build
if errorlevel 1 exit /B 1
