USAGE="$(basename $0) service-name version"
[ "$1" ] || {
    echo "Service name is missing!"
    echo "Usage: $USAGE"
    exit 1
}

[ "$1" ] || {
    echo "Service version (tag) is missing!"
    echo "Usage: $USAGE"
    exit 2
}
SRV=$1
TAG=$2

kubectl set image deployment/${SRV} ${SRV}=ascmn/${SRV}:${TAG}
