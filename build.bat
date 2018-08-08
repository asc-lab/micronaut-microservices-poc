call mvn clean install -f payment-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f policy-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f pricing-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f product-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f auth-service
if errorlevel 1 exit /B 1

call mvn clean install -f payment-service
if errorlevel 1 exit /B 1

call mvn clean install -f policy-service -Dmaven.test.skip
if errorlevel 1 exit /B 1

call mvn clean install -f pricing-service -Dmaven.test.skip
if errorlevel 1 exit /B 1

call mvn clean install -f product-service
if errorlevel 1 exit /B 1

call mvn clean install -f agent-portal-gateway
if errorlevel 1 exit /B 1

call yarn --cwd web-vue install
if errorlevel 1 exit /B 1
call yarn --cwd web-vue run build
if errorlevel 1 exit /B 1
