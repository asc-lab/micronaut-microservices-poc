CLUSTER=micronaut
VPC_NAME=${CLUSTER}-vpc
ADMIN_ROLE_NAME=${CLUSTER}-admin

aws cloudformation delete-stack \
    --stack-name ${CLUSTER}-workers

aws ec2 delete-key-pair --key-name ${CLUSTER}-keys

aws eks delete-cluster \
    --name ${CLUSTER}

aws cloudformation delete-stack \
    --stack-name ${VPC_NAME}

aws iam detach-role-policy \
    --role-name ${ADMIN_ROLE_NAME} \
    --policy-arn arn:aws:iam::aws:policy/AmazonEKSClusterPolicy

aws iam detach-role-policy \
    --role-name ${ADMIN_ROLE_NAME} \
    --policy-arn arn:aws:iam::aws:policy/AmazonEKSServicePolicy

aws iam delete-role \
    --role-name ${ADMIN_ROLE_NAME}
