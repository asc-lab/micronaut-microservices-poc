RESOURCE_GROUP=micronaut
CLUSTER=micronaut
az group create --name ${RESOURCE_GROUP} --location westeurope
az aks create \
    --resource-group ${RESOURCE_GROUP} \
    --name ${CLUSTER} \
    --node-vm-size Standard_D2_v2 \
    --node-count 2 \
    --enable-addons monitoring \
    --generate-ssh-keys
az aks get-credentials \
    --resource-group=${RESOURCE_GROUP} \
    --name=${CLUSTER} \
    --overwrite-existing
