call mvn clean install -f command-bus-api
if errorlevel 1 exit /B 1

call mvn clean install -f command-bus
if errorlevel 1 exit /B 1

call mvn clean install -f policy-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f documents-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f payment-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f policy-search-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f pricing-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f product-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f dashboard-service-api
if errorlevel 1 exit /B 1

call mvn clean install -f auth-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f policy-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f payment-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f policy-search-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f pricing-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f product-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f documents-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f chat-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f dashboard-service -DskipTests
if errorlevel 1 exit /B 1

call mvn clean install -f agent-portal-gateway -DskipTests
if errorlevel 1 exit /B 1
