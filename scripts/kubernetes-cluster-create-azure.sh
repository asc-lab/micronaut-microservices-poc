RESOURCE_GROUP=micronaut
CLUSTER=micronaut
az group create --name ${RESOURCE_GROUP} --location westeurope
az acs create --orchestrator-type kubernetes --agent-count 2 --agent-vm-size Standard_D2_v2 --resource-group ${RESOURCE_GROUP} --name ${CLUSTER} --generate-ssh-keys
az acs kubernetes get-credentials --resource-group=${RESOURCE_GROUP} --name=${CLUSTER}
