#
# https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html
#
CLUSTER=micronaut
VPC_NAME=${CLUSTER}-vpc
ADMIN_ROLE_NAME=${CLUSTER}-admin
WORKER_AMI=ami-0c7a4976cb6fafd3a
WORKER_INSTANCE_TYPE=t2.medium

# Create service role
aws iam create-role \
    --role-name ${ADMIN_ROLE_NAME} \
    --assume-role-policy-document file://cluster-admin-role-policy-document.json

ADMIN_ROLE_ARN=`aws iam get-role --role-name ${ADMIN_ROLE_NAME} --output json |jq -r .Role.Arn`

aws iam attach-role-policy \
    --role-name ${ADMIN_ROLE_NAME} \
    --policy-arn arn:aws:iam::aws:policy/AmazonEKSClusterPolicy

aws iam attach-role-policy \
    --role-name ${ADMIN_ROLE_NAME} \
    --policy-arn arn:aws:iam::aws:policy/AmazonEKSServicePolicy

# Create VPC
aws cloudformation create-stack \
    --stack-name ${VPC_NAME} \
    --template-url https://amazon-eks.s3-us-west-2.amazonaws.com/cloudformation/2018-08-30/amazon-eks-vpc-sample.yaml

#aws cloudformation describe-stacks --stack-name ${VPC_NAME} --output json

VPC_ID=`aws cloudformation describe-stacks --stack-name ${VPC_NAME} --output json | jq -rc '.Stacks[0].Outputs[] | select (.OutputKey == "VpcId") | .OutputValue'`
SUBNETS=`aws cloudformation describe-stacks --stack-name ${VPC_NAME} --output json | jq -rc '.Stacks[0].Outputs[] | select (.OutputKey == "SubnetIds") | .OutputValue'`
SECURITY_GROUPS=`aws cloudformation describe-stacks --stack-name ${VPC_NAME} --output json | jq -rc '.Stacks[0].Outputs[] | select (.OutputKey == "SecurityGroups") | .OutputValue'`

# Create Master Cluster
aws eks create-cluster \
    --name ${CLUSTER} \
    --role-arn ${ADMIN_ROLE_ARN} \
    --resources-vpc-config subnetIds=${SUBNETS},securityGroupIds=${SECURITY_GROUPS}


# TODO: Wait for the master cluster to become ACTIVE
# should take less than 10 minutes
# aws eks describe-cluster --name ${CLUSTER} --query cluster.status
sleep 600

aws eks update-kubeconfig --name ${CLUSTER}

aws ec2 create-key-pair --key-name ${CLUSTER}-keys

# Create Worker Nodes
# https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html#eks-launch-workers
aws cloudformation create-stack \
    --stack-name ${CLUSTER}-workers \
    --template-url https://amazon-eks.s3-us-west-2.amazonaws.com/cloudformation/2018-08-30/amazon-eks-nodegroup.yaml \
    --capabilities "CAPABILITY_IAM" \
    --parameters "[ {\"ParameterKey\": \"KeyName\", \"ParameterValue\": \"${CLUSTER}-keys\"},
                    {\"ParameterKey\": \"NodeImageId\", \"ParameterValue\": \"${WORKER_AMI}\"},
                    {\"ParameterKey\": \"NodeInstanceType\", \"ParameterValue\": \"${WORKER_INSTANCE_TYPE}\"},
                    {\"ParameterKey\": \"ClusterName\", \"ParameterValue\": \"${CLUSTER}\"},
                    {\"ParameterKey\": \"NodeGroupName\", \"ParameterValue\": \"${CLUSTER}-nodegroup\"},
                    {\"ParameterKey\": \"ClusterControlPlaneSecurityGroup\", \"ParameterValue\": \"${SECURITY_GROUPS}\"},
                    {\"ParameterKey\": \"VpcId\", \"ParameterValue\": \"${VPC_ID}\"},
                    {\"ParameterKey\": \"Subnets\", \"ParameterValue\": \"${SUBNETS}\"}]"
                    
WORKER_ROLE_ARN=`aws cloudformation describe-stacks --stack-name ${CLUSTER}-workers --output json | jq -rc '.Stacks[0].Outputs[] | select (.OutputKey == "NodeInstanceRole") | .OutputValue'`

curl -O https://amazon-eks.s3-us-west-2.amazonaws.com/cloudformation/2018-08-30/aws-auth-cm.yaml
sed -i "s|rolearn:.*|rolearn: ${WORKER_ROLE_ARN}|" aws-auth-cm.yaml
kubectl apply -f aws-auth-cm.yaml
